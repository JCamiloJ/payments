package com.epam.rd.project.payments.exceptions;

public class CustomerCreationException extends RuntimeException{

    public CustomerCreationException(String message){
        super(message);
    }

}
