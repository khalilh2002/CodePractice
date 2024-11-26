package com.lsi.jee.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/commande/*")
public class CommandeController extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();


    String pathvalue = request.getPathInfo();
    out.println(pathvalue);
    if (pathvalue==null || pathvalue.equals("/")) {
      request.getRequestDispatcher("/WEB-INF/view/commande/list.jsp").forward(request, response);
    }else if (pathvalue.startsWith("/")){
      String type = pathvalue.substring(1);
      switch (type){
        case "list":
          request.getRequestDispatcher("/WEB-INF/view/commande/list.jsp").forward(request, response);
          break;
      }
    }else {
      out.println("path is wrong : "+pathvalue);
    }
  }
}
