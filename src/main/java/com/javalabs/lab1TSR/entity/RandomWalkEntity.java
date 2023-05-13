package com.javalabs.lab1TSR.entity;

import com.javalabs.lab1TSR.records.RandomWalkRequest;
import jakarta.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "random_walk_cache")
public class RandomWalkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "value", nullable = false, unique = true)
    private int value;

    @Column(name = "random_walk", nullable = false)
    private int randomWalk;

    public RandomWalkEntity() {}

    public RandomWalkEntity(RandomWalkRequest request) {
        this.value = (int)request.value();
        this.randomWalk = request.getRandomWalk().getRandomWalk();
    }

    public void update(RandomWalkRequest request) {
        this.value = (int)request.value();
        this.randomWalk = request.getRandomWalk().getRandomWalk();
    }

    public boolean equal(RandomWalkEntity a, RandomWalkEntity b){
        return (a.value == b.value && a.randomWalk == b.randomWalk);
    }

    public int getValue(){
        return this.value;
    }

    public int getRandomWalk(){
        return this.randomWalk;
    }

    public void setRandomWalk(int randomWalk){
        this.randomWalk = randomWalk;
    }
}
