package com.lsi.jee.service;
import com.lsi.jee.entity.Client;
import com.lsi.jee.repository.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;
import java.util.List;

@Log
@ApplicationScoped
@Transactional
public class ClientService {

  @Inject
  private ClientRepository clientRepository;

  public void test(){
    log.info("repositrot is not null");
    Client client = new Client();
    client.setNom("khalildd");
    client.setPrenom("fdsfdfdsfdssf");

    clientRepository.save(client);
  }

  public List<Client> getAllClients(){
    return clientRepository.findAll();
  }

  public Client getClient(Long id){
    return clientRepository.findByClientId(id);
  }

  public void addClient(String nom, String prenom){
    Client client = new Client();
    client.setNom(nom);
    client.setPrenom(prenom);
    clientRepository.save(client);
  }

  public void deleteClient(Long id){
    Client client = clientRepository.findByClientId(id);
    clientRepository.delete(client);
  }

  public Client getClientCommande(Long id){
    Client client = clientRepository.findByClientId(id);
    if (client != null) {
      client.getCommandes().forEach(commande->{
        commande.setQuantite(commande.getProduits().size());
      });
    }

    return client;
  }



  public void updateClient(Long id, String nom, String prenom) {
    Client client = clientRepository.findByClientId(id);
    if (client != null) {
      client.setNom(nom);
      client.setPrenom(prenom);
      clientRepository.save(client); // Save the updated client
    } else {
      throw new IllegalArgumentException("Client not found for ID: " + id);
    }
  }


}
