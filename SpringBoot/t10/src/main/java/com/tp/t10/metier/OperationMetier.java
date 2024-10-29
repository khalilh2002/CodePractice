package com.tp.t10.metier;

import com.tp.t10.entities.Operation;

import java.util.List;

public interface OperationMetier {

  public Operation saveOperation(Operation operation);
  public List<Operation> listOperations();
}
