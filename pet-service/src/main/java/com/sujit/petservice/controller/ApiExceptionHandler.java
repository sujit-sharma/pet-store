package com.sujit.petservice.controller;

import com.sujit.petservice.model.AppError;
import com.sujit.petservice.model.EntityNotFound;
import com.sujit.petservice.model.ViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Set;

@Component
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Set<AppError>> handle(ViolationException exception) {
        return ResponseEntity.badRequest().body(exception.getErrors());
    }

    @ExceptionHandler
    public ResponseEntity<?> handle(EntityNotFound exception) {
        return ResponseEntity.notFound().build();
    }

}
