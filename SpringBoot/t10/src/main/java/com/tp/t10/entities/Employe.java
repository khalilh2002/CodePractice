package com.tp.t10.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;

@Entity
public class Employe implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long codeEmploye;
  private String nomEmploye;

  @ManyToMany
  @JoinTable(name = "EMP_GR")
  private Collection<Groupe> groupes;

  @ManyToOne
  @JoinColumn(name = "code_emp_sup")
  private Employe employeSup;


  public Employe() {
    super();
  }

  public Employe(String nomEmploye) {
    super();
    this.nomEmploye = nomEmploye;
  }

  public Long getCodeEmploye() {
    return codeEmploye;
  }

  public void setCodeEmploye(Long codeEmploye) {
    this.codeEmploye = codeEmploye;
  }

  @JsonIgnore
  public String getNomEmploye() {
    return nomEmploye;
  }

  @JsonSetter
  public void setNomEmploye(String nomEmploye) {
    this.nomEmploye = nomEmploye;
  }
  @JsonIgnore
  public Collection<Groupe> getGroupes() {
    return groupes;
  }

  public void setGroupes(Collection<Groupe> groupes) {
    this.groupes = groupes;
  }

  public Employe getEmployeSup() {
    return employeSup;
  }

  public void setEmployeSup(Employe employeSup) {
    this.employeSup = employeSup;
  }
}
