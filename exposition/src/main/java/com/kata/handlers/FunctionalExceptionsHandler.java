package com.kata.handlers;

import com.kata.exceptions.FunctionalException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class FunctionalExceptionsHandler {

    @ExceptionHandler(FunctionalException.class)
    public ResponseEntity handleFunctionalExceptions(final FunctionalException ex, WebRequest request){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
