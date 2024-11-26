package com.lsi.jee.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Commande implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(nullable = false, unique = true)
  private Long id;

  @Column(nullable = false)
  private Integer quantite;

  @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
  @JoinColumn(name = "client_id", nullable = false)
  private Client client;

  @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(
    name = "commande_produit",
    joinColumns = @JoinColumn(name = "commande_id"),
    inverseJoinColumns = @JoinColumn(name = "produit_id")
  )
  private List<Produit> produits;
}
