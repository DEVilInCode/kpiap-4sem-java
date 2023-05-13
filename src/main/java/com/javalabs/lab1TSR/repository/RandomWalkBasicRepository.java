package com.javalabs.lab1TSR.repository;

import com.javalabs.lab1TSR.entity.RandomWalkEntity;
import com.javalabs.lab1TSR.records.RandomWalk;
import com.javalabs.lab1TSR.records.RandomWalkRequest;
import com.javalabs.lab1TSR.repository.RandomWalkEntityRepository;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RandomWalkBasicRepository {
    private static final Logger logger = LogManager.getLogger(RandomWalkBasicRepository.class);
    private final RandomWalkEntityRepository repository;

    public RandomWalkBasicRepository(RandomWalkEntityRepository repository) {
        this.repository = repository;
    }

    public RandomWalk get(RandomWalkRequest request) {
        int value = (int)request.value();
        Optional<RandomWalkEntity> entityOptional = (repository.findEntityByValue(value));

        return entityOptional.map(entity -> {
            //logger.info("RandomWalk with value {} found in cache", value);
            return toRandomWalk(entity);
        }).orElseGet(() -> {
            RandomWalkEntity entity = createAndSaveEntity(request);
            //logger.info("RandomWalk with value {} added to cache", value);
            return toRandomWalk(entity);
        });
    }

    @CacheEvict(value = "random_walk_cache", allEntries = true)
    public RandomWalk save(@NonNull RandomWalkRequest request, @NonNull RandomWalk randomWalk) {
        int value = (int)request.value();
        RandomWalkEntity entity = repository.findEntityByValue(value)
                .orElseGet(() -> new RandomWalkEntity(request));

        entity.setRandomWalk(randomWalk.getValue());
        repository.save(entity);
        return toRandomWalk(entity);
    }

    private RandomWalk toRandomWalk(RandomWalkEntity entity) {
        return new RandomWalk(entity.getValue(), entity.getRandomWalk());
    }

    private synchronized RandomWalkEntity createAndSaveEntity(RandomWalkRequest request) {
        RandomWalkEntity entity = new RandomWalkEntity(request);
        repository.save(entity);
        return entity;
    }
}