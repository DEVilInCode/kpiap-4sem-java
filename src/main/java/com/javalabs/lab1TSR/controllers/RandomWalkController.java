package com.javalabs.lab1TSR.controllers;

import com.javalabs.lab1TSR.counter.RequestCounter;
import com.javalabs.lab1TSR.records.RandomWalk;
import com.javalabs.lab1TSR.records.RandomWalkBulkResponse;
import com.javalabs.lab1TSR.records.RandomWalkRequest;

import com.javalabs.lab1TSR.records.StatisticsMapper;
import com.javalabs.lab1TSR.repository.RandomWalkBasicRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;


@RestController
public class RandomWalkController {
    private final RandomWalkBasicRepository repository;
    private final RequestCounter counter;

    public RandomWalkController(RandomWalkBasicRepository repository, RequestCounter counter){
        this.repository = repository;
        this.counter = counter;
    }

    @GetMapping("/walk")
    public RandomWalk randomWalk(@RequestParam(value = "num", defaultValue = "5") double number){
        counter.increment();
        return repository.get(new RandomWalkRequest(number));
    }

    @PostMapping("/walk")
    public ResponseEntity<?> randomWalkBulk(@RequestBody List<RandomWalkRequest> body) {
        counter.increment();
        List<RandomWalk> result = new ArrayList<>();
        body.forEach((element) -> result.add(
                repository.get(element)
        ));

        StatisticsMapper stats = new StatisticsMapper(result.stream()
                .map(RandomWalk::value)
                .collect(IntSummaryStatistics::new,
                        IntSummaryStatistics::accept,
                        IntSummaryStatistics::combine));

        return new ResponseEntity<>(new RandomWalkBulkResponse(stats, result), HttpStatus.OK);
    }
}