package com.lab3.providor;


import com.lab3.ejbetudiant.EtudiantServiceRemote;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DeleteEtudiantServlet", value = "/delete-etudiant")
public class DeleteEtudiantServlet extends HttpServlet {

    @EJB(lookup = "java:global/etudiantejb-1.0-SNAPSHOT/EtudiantService!ma.etudiantejb.ejbcontainer.EtudiantServiceRemote")
    private EtudiantServiceRemote etudiantService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        etudiantService.deleteEtudiant(id);

        response.getWriter().write("Étudiant supprimé avec succès !");
    }
}
