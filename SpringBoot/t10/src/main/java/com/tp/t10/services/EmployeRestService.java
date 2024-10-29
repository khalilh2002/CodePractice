package com.tp.t10.services;

import com.tp.t10.entities.Employe;
import com.tp.t10.metier.EmployeMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeRestService  {
  @Autowired
  private EmployeMetier empMetier;

  @PostMapping(value = "/employes")
  public Employe employee(@RequestBody Employe employe){
    return empMetier.saveEmploye(employe);
  }

  @GetMapping(value = "/employes")
  public List<Employe> getEmployes(){
    return empMetier.listEmploye();
  }
}
