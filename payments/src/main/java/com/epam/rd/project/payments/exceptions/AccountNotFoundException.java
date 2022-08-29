package com.epam.rd.project.payments.exceptions;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(String message){
        super(message);
    }

}

