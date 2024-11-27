package com.lsi.jee.service;

import com.lsi.jee.entity.Produit;
import com.lsi.jee.repository.CommandeRepository;
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
}
