package com.tp.t10.metier;

import com.tp.t10.dao.ClientRepository;
import com.tp.t10.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientMetierImp implements ClientMetier {

  @Autowired
  private ClientRepository clientRepository;


  @Override
  public Client saveClient(Client client) {
    return clientRepository.save(client);
  }

  @Override
  public List<Client> listClient() {
    return clientRepository.findAll();
  }
}
