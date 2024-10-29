package com.tp.t10.metier;

import com.tp.t10.dao.GroupeRepository;
import com.tp.t10.entities.Groupe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class GroupMetierImp implements GroupMetier {

  @Autowired
  private GroupeRepository groupeRepository;

  @Override
  public Groupe saveGroup(Groupe groupe) {
    return groupeRepository.save(groupe);
  }

  @Override
  public List<Groupe> listGroupes() {
    return groupeRepository.findAll();
  }
}
