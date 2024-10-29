package com.tp.t10.services;

import com.tp.t10.entities.Operation;
import com.tp.t10.metier.OperationMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OperationRestService {

  @Autowired
  private OperationMetier operationMetier;

  @PostMapping(value = "/operations")
  public Operation addOperation(@RequestBody Operation operation) {
    return operationMetier.saveOperation(operation);
  }

  @GetMapping (value = "/operations")
  public List<Operation> getOperations() {
    return operationMetier.listOperations();
  }
}
