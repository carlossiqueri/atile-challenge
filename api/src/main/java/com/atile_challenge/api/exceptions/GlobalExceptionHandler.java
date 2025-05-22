package com.atile_challenge.api.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException exception){
        Map<String, String> errorDetail = new HashMap<>();
        errorDetail.put("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetail);
    }

    @ExceptionHandler({ArgumentException.class})
    public ResponseEntity<Map<String, String>> handleAgurmentException(ArgumentException exception){
        Map<String, String> errorDetail = new HashMap<>();
        errorDetail.put("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetail);
    }

}
