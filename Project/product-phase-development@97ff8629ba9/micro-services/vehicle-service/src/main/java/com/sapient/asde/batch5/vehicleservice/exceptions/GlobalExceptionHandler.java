package com.sapient.asde.batch5.vehicleservice.exceptions;

import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String SUCCESS = "success";
  private static final String MESSAGE = "message";

  @ExceptionHandler(value = { ServiceException.class })
  public ResponseEntity<Object> handleServiceConflict(ServiceException ex) {
    Map<String, Object> response = new HashMap<>();
    response.put(SUCCESS, false);
    response.put(MESSAGE, ex.getMessage());
    HttpStatus status = ex.getStatus();

    if (status == null) {
      status = HttpStatus.BAD_REQUEST;
    }

    return ResponseEntity.status(status).body(response);
  }

  @ExceptionHandler(value = { Exception.class })
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public Map<String, Object> handleOtherConflict(Exception ex) {
    Map<String, Object> response = new HashMap<>();
    response.put(SUCCESS, false);
    response.put(MESSAGE, ex.getMessage());
    return response;
  }

}