package com.sapient.asde.batch5.vehicleservice.service;

import org.springframework.http.HttpStatus;

public class ServiceException extends Exception {

  private final HttpStatus status;

  public ServiceException() {
    this.status = null;
  }

  public ServiceException(HttpStatus status) {
    super();
    this.status = status;
  }

  public ServiceException(String message) {
    super(message);
    this.status = null;
  }

  public ServiceException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }

  public ServiceException(Throwable cause) {
    super(cause);
    this.status = null;
  }

  public ServiceException(Throwable cause, HttpStatus status) {
    super(cause);
    this.status = status;
  }

  public ServiceException(String message, Throwable cause) {
    super(message, cause);
    this.status = null;
  }

  public ServiceException(String message, Throwable cause, HttpStatus status) {
    super(message, cause);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
