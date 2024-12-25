package com.lab3.demo;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface EtudiantRemote {
  void createEtudiant(Etudiant e);
  Etudiant findEtudiant(int id);
  List<Etudiant> findAll();
  void updateEtudiant(Etudiant e);
  void deleteEtudiant(int id);
}

