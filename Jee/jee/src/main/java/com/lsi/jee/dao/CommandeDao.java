package com.lsi.jee.dao;

import com.lsi.jee.entity.Commande;
import com.lsi.jee.repository.CommandeRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
@ApplicationScoped
@Default
public class CommandeDao implements CommandeRepository {
  @Inject
  private EntityManager entityManager;

  @Override
  public void save(Commande command) {
    entityManager.getTransaction().begin();
    if (command.getId() == null) {
      entityManager.persist(command);
    }else {
      entityManager.merge(command);
    }
    entityManager.getTransaction().commit();
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
  public void delete(Commande command) {
    entityManager.getTransaction().begin();
    entityManager.remove(command);
    entityManager.getTransaction().commit();
  }
}
