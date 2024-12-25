package com.lab3.ejbetudiant;

import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface EtudiantServiceRemote {
    void addEtudiant(Etudiant etudiant);
    void updateEtudiant(Etudiant etudiant);
    void deleteEtudiant(int id);
    Etudiant findEtudiantById(int id);
    List<Etudiant> findAllEtudiants();
}
