package com.lsi.jee.controller;

import com.lsi.jee.entity.Client;
import com.lsi.jee.entity.Commande;
import com.lsi.jee.entity.Produit;
import com.lsi.jee.service.ClientService;
import com.lsi.jee.service.CommandeService;
import com.lsi.jee.service.ProduitService;
import jakarta.inject.Inject;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@WebServlet(value = "/commande/*")
@Transactional
public class CommandeController extends HttpServlet {

  @Inject
  CommandeService commandeService;

  @Inject
  ProduitService produitService;

  @Inject
  ClientService clientService;

  @Override
  public void init() throws ServletException {
    commandeService.test();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    String pathvalue = request.getPathInfo();
    if (pathvalue == null || pathvalue.equals("/")) {
      handleListCommandes(request, response);
    } else if (pathvalue.startsWith("/")) {
      String type = pathvalue.substring(1);
      handleGetByType(type, request, response, out);
    } else {
      out.println("path is wrong : " + pathvalue);
    }
  }

  private void handleListCommandes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Commande> commandes = commandeService.getAllCommandes();
    request.setAttribute("commandes", commandes);
    request.getRequestDispatcher("/WEB-INF/view/commande/list.jsp").forward(request, response);
  }

  private void handleGetByType(String type, HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws ServletException, IOException {
    switch (type) {
      case "list":
        handleListCommandes(request, response);
        break;
      case "add":
        handleAddCommandeView(request, response);
        break;
      default:
        handleGetSingleCommande(type, request, response, out);
        break;
    }
  }

  private void handleAddCommandeView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Produit> produits = produitService.getAllProduits();
    List<Client> clients = clientService.getAllClients();
    request.setAttribute("produits", produits);
    request.setAttribute("clients", clients);
    request.getRequestDispatcher("/WEB-INF/view/commande/add.jsp").forward(request, response);
  }

  private void handleGetSingleCommande(String type, HttpServletRequest request, HttpServletResponse response, PrintWriter out) throws ServletException, IOException {
    try {
      Long id = Long.parseLong(type);
      Commande commande = commandeService.getCommandeWithDetails(id);
      request.setAttribute("commande", commande);
      request.getRequestDispatcher("/WEB-INF/view/commande/one.jsp").forward(request, response);
    } catch (NumberFormatException e) {
      out.println(e.getMessage());
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json;charset=UTF-8");

    String pathvalue = request.getPathInfo();
    if (pathvalue.startsWith("/")) {
      String type = pathvalue.substring(1);
      handlePostByType(type, request, response);
    }
  }

  private void handlePostByType(String type, HttpServletRequest request, HttpServletResponse response) throws IOException {
    try {
      JsonReader jsonReader = Json.createReader(request.getInputStream());
      JsonObject jsonObject = jsonReader.readObject();
      switch (type) {
        case "add":
          handleAddCommande(jsonObject, response);
          break;
        case "delete":
          handleDeleteCommande(jsonObject, response);
          break;
        default:
          sendErrorResponse(response, "Invalid POST type");
          break;
      }
    } catch (Exception e) {
      sendErrorResponse(response, e.getMessage());
    }
  }

  private void handleAddCommande(JsonObject jsonObject, HttpServletResponse response) throws IOException {
    Long clientId = jsonObject.getJsonNumber("client_id").longValue();
    JsonArray produitIdsJson = jsonObject.getJsonArray("produit_ids");

    List<Long> produitIds = new ArrayList<>();
    for (int i = 0; i < produitIdsJson.size(); i++) {
      produitIds.add(produitIdsJson.getJsonNumber(i).longValue());
    }

    commandeService.addCommande(clientId, produitIds);
    sendSuccessResponse(response, "Commande added successfully.");
  }

  private void handleDeleteCommande(JsonObject jsonObject, HttpServletResponse response) throws IOException {
    Long commandeId = jsonObject.getJsonNumber("commande_id").longValue();
    commandeService.deleteCommandeById(commandeId);
    sendSuccessResponse(response, "Commande deleted successfully.");
  }

  private void sendSuccessResponse(HttpServletResponse response, String message) throws IOException {
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().write("{\"message\": \"" + message + "\"}");
  }

  private void sendErrorResponse(HttpServletResponse response, String errorMessage) throws IOException {
    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    response.getWriter().write("{\"error\": \"" + errorMessage + "\"}");
  }
}
