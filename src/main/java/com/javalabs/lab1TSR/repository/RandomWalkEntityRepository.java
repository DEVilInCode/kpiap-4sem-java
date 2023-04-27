package com.javalabs.lab1TSR.repository;

import com.javalabs.lab1TSR.entity.RandomWalkEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RandomWalkEntityRepository extends CrudRepository<RandomWalkEntity, Long>{

    RandomWalkEntity getEntityById(long id);

    RandomWalkEntity findEntityByValue(int value);

}
