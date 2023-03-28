package com.javalabs.lab1TSR.repository;

import com.javalabs.lab1TSR.records.RandomWalk;
import com.javalabs.lab1TSR.records.RandomWalkRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class RandomWalkBasicRepository{
    private static final Logger logger = LogManager.getLogger(RandomWalk.class);

    @Cacheable("values")
    public RandomWalk get(RandomWalkRequest parametrs) {
        logger.info("RandomWalk cached");
        return parametrs.getRandomWalk();
    }
}
