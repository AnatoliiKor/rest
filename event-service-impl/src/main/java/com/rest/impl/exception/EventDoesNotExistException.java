package com.rest.impl.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventDoesNotExistException extends RuntimeException{
    public EventDoesNotExistException(long id){
        super("Event wit id=" + id + " does not exist.");
    }
}