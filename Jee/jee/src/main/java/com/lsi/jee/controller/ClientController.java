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
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@WebServlet(name = "clientServlet", value = "/client/*")
@ApplicationScoped
public class ClientController extends HttpServlet {


  @Inject
  private ClientService clientService;



  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    response.setContentType("text/html");
    String pathInfo = request.getPathInfo();

    if (pathInfo == null || pathInfo.equals("/")) {
      // Default behavior: show all clients
      List<Client> clientList = clientService.getAllClients();
      request.setAttribute("clientList", clientList);
      request.getRequestDispatcher("/WEB-INF/view/clientList.jsp").forward(request, response);
    } else if (pathInfo.startsWith("/") && pathInfo.length() > 1) {
      String type = pathInfo.substring(1);
      if (type.equals("add")) {
        request.getRequestDispatcher("/WEB-INF/view/addClient.jsp").forward(request, response);

      }else if (type.equals("list")) {
        // Show all clients
        List<Client> clientList = clientService.getAllClients();
        request.setAttribute("clientList", clientList);
        request.getRequestDispatcher("/WEB-INF/view/clientList.jsp").forward(request, response);
      } else {
        try {
          // Try to parse as client ID
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
        }
      }
    } else {
      response.getWriter().write("Invalid request path.");
    }
  }


  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");

    String pathInfo = request.getPathInfo();
    if (pathInfo.startsWith("/") && pathInfo.length() > 1) {
      String type = pathInfo.substring(1);

      try {
        JsonReader jsonReader = Json.createReader(request.getInputStream());
        JsonObject jsonObject = jsonReader.readObject();

        switch (type) {
          case "add":
            String nom = jsonObject.getString("nom");
            String prenom = jsonObject.getString("prenom");
            clientService.addClient(nom , prenom);
            break;

          case "delete":
            // Handle delete functionality

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
  }
}
