package com.lsi.lab2.exception;

public class UserNotFoundException extends Exception {
  public UserNotFoundException(String id) {
    super("user id :"+id+" not found");
  }
}
