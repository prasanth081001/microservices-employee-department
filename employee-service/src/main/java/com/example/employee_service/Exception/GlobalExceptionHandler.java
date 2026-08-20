package com.example.employee_service.Exception;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(FeignException.ServiceUnavailable.class)
    public ResponseEntity<Map<String, Object>> handleServiceUnavailable(
            FeignException.ServiceUnavailable exception) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", 503);
        response.put("error", "Service Unavailable");
        response.put("message", "Department Service is currently unavailable");

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(response);
    }
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleEmployeeNotFound(
            EmployeeNotFoundException exception) {

        Map<String, Object> response = new HashMap<>();

        response.put("status", 404);
        response.put("error", "Not Found");
        response.put("message", exception.getMessage());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }
}
