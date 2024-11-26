package com.lsi.jee.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Client implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique = true, nullable = false)
  private Long id;

  @Column(unique = true, nullable = false)
  private String nom;

  @Column(unique = true, nullable = false)
  private String prenom;

  @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Commande> commandes;
}
