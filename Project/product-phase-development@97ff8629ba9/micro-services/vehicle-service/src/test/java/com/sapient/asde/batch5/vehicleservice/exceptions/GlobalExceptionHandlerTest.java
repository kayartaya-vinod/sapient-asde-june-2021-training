package com.sapient.asde.batch5.vehicleservice.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import com.sapient.asde.batch5.vehicleservice.service.ServiceException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class GlobalExceptionHandlerTest {
  @Autowired
  private GlobalExceptionHandler geh;

  private static final String SUCCESS = "success";
  private static final String MESSAGE = "message";

  @Test
  void shouldReturnMapObject() {
    Map<String, Object> expected = new HashMap<>();
    expected.put(SUCCESS, false);
    expected.put(MESSAGE, "Exception Catched");
    Map<String, Object> actual = geh.handleOtherConflict(new Exception("Exception Catched"));
    assertEquals(expected, actual);
  }

  @Test
  void shouldReturnResponseEntity() {
    Map<String, Object> response = new HashMap<>();
    response.put(SUCCESS, false);
    response.put(MESSAGE, "ServiceException Catched");
    ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    ResponseEntity<Object> actual = geh.handleServiceConflict(new ServiceException("ServiceException Catched"));
    assertEquals(expected, actual);

    expected = ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    actual = geh.handleServiceConflict(new ServiceException("ServiceException Catched", HttpStatus.NOT_FOUND));
    assertEquals(expected, actual);
  }
}