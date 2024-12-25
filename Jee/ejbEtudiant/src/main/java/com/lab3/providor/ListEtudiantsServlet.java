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
import java.util.List;

@WebServlet(name = "ListEtudiantsServlet", value = "/list-etudiants")
public class ListEtudiantsServlet extends HttpServlet {

    @EJB(lookup = "java:global/etudiantejb-1.0-SNAPSHOT/EtudiantService!ma.etudiantejb.ejbcontainer.EtudiantServiceRemote")
    private EtudiantServiceRemote etudiantService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Etudiant> etudiants = etudiantService.findAllEtudiants();

        response.setContentType("text/html");
        StringBuilder result = new StringBuilder("<h1>Liste des Étudiants :</h1><ul>");
        for (Etudiant etudiant : etudiants) {
            result.append("<li>").append(etudiant).append("</li>");
        }
        result.append("</ul>");
        response.getWriter().write(result.toString());
    }
}
