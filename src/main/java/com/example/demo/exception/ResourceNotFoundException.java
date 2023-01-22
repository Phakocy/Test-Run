package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String resourceName; // The class e.g. User, Account
    private String fieldName;   // The name of the field in the class e.g. userId, email, accountNumber
    private Long fieldValue;  //  The value of the field e.g. id = 10

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue){
        super(String.format("%s not found with %s : %s", resourceName, fieldName,fieldValue )); // We can just pass super(message) to get the message from the stack trace
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }


}
