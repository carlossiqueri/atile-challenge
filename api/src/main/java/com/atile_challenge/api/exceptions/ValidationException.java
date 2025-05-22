package com.atile_challenge.api.exceptions;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@RestControllerAdvice
public class ValidationException {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException exception){

        Map<String, String> fieldErrors = new LinkedHashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error ->
            fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("message", "Validation failed");
        errorBody.put("fields", fieldErrors);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("error", errorBody);
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleEnumValidation(HttpMessageNotReadableException exception) {
        Throwable cause = exception.getCause();
        Map<String, Object> errorBody = new LinkedHashMap<>();
        errorBody.put("message", "Malformed request");

        if (cause instanceof InvalidFormatException invalidFormationException) {
            String field = invalidFormationException.getPath().get(0).getFieldName();
            String invalidValue = invalidFormationException.getValue().toString();
            String expectedType = invalidFormationException.getTargetType().getSimpleName();

            errorBody.put("message", String.format("Invalid value '%s' for field '%s'. Expected a valid %s.", invalidValue, field, expectedType));
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("error", errorBody);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
