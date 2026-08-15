package com.pm.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleArgumentNotValidException(
            MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(),fieldError.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<Map<String,String>> handleEmailAlreadyExist
            (EmailAlreadyExistException ex){
        Map<String,String> error = new HashMap<>();
        error.put("message","email already exist exception");
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(PatientNotExistException.class)
    public  ResponseEntity<Map<String,String>> handlePatientNotExist(PatientNotExistException ex){
        Map<String, String> error = new HashMap<>();
        error.put("message","patient does not exist at this id");
        return ResponseEntity.badRequest().body(error);
    }
}
