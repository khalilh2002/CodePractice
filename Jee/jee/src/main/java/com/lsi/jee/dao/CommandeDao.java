package com.lsi.jee.dao;

import com.lsi.jee.entity.Commande;
import com.lsi.jee.repository.CommandeRepository;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

public class CommandeDao implements CommandeRepository {
  @Inject
  private EntityManager entityManager;

  @Override
  @Transactional
  public void save(Commande command) {
    entityManager.persist(command);
  }

  @Override
  public Commande findByCommandId(Long commandId) {
    return entityManager.find(Commande.class , commandId);
  }

  @Override
  public List<Commande> findAll() {
    return entityManager.createQuery("from Commande", Commande.class).getResultList();
  }

  @Override
  @Transactional
  public void delete(Commande command) {
    entityManager.remove(command);
  }
}
