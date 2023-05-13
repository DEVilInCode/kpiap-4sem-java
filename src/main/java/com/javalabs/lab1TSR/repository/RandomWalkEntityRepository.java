package com.javalabs.lab1TSR.repository;

import com.javalabs.lab1TSR.entity.RandomWalkEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RandomWalkEntityRepository extends JpaRepository<RandomWalkEntity, Integer> {

    Optional<RandomWalkEntity> getEntityById(long id);

    Optional<RandomWalkEntity>  findEntityByValue(int value);

}
