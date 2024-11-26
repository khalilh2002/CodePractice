package com.lsi.jee.service;

import com.lsi.jee.entity.Client;
import com.lsi.jee.entity.Commande;
import com.lsi.jee.entity.Produit;
import com.lsi.jee.repository.ClientRepository;
import com.lsi.jee.repository.CommandeRepository;
import com.lsi.jee.repository.ProduitRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Log
@ApplicationScoped
public class CommandeService {

  @Inject
  private CommandeRepository commandeRepository;

  @Inject
  private ProduitRepository produitRepository;

  @Inject
  private ClientRepository clientRepository;






  public List<Commande> getAllCommandes(){
    return commandeRepository.findAll();
  }

  public Commande getCommandeById(Long id){
    return commandeRepository.findByCommandId(id);
  }

  public void deleteCommandeById(Long id){
    Commande commande = commandeRepository.findByCommandId(id);
    commandeRepository.delete(commande);
  }
  public void addCommande(Long clientId , List<Long> produitIdList){
    Client client = clientRepository.findByClientId(clientId);
    List<Produit> produits = new ArrayList<>();
    for (Long produitId : produitIdList){
      Produit produit = produitRepository.findByProduitId(produitId);
      produits.add(produit);
    }
    Commande commande = new Commande();
    commande.setProduits(produits);
    commande.setQuantite(produits.size());
    commande.setClient(client);
    commandeRepository.save(commande);
  }

}
