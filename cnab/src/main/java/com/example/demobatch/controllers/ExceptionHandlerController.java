package com.example.demobatch.controllers;

import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(JobInstanceAlreadyCompleteException.class)
    public ResponseEntity<String> handleJobInstanceAlreadyCompleteException(JobInstanceAlreadyCompleteException ex) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Arquivo ja processado pelo sistema " + ex.getMessage());
    }


}
