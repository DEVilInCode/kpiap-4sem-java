package com.javalabs.lab1TSR.repository;

import com.javalabs.lab1TSR.records.RandomWalk;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class RandomWalkBasicRepository implements RandomWalkRepository {

    @Override
    @Cacheable("values")
    public RandomWalk getByCache(int num) {
        return new RandomWalk(num);
    }
}
