package com.javalabs.lab1TSR.counter;

import org.springframework.stereotype.Component;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RequestCounter {

    private final AtomicInteger count;
    private static final Logger logger = LogManager.getLogger(RequestCounter.class);

    public RequestCounter(){
        count = new AtomicInteger(0);
    }

    public synchronized void increment(){
        count.incrementAndGet();
    }

    public synchronized int count(){
        return count.get();
    }

}
