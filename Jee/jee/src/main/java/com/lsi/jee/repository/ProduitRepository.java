package com.lsi.jee.repository;

import com.lsi.jee.entity.Produit;

import java.util.List;

public interface ProduitRepository {
  void save(Produit produit);
  Produit findByProduitId(Long produitId);
  List<Produit> findAll();
  void delete(Produit produit);
}
