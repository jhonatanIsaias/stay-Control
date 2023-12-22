package com.company.account.model.services.Exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error ="Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandError err = new StandError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<StandError> database(DataBaseException e, HttpServletRequest request){
        String error ="Resource not found";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandError err = new StandError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(DayLimitException.class)
    public ResponseEntity<StandError> limit(DayLimitException e, HttpServletRequest request){
        String error ="day limit expire";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandError err = new StandError(Instant.now(),status.value(),error,e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
