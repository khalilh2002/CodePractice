package com.lsi.jee.service;
import com.lsi.jee.entity.Client;
import com.lsi.jee.repository.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.java.Log;
import java.util.List;

@Log
@ApplicationScoped
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

}
