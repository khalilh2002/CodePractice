package com.lab3.domain.persistance;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Etudiant {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_etudiant")
  private Long id;

  @Column(name = "nom", nullable = false)
  private String nom;

  @Column(name = "prénom", nullable = false)
  private String prenom;

  @Column(name = "cne", nullable = false, unique = true)
  private String cne;

  @Column(name = "adresse")
  private String adresse;

  @Column(name = "niveau")
  private String niveau;
}
