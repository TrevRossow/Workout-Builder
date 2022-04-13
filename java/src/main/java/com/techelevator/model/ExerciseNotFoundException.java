package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND, reason = "Exercise Not Found.")
public class ExerciseNotFoundException extends RuntimeException {
    public ExerciseNotFoundException(String msg){
        super(msg);

    }
    public ExerciseNotFoundException(String msg, Throwable t){
        super(msg,t);
    }
    }
