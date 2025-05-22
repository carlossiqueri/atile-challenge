package com.atile_challenge.api.exceptions;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationException {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException exc){

        Map<String, String> fieldErrors = new LinkedHashMap<>();
        exc.getBindingResult().getFieldErrors().forEach(error ->
            fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("message", "Validation failed");
        errorBody.put("fields", fieldErrors);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("error", errorBody);
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
