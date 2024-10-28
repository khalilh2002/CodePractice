package com.tp.t10.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte {

  private double decouvert;

  public CompteCourant() {
    super();
  }
  public CompteCourant(String codeCompte , Date dateCreation , Double solde, double decouvert){
    super(codeCompte , dateCreation , solde);
    this.decouvert = decouvert;
  }

  public double getDecouvert() {
    return decouvert;
  }

  public void setDecouvert(double decouvert) {
    this.decouvert = decouvert;
  }
}
