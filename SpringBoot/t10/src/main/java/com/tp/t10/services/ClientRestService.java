package com.tp.t10.services;

import com.tp.t10.entities.Client;
import com.tp.t10.metier.ClientMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientRestService {


  @Autowired
  private ClientMetier clientMetier;

  @PostMapping(value = "/clients")
  public Client clients(@RequestBody Client client) {
    return clientMetier.saveClient(client);
  }


  @GetMapping(value = "/clients")
  public List<Client> getClients() {
    return clientMetier.listClient();
  }
}
