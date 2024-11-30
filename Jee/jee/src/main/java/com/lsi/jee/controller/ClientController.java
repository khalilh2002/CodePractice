package com.lsi.jee.controller;

import com.lsi.jee.entity.Client;
import com.lsi.jee.service.ClientService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "clientServlet", value = "/client/*")
@ApplicationScoped
@Transactional
public class ClientController extends HttpServlet {

  @Inject
  private ClientService clientService;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    String pathInfo = request.getPathInfo();

    if (pathInfo == null || pathInfo.equals("/")) {
      showAllClients(request, response);
    } else if (pathInfo.startsWith("/") && pathInfo.length() > 1) {
      handleGetRequestByType(pathInfo, request, response);
    } else {
      response.getWriter().write("Invalid request path.");
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    String pathInfo = request.getPathInfo();
    if (pathInfo != null && pathInfo.startsWith("/") && pathInfo.length() > 1) {
      handlePostRequestByType(pathInfo, request, response);
    } else {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("{\"error\": \"Invalid request path.\"}");
    }
  }

  // Utility methods for doGet

  private void showAllClients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Client> clientList = clientService.getAllClients();
    request.setAttribute("clientList", clientList);
    request.getRequestDispatcher("/WEB-INF/view/clientList.jsp").forward(request, response);
  }

  private void handleGetRequestByType(String pathInfo, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String type = pathInfo.substring(1);

    switch (type) {
      case "add" -> request.getRequestDispatcher("/WEB-INF/view/addClient.jsp").forward(request, response);
      case "list" -> showAllClients(request, response);
      case "edit" -> showEditClientForm(request, response);
      default -> handleClientDetailsOrError(type, request, response);
    }
  }

  private void handleClientDetailsOrError(String type, HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      Long id = Long.parseLong(type);
      Client client = clientService.getClientCommande(id);
      if (client != null) {
        request.setAttribute("client", client);
        request.getRequestDispatcher("/WEB-INF/view/oneClient.jsp").forward(request, response);
      } else {
        response.getWriter().write("Client not found for ID: " + id);
      }
    } catch (NumberFormatException e) {
      response.getWriter().write("Invalid ID format: " + type);
    } catch (ServletException e) {
      throw new RuntimeException(e);
    }
  }

  // Utility methods for doPost

  private void handlePostRequestByType(String pathInfo, HttpServletRequest request, HttpServletResponse response) throws IOException {
    String type = pathInfo.substring(1);

    try (JsonReader jsonReader = Json.createReader(request.getInputStream())) {
      JsonObject jsonObject = (jsonReader).readObject();
      switch (type) {
        case "add":
          handleAddClient(jsonObject, response);
          break;
        case "delete":
          handleDeleteClient(jsonObject, response);
          break;
        case "edit":
          handleEditClient(jsonObject, response);
          break;
        default:
          response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
          response.getWriter().write("{\"error\": \"Invalid action.\"}");
      }
    } catch (Exception e) {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
    }
  }

  private void handleAddClient(JsonObject jsonObject, HttpServletResponse response) throws IOException {
    String nom = jsonObject.getString("nom");
    String prenom = jsonObject.getString("prenom");
    clientService.addClient(nom, prenom);
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().write("{\"message\": \"Client added successfully.\"}");
  }

  private void handleDeleteClient(JsonObject jsonObject, HttpServletResponse response) throws IOException {
    Long clientId = jsonObject.getJsonNumber("id").longValue();
    Client client = clientService.getClient(clientId);

    if (client != null) {
      clientService.deleteClient(clientId);
      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().write("{\"message\": \"Client deleted successfully.\"}");
    } else {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      response.getWriter().write("{\"error\": \"Client not found.\"}");
    }
  }





  private void showEditClientForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String idParam = request.getParameter("id");
    if (idParam != null) {
      try {
        Long id = Long.parseLong(idParam);
        Client client = clientService.getClient(id);
        if (client != null) {
          request.setAttribute("client", client);
          request.getRequestDispatcher("/WEB-INF/view/editClient.jsp").forward(request, response);
        } else {
          response.getWriter().write("Client not found for ID: " + id);
        }
      } catch (NumberFormatException e) {
        response.getWriter().write("Invalid ID format.");
      }
    } else {
      response.getWriter().write("Client ID is required.");
    }
  }
  private void handleEditClient(JsonObject jsonObject, HttpServletResponse response) throws IOException {
    try {
      Long id = jsonObject.getJsonNumber("id").longValue();
      String nom = jsonObject.getString("nom");
      String prenom = jsonObject.getString("prenom");

      clientService.updateClient(id, nom, prenom);
      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().write("{\"message\": \"Client updated successfully.\"}");
    } catch (Exception e) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
    }
  }

}
