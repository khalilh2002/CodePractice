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

@WebServlet(name = "UpdateEtudiantServlet", value = "/update-etudiant")
public class UpdateEtudiantServlet extends HttpServlet {

    @EJB(lookup = "java:global/etudiantejb-1.0-SNAPSHOT/EtudiantService!ma.etudiantejb.ejbcontainer.EtudiantServiceRemote")
    private EtudiantServiceRemote etudiantService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String cne = request.getParameter("cne");
        String adresse = request.getParameter("adresse");
        String niveau = request.getParameter("niveau");

        Etudiant etudiant = new Etudiant(id, nom, prenom, cne, adresse, niveau);
        etudiantService.updateEtudiant(etudiant);

        response.getWriter().write("Étudiant mis à jour avec succès !");
    }
}
