package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request){
        // WebRequest is used to get client info
        // If You set web request to true, You will see something like this client=0:0:0:0:0:0:0:1 in the path"

        ErrorDetails errorDetails = new ErrorDetails( LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "USER_NOT_FOUND" );

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<ErrorDetails> handleEmailAlreadyExistException(EmailAlreadyExistException exception, WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails( LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "EMAIL_ALREADY_EXIST" );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

  //  Handling more than one exception in a single method (Here we handle General Exception and NullPointerException)

    @ExceptionHandler(value = {NullPointerException.class, Exception.class}) //Specific exception
    public ResponseEntity<ErrorDetails> handleSpecificException(Exception exception, WebRequest request){

        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false),
                "INTERNAL_SERVER_ERROR");

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
