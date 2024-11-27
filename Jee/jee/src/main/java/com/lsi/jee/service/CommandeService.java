package com.lsi.jee.service;

import com.lsi.jee.entity.Client;
import com.lsi.jee.entity.Commande;
import com.lsi.jee.entity.Produit;
import com.lsi.jee.repository.ClientRepository;
import com.lsi.jee.repository.CommandeRepository;
import com.lsi.jee.repository.ProduitRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transactional;
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

  @Inject
  private EntityManager entityManager;

public void test(){
  EntityManager em = Persistence.createEntityManagerFactory("default").createEntityManager();

}



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



  public void addCommande(Long clientId, List<Long> produitIdList) {
    // Fetch the client to ensure it's in the current persistence context
    Client client = new Client();
    client.setId(clientId);
    if (client == null) {
      throw new IllegalArgumentException("Client not found with ID: " + clientId);
    }

    // Fetch products and ensure they're valid
    List<Produit> produits = new ArrayList<>();
    for (Long produitId : produitIdList) {
      Produit produit = produitRepository.findByProduitId(produitId);
      if (produit == null) {
        throw new IllegalArgumentException("Produit not found with ID: " + produitId);
      }
      produits.add(produit);
    }

    // Create and save the commande
    Commande commande = new Commande();
    commande.setClient(client); // Client is attached since it was retrieved in the same transaction
    commande.setProduits(produits);
    commande.setQuantite(produits.size());

    commandeRepository.save(commande); // Save should now work
  }


}
