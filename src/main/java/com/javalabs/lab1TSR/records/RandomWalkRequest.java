package com.javalabs.lab1TSR.records;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RandomWalkRequest(@JsonProperty("num") String number) {

    public RandomWalk getRandomWalk(){
        return new RandomWalk(number);
    }
}
