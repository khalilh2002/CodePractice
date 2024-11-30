package com.lsi.jee.repository;

import com.lsi.jee.entity.Client;

import java.util.List;


public interface ClientRepository {

  void save(Client client);
  Client findByClientId(Long clientId);
  List<Client> findAll();
  void delete(Client client);

}
