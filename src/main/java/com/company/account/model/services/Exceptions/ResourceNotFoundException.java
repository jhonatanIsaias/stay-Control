package com.company.account.model.services.Exceptions;

public class ResourceNotFoundException extends RuntimeException{
        public ResourceNotFoundException(Object id){
            super("Resource not found id" + id);
        }
}
