package com.javalabs.lab1TSR.repository;

import com.javalabs.lab1TSR.entity.RandomWalkEntity;
import com.javalabs.lab1TSR.records.RandomWalk;
import com.javalabs.lab1TSR.records.RandomWalkRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class RandomWalkBasicRepository{
    private static final Logger logger = LogManager.getLogger(RandomWalk.class);

    private final RandomWalkEntityRepository repository;

    public RandomWalkBasicRepository(RandomWalkEntityRepository repository){
        this.repository = repository;
    }

    @Cacheable("value")
    public RandomWalk get(RandomWalkRequest parametrs) {

        RandomWalkEntity randomWalkEntity = repository.findEntityByValue(parametrs.getRandomWalk().value());
        if(randomWalkEntity == null){
            randomWalkEntity = new RandomWalkEntity(parametrs);
            repository.save(randomWalkEntity);
            logger.info("RandomWalk saved in database");
            logger.info("RandomWalk cached");
        }
        return parametrs.getRandomWalk();
    }
}
