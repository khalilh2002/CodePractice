package com.lsi.jee.service;

import com.lsi.jee.entity.Produit;
import com.lsi.jee.repository.ProduitRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;

import java.math.BigDecimal;
import java.util.List;

@Log
@ApplicationScoped
@Transactional
public class ProduitService {
  @Inject
  private ProduitRepository produitRepository;


  public Produit getProduit(Long id){
    return produitRepository.findByProduitId(id);
  }

  public List<Produit> getAllProduits(){
    return produitRepository.findAll();
  }

  public void addProduit(String nomProduit , BigDecimal prix){
    Produit produit = new Produit();
    produit.setNomProduit(nomProduit);
    produit.setPrix(prix);
    produitRepository.save(produit);
  }

  public void deleteProduit(Long id) {
    Produit produit = produitRepository.findByProduitId(id);
    if (produit != null) {
      produitRepository.delete(produit);
    } else {
      throw new IllegalArgumentException("Produit not found for ID: " + id);
    }
  }

  public void editProduit(Long id, String newNomProduit, BigDecimal newPrix) {
    Produit produit = produitRepository.findByProduitId(id);
    if (produit != null) {
      produit.setNomProduit(newNomProduit);
      produit.setPrix(newPrix);
      produitRepository.save(produit); // Save the updated product
    } else {
      throw new IllegalArgumentException("Produit not found for ID: " + id);
    }
  }


}
