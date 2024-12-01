package com.lsi.lab2.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class EntityManagerProvider {

  private final EntityManagerFactory entityManagerFactory;

  // Initialize the EntityManagerFactory at application startup
  public EntityManagerProvider() {
    this.entityManagerFactory = Persistence.createEntityManagerFactory("default");
  }

  // Produce an EntityManager for each request or transaction
  @Produces
  @RequestScoped
  public EntityManager produceEntityManager() {
    return entityManagerFactory.createEntityManager();
  }

  // Dispose of the EntityManager at the end of the request or transaction
  public void closeEntityManager(@Disposes EntityManager entityManager) {
    if (entityManager.isOpen()) {
      entityManager.close();
    }
  }
}
