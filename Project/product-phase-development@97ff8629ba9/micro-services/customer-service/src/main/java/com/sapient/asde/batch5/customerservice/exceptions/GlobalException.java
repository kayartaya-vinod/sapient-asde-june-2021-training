package com.sapient.asde.batch5.customerservice.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleConflict(Exception ex) {
        Map<String, Object> resp = new HashMap<>();
        resp.put("success", false);
        resp.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
    }
}
