package com.sapient.asde.batch5.customerservice.repository;

public class RepositoryException extends Exception {

  public RepositoryException() {
  }

  public RepositoryException(String message) {
    super(message);
  }

  public RepositoryException(Throwable cause) {
    super(cause);
  }

}
