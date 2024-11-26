package com.lsi.jee.repository;

import com.lsi.jee.entity.Commande;

import java.util.List;

public interface CommandeRepository {
  void save(Commande command);
  Commande findByCommandId(Long commandId);
  List<Commande> findAll();
  void delete(Commande command);
}
