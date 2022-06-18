package com.example.javamicroservicii.exceptions;

public class NoAdoptionFound extends RuntimeException{
    public NoAdoptionFound(String ExceptionMessage){
        super(ExceptionMessage);
    }

}
