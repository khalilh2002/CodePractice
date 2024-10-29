package com.tp.t10.metier;

import com.tp.t10.dao.EmployeRepository;
import com.tp.t10.entities.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeMetierImp implements EmployeMetier {

  @Autowired
  private EmployeRepository employeRepository;


  @Override
  public Employe saveEmploye(Employe employe) {
    return employeRepository.save(employe);
  }

  @Override
  public List<Employe> listEmploye() {
    return employeRepository.findAll();
  }
}
