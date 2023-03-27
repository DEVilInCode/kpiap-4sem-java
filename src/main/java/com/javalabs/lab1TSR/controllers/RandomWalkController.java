package com.javalabs.lab1TSR.controllers;

import com.javalabs.lab1TSR.counter.RequestCounter;
import com.javalabs.lab1TSR.exceptions.NaNException;
import com.javalabs.lab1TSR.exceptions.NumberBoundsException;
import com.javalabs.lab1TSR.records.RandomWalk;
import com.javalabs.lab1TSR.repository.RandomWalkRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RandomWalkController {

    private static final Logger logger = LogManager.getLogger(RandomWalk.class);
    private final RandomWalkRepository repository;
    private final RequestCounter counter;

    public RandomWalkController(RandomWalkRepository repository, RequestCounter counter){
        this.repository = repository;
        this.counter = counter;
    }

    @GetMapping("/walk")
    public RandomWalk randomWalk(@RequestParam(value = "num", defaultValue = "5") String num){

        int number;

        counter.increment();

        if(num.matches("[+-]?\\d+")) {
            number = Integer.parseInt(num);

            if(number < 1 || number > 10)
                throw new NumberBoundsException("Number not in bounds");
        }
        else
            throw new NaNException("NaN");

        final RandomWalk randomWalk = repository.getByCache(number);

        logger.info(String.format("Random walk number: %d", randomWalk.randomWalk));

        return randomWalk;
    }
}