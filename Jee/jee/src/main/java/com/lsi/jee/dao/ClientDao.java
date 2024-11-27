package com.lsi.jee.dao;

import com.lsi.jee.entity.Client;
import com.lsi.jee.repository.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;


import java.util.List;
@ApplicationScoped
@Default
public class ClientDao implements ClientRepository {

  @Inject
  private EntityManager entityManager;

  @Override
  @Transactional
  public void save(Client client) {
    if (client.getId() == null) {
      entityManager.persist(client);
    } else {
      entityManager.merge(client);
    }
  }

  @Override
  public Client findByClientId(Long clientId) {
    return entityManager.find(Client.class, clientId); // No explicit transaction required
  }

  @Override
  public List<Client> findAll() {
    return entityManager.createQuery("from Client", Client.class).getResultList();
  }

  @Override
  @Transactional
  public void delete(Client client) {
    entityManager.remove(entityManager.contains(client) ? client : entityManager.merge(client));
  }
}
