package com.tp.t10.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class Operation implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long numeroOperacion;
  private Date dateOperation ;
  private double montant ;

  @ManyToOne
  @JoinColumn(name="CODE_CPTE")
  private Compte compte;

  @ManyToOne
  @JoinColumn(name="CODE_EMP")
  private Employe employe;

  public Operation(Date dateOperation, double montant) {
    super();
    this.dateOperation = dateOperation;
    this.montant = montant;
  }

  public Operation() {
    super();
  }

  public Employe getEmploye() {
    return employe;
  }

  public void setEmploye(Employe employe) {
    this.employe = employe;
  }

  public Compte getCompte() {
    return compte;
  }

  public void setCompte(Compte compte) {
    this.compte = compte;
  }

  public double getMontant() {
    return montant;
  }

  public void setMontant(double montant) {
    this.montant = montant;
  }

  public Date getDateOperation() {
    return dateOperation;
  }

  public void setDateOperation(Date dateOperation) {
    this.dateOperation = dateOperation;
  }

  public Long getNumeroOperacion() {
    return numeroOperacion;
  }

  public void setNumeroOperacion(Long numeroOperacion) {
    this.numeroOperacion = numeroOperacion;
  }
}
