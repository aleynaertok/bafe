package com.bafe.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IsEmptyException extends RuntimeException {

    public IsEmptyException(String message){

        super(message);
    }

}
