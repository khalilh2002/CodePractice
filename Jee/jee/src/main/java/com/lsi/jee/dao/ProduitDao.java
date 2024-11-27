package com.lsi.jee.dao;

import com.lsi.jee.entity.Produit;
import com.lsi.jee.repository.ProduitRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;


@ApplicationScoped
public class ProduitDao implements ProduitRepository {

  @Inject
  private EntityManager entityManager;

  @Override
  @Transactional
  public void save(Produit produit) {
    if (entityManager.find(Produit.class , produit.getId()) == null) {
      // Persist a new product
      entityManager.persist(produit);
    } else {
      // Merge an existing product
      entityManager.merge(produit);
    }
  }

  @Override
  public Produit findByProduitId(Long produitId) {
    // Find product by its primary key
    return entityManager.find(Produit.class, produitId);
  }

  @Override
  public List<Produit> findAll() {
    // Retrieve all products using JPQL
    return entityManager.createQuery("FROM Produit ", Produit.class).getResultList();
  }

  @Override
  @Transactional
  public void delete(Produit produit) {
    entityManager.remove(produit);
  }
}
