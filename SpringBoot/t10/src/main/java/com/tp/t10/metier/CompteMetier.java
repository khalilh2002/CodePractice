package com.tp.t10.metier;

import com.tp.t10.entities.Compte;

import java.util.List;

public interface CompteMetier {
  public Compte saveCompte(Compte compte);
  public List<Compte> listComptes();
}
