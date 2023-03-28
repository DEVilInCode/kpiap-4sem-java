package com.javalabs.lab1TSR.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogBuilder;
import org.apache.logging.log4j.LogManager;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NumberBoundsException extends RuntimeException{

    private final Logger logger = LogManager.getLogger(NumberBoundsException.class);

    public NumberBoundsException(){
        super();
        logger.warn("Bad Request");
    }

    public NumberBoundsException(String message, Throwable cause){
        super(message, cause);
        logger.warn(message);
    }

    public NumberBoundsException(String message){
        super(message);
        logger.warn(message);
    }

    public NumberBoundsException(Throwable cause){
        super(cause);
        logger.warn(cause.getMessage());
    }

}
