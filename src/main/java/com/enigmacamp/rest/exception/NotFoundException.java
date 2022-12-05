package com.enigmacamp.rest.exception;

public class NotFoundException extends RuntimeException{

    public NotFoundException() {
        super("Data is not Found");
    }
    public NotFoundException(String message){
        super(message);
    }
}
