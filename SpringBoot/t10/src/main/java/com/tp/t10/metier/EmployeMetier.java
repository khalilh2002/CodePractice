package com.tp.t10.metier;

import com.tp.t10.entities.Employe;

import java.util.List;

public interface EmployeMetier {
  public Employe saveEmploye(Employe employe);
  public List<Employe> listEmploye();
}
