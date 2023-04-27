package com.javalabs.lab1TSR.records;

public class RandomWalkRequest {

    private double number;

    public double number(){
        return this.number;
    }
    public RandomWalkRequest(int number) {
        this.number = number;
    }
    public RandomWalkRequest(double num){
        this.number = num;
    }

    public RandomWalk getRandomWalk(){
        return new RandomWalk(number);
    }
}
