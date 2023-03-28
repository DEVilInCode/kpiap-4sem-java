package com.javalabs.lab1TSR.records;

import com.javalabs.lab1TSR.exceptions.NaNException;
import com.javalabs.lab1TSR.exceptions.NumberBoundsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class RandomWalk {

    private static final Logger logger = LogManager.getLogger(RandomWalk.class);
    public final int randomWalk;

    public RandomWalk(String number){

        if(!number.matches("[+-]?\\d+"))
            throw new NaNException("NaN");

        int parsedNum = Integer.parseInt(number);
        if(parsedNum < 1 || parsedNum > 10)
            throw new NumberBoundsException(String.format("Number not in bounds: %d", parsedNum));

        randomWalk = GetRandomWalk(parsedNum);
        logger.info("RandomWalk succeed");
    }

    private int GetRandomWalk(int number) {
        return new Random().nextInt(number);
    }
}
