package com.enigmacamp.restapidemo.Exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("Data not found");
    }

    public NotFoundException(String message){
        super(message);
    }


}
