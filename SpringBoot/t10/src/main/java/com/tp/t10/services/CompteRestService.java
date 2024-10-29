package com.tp.t10.services;

import com.tp.t10.entities.Compte;
import com.tp.t10.metier.CompteMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompteRestService {

  @Autowired
  private CompteMetier compteMetier;

  @PostMapping(value = "/comptes")
  public Compte addCompte(@RequestBody Compte compte) {
    return compteMetier.saveCompte(compte);
  }

  @GetMapping(value = "/comptes")
  public List<Compte> getComptes() {
    return compteMetier.listComptes();
  }

}
