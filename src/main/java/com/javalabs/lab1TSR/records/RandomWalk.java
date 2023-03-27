package com.javalabs.lab1TSR.records;

import java.util.Random;

public class RandomWalk {

    public final int randomWalk;

    public RandomWalk(int number){
        randomWalk = GetRandomWalk(number);
    }

    private int GetRandomWalk(int number) {
        Random rand = new Random();
        return rand.nextInt(number);
    }
}
