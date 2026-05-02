package com.project.foms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoCustomerException.class)
    public ResponseEntity<String> handlerNoCustomerException(NoCustomerException c) {
        return new ResponseEntity<>(c.getMessage(), HttpStatus.NOT_FOUND);
    }

    // example in ResturantServiceImp class.
    // One time if i will throw exception using no need making custom exception
    // class --.ElseThrow(()-> new RuntimeException("-----"));
    // @ExceptionHandler(RuntimeException.class)
    // public ResponseEntity<String> handlerRuntimeException(RuntimeException c){
    // return new ResponseEntity<>(c.getMessage(),HttpStatus.NOT_FOUND);
    // }

}
