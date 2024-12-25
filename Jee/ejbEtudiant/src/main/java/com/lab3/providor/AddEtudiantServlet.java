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

@WebServlet(name = "AddEtudiantServlet", value = "/add-etudiant")
public class AddEtudiantServlet extends HttpServlet {

    @EJB(lookup = "java:global/etudiantejb-1.0-SNAPSHOT/EtudiantService!ma.etudiantejb.ejbcontainer.EtudiantServiceRemote")
    private EtudiantServiceRemote etudiantService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String cne = request.getParameter("cne");
        String adresse = request.getParameter("adresse");
        String niveau = request.getParameter("niveau");

        Etudiant etudiant = new Etudiant();
        etudiant.setNom("Dupont");
        etudiant.setPrenom("Jean");
        etudiant.setCne("CNE12345");
        etudiant.setAdresse("Casablanca");
        etudiant.setNiveau("Master");
        etudiantService.addEtudiant(etudiant);

        response.getWriter().write("Étudiant ajouté avec succès !");
    }
}
