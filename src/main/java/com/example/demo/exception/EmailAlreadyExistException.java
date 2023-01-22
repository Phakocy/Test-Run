package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EmailAlreadyExistException extends RuntimeException{

    public String message;  // Check ResourceNotFoundException class to customize message

    public EmailAlreadyExistException(String message){
        super(message);
    }

}
