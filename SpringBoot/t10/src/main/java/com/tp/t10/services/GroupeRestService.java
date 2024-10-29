package com.tp.t10.services;

import com.tp.t10.entities.Groupe;
import com.tp.t10.metier.GroupMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupeRestService {
  @Autowired
  private GroupMetier groupMetier;

  @PostMapping(value = "/groupes")
  public Groupe addGroupe(@RequestBody Groupe groupe) {
    return groupMetier.saveGroup(groupe);
  }

  @GetMapping(value = "/groupes")
  public List<Groupe> getAllGroupes() {
    return groupMetier.listGroupes();
  }

}
