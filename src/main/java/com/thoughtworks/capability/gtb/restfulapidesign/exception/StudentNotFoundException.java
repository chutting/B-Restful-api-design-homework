package com.thoughtworks.capability.gtb.restfulapidesign.exception;

public class StudentNotFoundException extends RuntimeException {
  public StudentNotFoundException(String message) {
    super(message);
  }
}
