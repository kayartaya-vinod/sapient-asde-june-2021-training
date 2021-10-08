package com.sapient.asde.batch5.customerservice.service;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception {

  public ServiceException(String string, HttpStatus noContent) {
  }

  public ServiceException(String message) {
    super(message);
  }

  public ServiceException(Throwable cause) {
    super(cause);
  }


}
