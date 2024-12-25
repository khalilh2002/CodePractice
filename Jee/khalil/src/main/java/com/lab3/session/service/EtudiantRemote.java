package com.lab3.session.service;

import com.lab3.domain.persistance.Etudiant;
import jakarta.ejb.Remote;
import java.util.List;

@Remote
public interface EtudiantRemote {

  void createEtudiant(Etudiant etudiant);

  Etudiant findEtudiantById(int id);

  List<Etudiant> getAllEtudiants();

  void updateEtudiant(Etudiant etudiant);

  void deleteEtudiant(int id);
}
