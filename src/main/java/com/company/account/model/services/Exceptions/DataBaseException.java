package com.company.account.model.services.Exceptions;

public class DataBaseException extends RuntimeException {
    public DataBaseException(String message){
        super(message);
    }
}
