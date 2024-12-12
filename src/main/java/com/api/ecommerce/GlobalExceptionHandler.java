package com.api.ecommerce;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
    List<String> errorMessages = ex.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(FieldError::getDefaultMessage)  // Get the default message from annotations
      .collect(Collectors.toList());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body("Validation failed: " + String.join(", ", errorMessages));
  }
}
