package com.lsi.jee.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
public class Produit implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(unique = true, nullable = false)
  private Long id;

  @Column(nullable = false, length = 100) // Added length constraint for the name
  private String nomProduit;

  @Column(nullable = false, precision = 10, scale = 2) // Using BigDecimal for precision
  private BigDecimal prix;
}
