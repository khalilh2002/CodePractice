package com.tp.t10.metier;

import com.tp.t10.entities.Groupe;

import java.util.List;

public interface GroupMetier {

  public Groupe saveGroup(Groupe groupe);
  public List<Groupe> listGroupes();
}
