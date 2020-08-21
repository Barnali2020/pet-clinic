package com.barnali.petclinic.exceptions;

/**
 * Created by barnali on Aug, 2020
 */
public class NotFoundException extends RuntimeException{

    String message;

    public NotFoundException() {}

    public NotFoundException(String message) {
        this.message = message;
    }

    public NotFoundException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

}
