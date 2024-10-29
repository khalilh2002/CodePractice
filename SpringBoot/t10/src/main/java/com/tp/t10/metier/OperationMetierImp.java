package com.tp.t10.metier;

import com.tp.t10.dao.OperationRepository;
import com.tp.t10.entities.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OperationMetierImp implements OperationMetier {
  @Autowired
  private OperationRepository operationRepository;

  @Override
  public Operation saveOperation(Operation operation) {
    return operationRepository.save(operation);
  }

  @Override
  public List<Operation> listOperations() {
    return operationRepository.findAll();
  }
}
