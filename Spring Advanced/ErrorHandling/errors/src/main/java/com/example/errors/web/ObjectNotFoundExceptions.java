package com.example.errors.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "Object not found!")
public class ObjectNotFoundExceptions extends RuntimeException {

    public ObjectNotFoundExceptions(String message) {
        super(message);
    }
}
