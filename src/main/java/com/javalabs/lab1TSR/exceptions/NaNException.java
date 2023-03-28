package com.javalabs.lab1TSR.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogBuilder;
import org.apache.logging.log4j.LogManager;


@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class NaNException extends RuntimeException {

    private final Logger logger = LogManager.getLogger(NaNException.class);

    public NaNException(){
        super();
        logger.warn("Internal Server Error");
    }

    public NaNException(String message, Throwable cause){
        super(message, cause);
        logger.warn(message);
    }

    public NaNException(String message){
        super(message);
        logger.warn(message);
    }

    public NaNException(Throwable cause){
        super(cause);
        logger.warn(cause.getMessage());
    }

}
