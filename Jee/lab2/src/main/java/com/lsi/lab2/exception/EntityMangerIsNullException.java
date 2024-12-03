package com.lsi.lab2.exception;

public class EntityMangerIsNullException extends RuntimeException {
  public EntityMangerIsNullException() {
    super("entitymanger is null");
  }
}
