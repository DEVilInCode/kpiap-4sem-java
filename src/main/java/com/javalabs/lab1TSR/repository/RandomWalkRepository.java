package com.javalabs.lab1TSR.repository;

import com.javalabs.lab1TSR.records.RandomWalk;

public interface RandomWalkRepository {
    public RandomWalk getByCache(int num);
}
