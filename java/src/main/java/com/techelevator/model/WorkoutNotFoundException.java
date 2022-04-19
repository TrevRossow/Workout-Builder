package com.techelevator.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.NOT_FOUND, reason = "Workout Not Found.")
public class WorkoutNotFoundException extends RuntimeException {
    public WorkoutNotFoundException(String msg) {
        super(msg);

    }
    public WorkoutNotFoundException(String msg, Throwable t) {
        super(msg, t);
        }
    }

