package com.myblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //specific exception
    @ExceptionHandler(ResourceNotFoundException.class)
    //this class object when gets created then this method will handle the exception
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
            ResourceNotFoundException exception, WebRequest webRequest) { //if exception occurs this is the information we need to display
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));


        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    //global exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
            Exception exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), webRequest.getDescription(false));


        return new ResponseEntity<>(errorDetails, HttpStatus.CONFLICT);  //if we give/ "mike"  instead of /9000 wrong id

    }
}
