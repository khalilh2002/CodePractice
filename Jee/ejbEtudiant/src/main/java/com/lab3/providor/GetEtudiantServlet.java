package com.lab3.providor;


import com.lab3.ejbetudiant.Etudiant;
import com.lab3.ejbetudiant.EtudiantServiceRemote;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "GetEtudiantServlet", value = "/get-etudiant")
public class GetEtudiantServlet extends HttpServlet {

    @EJB(lookup = "java:global/etudiantejb-1.0-SNAPSHOT/EtudiantService!ma.etudiantejb.ejbcontainer.EtudiantServiceRemote")
    private EtudiantServiceRemote etudiantService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Etudiant etudiant = etudiantService.findEtudiantById(id);

        response.setContentType("text/html");
        response.getWriter().write(etudiant != null ? etudiant.toString() : "Étudiant non trouvé !");
    }
}
