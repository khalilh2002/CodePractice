package com.tp.t10.metier;

import com.tp.t10.dao.CompteRepository;
import com.tp.t10.entities.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompteMetierImp implements CompteMetier {

  @Autowired
  private CompteRepository compteRepository;


  @Override
  public Compte saveCompte(Compte compte) {
    return compteRepository.save(compte);
  }

  @Override
  public List<Compte> listComptes() {
    return compteRepository.findAll();
  }
}
