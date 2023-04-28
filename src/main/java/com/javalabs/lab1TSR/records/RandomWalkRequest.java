package com.javalabs.lab1TSR.records;

public class RandomWalkRequest {

    private double value;

    public double value(){
        return this.value;
    }
    public RandomWalkRequest(int value) {
        this.value = value;
    }
    public RandomWalkRequest(double value){
        this.value = value;
    }

    public RandomWalk getRandomWalk(){
        return new RandomWalk(value);
    }
}
