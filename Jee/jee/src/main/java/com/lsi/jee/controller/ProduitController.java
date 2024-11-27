package com.lsi.jee.controller;

import com.lsi.jee.entity.Produit;
import com.lsi.jee.service.ProduitService;
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
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "produitServlet", value = "/produit/*")
@ApplicationScoped
@Transactional
public class ProduitController extends HttpServlet {

  @Inject
  private ProduitService produitService;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    String pathInfo = request.getPathInfo();

    if (pathInfo == null || pathInfo.equals("/")) {
      showAllProduits(request, response);
    } else if (pathInfo.startsWith("/") && pathInfo.length() > 1) {
      handleGetRequestByType(pathInfo, request, response);
    } else {
      response.getWriter().write("Invalid request path.");
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

  private void showAllProduits(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Produit> produitList = produitService.getAllProduits();
    request.setAttribute("produitList", produitList);
    request.getRequestDispatcher("/WEB-INF/view/produitList.jsp").forward(request, response);
  }

  private void handleGetRequestByType(String pathInfo, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String type = pathInfo.substring(1);
    if (pathInfo.startsWith("/edit")) {
      handleEditPage(request, response);
    }else if (type.equals("add")) {
      request.getRequestDispatcher("/WEB-INF/view/addProduit.jsp").forward(request, response);
    } else if (type.equals("list")) {
      showAllProduits(request, response);
    } else {
      handleProduitDetailsOrError(type, request, response);
    }
  }

  private void handleProduitDetailsOrError(String type, HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      Long id = Long.parseLong(type);
      Produit produit = produitService.getProduit(id);
      if (produit != null) {
        request.setAttribute("produit", produit);
        request.getRequestDispatcher("/WEB-INF/view/oneProduit.jsp").forward(request, response);
      } else {
        response.getWriter().write("Produit not found for ID: " + id);
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

    try (
      JsonReader jsonReader = Json.createReader(request.getInputStream())) {
      JsonObject jsonObject = jsonReader.readObject();
      switch (type) {
        case "add":
          handleAddProduit(jsonObject, response);
          break;
        case "delete":
          handleDeleteProduit(jsonObject, response);
          break;
        case "edit":
          handleEditProduit(jsonObject, response);
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

  private void handleAddProduit(JsonObject jsonObject, HttpServletResponse response) throws IOException {
    String nomProduit = jsonObject.getString("nomProduit");
    BigDecimal prix = new BigDecimal(jsonObject.getString("prix"));
    produitService.addProduit(nomProduit, prix);
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().write("{\"message\": \"Produit added successfully.\"}");
  }


  private void handleDeleteProduit(JsonObject jsonObject, HttpServletResponse response) throws IOException {
    try {
      Long id = jsonObject.getJsonNumber("id").longValue();
      produitService.deleteProduit(id);
      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().write("{\"message\": \"Produit deleted successfully.\"}");
    } catch (IllegalArgumentException e) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
    } catch (Exception e) {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      response.getWriter().write("{\"error\": \"An error :"+e.getMessage()+" .\"}");
    }
  }

  private void handleEditPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try {
      String idStr = request.getParameter("id");
      if (idStr == null) {
        throw new IllegalArgumentException("No ID provided.");
      }

      Long id = Long.parseLong(idStr);
      Produit produit = produitService.getProduit(id);

      if (produit != null) {
        request.setAttribute("produit", produit);
        request.getRequestDispatcher("/WEB-INF/view/editProduit.jsp").forward(request, response);
      } else {
        response.getWriter().write("Produit not found for ID: " + id);
      }
    } catch (NumberFormatException e) {
      response.getWriter().write("Invalid ID format.");
    } catch (IllegalArgumentException e) {
      response.getWriter().write(e.getMessage());
    }
  }
  private void handleEditProduit(JsonObject jsonObject, HttpServletResponse response) throws IOException {
    try {
      Long id = Long.parseLong(jsonObject.getString("id"));
      String newNomProduit = jsonObject.getString("nomProduit");
      BigDecimal newPrix = new BigDecimal(jsonObject.getString("prix"));

      produitService.editProduit(id, newNomProduit, newPrix);

      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().write("{\"message\": \"Produit updated successfully.\"}");
    } catch (IllegalArgumentException e) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
    } catch (Exception e) {
      response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
      response.getWriter().write("{\"error\": \"An error occurred: " + e.getMessage() + "\"}");
    }
  }

}
