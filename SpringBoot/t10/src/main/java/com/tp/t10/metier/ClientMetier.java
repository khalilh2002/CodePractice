package com.tp.t10.metier;

import com.tp.t10.entities.Client;

import java.util.List;

public interface ClientMetier {
  public Client saveClient(Client client);
  public List<Client> listClient();
}
