package com.lsi.jee.dao;

import com.lsi.jee.entity.Client;
import com.lsi.jee.repository.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;


import java.util.List;

@ApplicationScoped
public class ClientDao implements ClientRepository {


  @Inject
  private EntityManager entityManager;



  @Override
  @Transactional
  public void save(Client client) {
      entityManager.getTransaction().begin();
      entityManager.persist(client);
      entityManager.getTransaction().commit();
  }

  @Override
  public Client findByClientId(Long clientId) {
    return entityManager.find(Client.class, clientId);
  }

  @Override
  public List<Client> findAll() {
    return entityManager.createQuery("from Client", Client.class).getResultList();
  }

  @Override
  @Transactional
  public void delete(Client client) {
    entityManager.getTransaction().begin();
    entityManager.remove(client);
    entityManager.getTransaction().commit();

  }


}
