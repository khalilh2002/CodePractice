package com.lsi.jee.dao;

import com.lsi.jee.entity.Client;
import com.lsi.jee.repository.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Inject;
import jakarta.persistence.*;


import java.util.List;
@ApplicationScoped
@Default
public class ClientDao implements ClientRepository {

  @Inject
  private EntityManager entityManager;

  @Override
  public void save(Client client) {
    entityManager.getTransaction().begin();
    if (client.getId() == null) {
      entityManager.persist(client);
    } else {
      entityManager.merge(client);
    }
    entityManager.getTransaction().commit();
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
  public void delete(Client client) {
    entityManager.getTransaction().begin();
    entityManager.remove(entityManager.contains(client) ? client : entityManager.merge(client));
    entityManager.getTransaction().begin();
  }
}
