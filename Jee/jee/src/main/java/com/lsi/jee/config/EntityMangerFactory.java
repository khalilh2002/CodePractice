package com.lsi.jee.config;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

@ApplicationScoped
public class EntityMangerFactory {

  @Produces
  private EntityManagerFactory entityManagerFactory =
    Persistence.createEntityManagerFactory("default");

  @Produces
  public EntityManager produceEntityManager() {
    return entityManagerFactory.createEntityManager();
  }
}
