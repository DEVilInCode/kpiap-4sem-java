package com.javalabs.lab1TSR.records;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.javalabs.lab1TSR.entity.RandomWalkEntity;
import com.javalabs.lab1TSR.exceptions.NaNException;
import com.javalabs.lab1TSR.exceptions.NumberBoundsException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

@JsonSerialize(using = RandomWalkSerializer.class)
public class RandomWalk {

    private static final Logger logger = LogManager.getLogger(RandomWalk.class);

    private final int value;
    private final int randomWalk;

    public int getValue(){
        return this.value;
    }

    public int getRandomWalk(){
        return this.randomWalk;
    }

    public RandomWalk(int value, int randomWalk){
        this.value = value;
        this.randomWalk = randomWalk;
    }

    public RandomWalk(double number){

        if(Double.isInfinite(number))
            throw new NaNException("Infinite");

        if(Double.isNaN(number))
            throw new NaNException("NaN");

        int parsedNum = (int)number;
        if(parsedNum < 1 || parsedNum > 10000)
            throw new NumberBoundsException(String.format("Number not in bounds: %d", parsedNum));

        this.value = parsedNum;
        this.randomWalk = CreateRandomWalk(parsedNum);
        //logger.info("RandomWalk succeed");
    }

    public RandomWalk(RandomWalkEntity entity){
        this.value = entity.getValue();
        this.randomWalk = entity.getRandomWalk();
    }

    private int CreateRandomWalk(int number) {
        return new Random().nextInt(number);
    }
}
