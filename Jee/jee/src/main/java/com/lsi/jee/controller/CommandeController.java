package com.lsi.jee.controller;

import com.lsi.jee.entity.Commande;
import com.lsi.jee.entity.Produit;
import com.lsi.jee.service.CommandeService;
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

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/commande/*")
public class CommandeController extends HttpServlet {

  @Inject
  CommandeService commandeService;


  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();


    String pathvalue = request.getPathInfo();
    out.println(pathvalue);
    if (pathvalue == null || pathvalue.equals("/")) {
      request.getRequestDispatcher("/WEB-INF/view/commande/list.jsp").forward(request, response);
    } else if (pathvalue.startsWith("/")) {
      String type = pathvalue.substring(1);
      switch (type) {
        case "list":
          List<Commande> commandes = commandeService.getAllCommandes();
          request.setAttribute("commandes", commandes);
          request.getRequestDispatcher("/WEB-INF/view/commande/list.jsp").forward(request, response);
          break;
        case "add":
          List<Produit> produits ;
          request.getRequestDispatcher("/WEB-INF/view/commande/add.jsp").forward(request, response);
          break;
        default:
          try {
            Long id = Long.parseLong(type);
            Commande commande = commandeService.getCommandeById(id);
            request.setAttribute("commande", commande);
            request.getRequestDispatcher("/WEB-INF/view/commande/one.jsp").forward(request, response);
          } catch (NumberFormatException e) {
            out.println(e.getMessage());
          }
      }
    } else {
      out.println("path is wrong : " + pathvalue);
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("application/json;charset=UTF-8");
    PrintWriter out = response.getWriter();
    String pathvalue = request.getPathInfo();
    if (pathvalue.startsWith("/")) {
      String type = pathvalue.substring(1);
        try {
          JsonReader jsonReader = Json.createReader(request.getInputStream());
          JsonObject jsonObject = jsonReader.readObject();
          switch (type){
            case "add":
              Long clientId = jsonObject.getJsonNumber("client_id").longValue();
              JsonArray produitIdsJson = jsonObject.getJsonArray("produit_ids");

              // Convert JSON array to List<Long>
              List<Long> produitIds = new ArrayList<>();
              for (int i = 0; i < produitIdsJson.size(); i++) {
                produitIds.add(produitIdsJson.getJsonNumber(i).longValue());
              }

              // Call the service method
              commandeService.addCommande(clientId, produitIds);
              response.setStatus(HttpServletResponse.SC_CREATED);
              response.getWriter().write("{\"message\": \"Commande added successfully.\"}");
              break;
            case "delete":
              Long commandeId = jsonObject.getJsonNumber("commande_id").longValue();

              // Call the service method to delete the commande
              commandeService.deleteCommandeById(commandeId);

              // Respond with success
              response.setStatus(HttpServletResponse.SC_OK);
              response.getWriter().write("{\"message\": \"Commande deleted successfully.\"}");
              break;
          }

        }catch (Exception e) {
          // Handle errors
          response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
          response.getWriter().write("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }
  }




}
