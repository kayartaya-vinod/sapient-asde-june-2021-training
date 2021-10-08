package com.sapient.asde.batch5.customerservice.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class GlobalExceptionTest {

  @Test
  void shouldReturnNotFound() {
    GlobalException geh = new GlobalException();
    Map<String, Object> resp = new HashMap<>();
    resp.put("success", false);
    resp.put("message", "Exception Catched");
    ResponseEntity<Object> expected = ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
    ResponseEntity<Object> actual = geh.handleConflict(new Exception("Exception Catched"));
    assertEquals(expected, actual);
  }
}
