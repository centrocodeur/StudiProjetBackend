package com.marien.backend.exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException(String message){
        super(message);
    }

}
