package com.javalabs.lab1TSR.entity;

import com.javalabs.lab1TSR.records.RandomWalk;
import com.javalabs.lab1TSR.records.RandomWalkRequest;
import jakarta.persistence.*;

@Entity
@Table
public class RandomWalkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private long id;

    private int value;
    private int randomWalk;

    protected RandomWalkEntity(){
    }

    public RandomWalkEntity(RandomWalkRequest request){
        this.value = new RandomWalk(request.number()).value();
        this.randomWalk = request.getRandomWalk().randomWalk();
    }

    public boolean equal(RandomWalkEntity a, RandomWalkEntity b){
        return (a.value == b.value) && (a.randomWalk == b.randomWalk);
    }

    public int value(){
        return this.value;
    }

    public int randomWalk(){
        return this.randomWalk;
    }
}
