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

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/walk")
public class RandomWalkController {
    private final RandomWalkBasicRepository repository;
    private final RequestCounter counter;

    public RandomWalkController(RandomWalkBasicRepository repository, RequestCounter counter){
        this.repository = repository;
        this.counter = counter;
    }

    @GetMapping
    public ResponseEntity<RandomWalk> randomWalk(@RequestParam(value = "num", defaultValue = "5") double number){
        counter.increment();
        RandomWalk randomWalk = repository.get(new RandomWalkRequest(number));
        return new ResponseEntity<>(randomWalk, HttpStatus.OK);
    }

    @PutMapping("/bulk")
    public ResponseEntity<RandomWalkBulkResponse> randomWalkBulk(@RequestBody List<RandomWalkRequest> requests) {
        counter.increment();
        List<RandomWalk> walks = requests.parallelStream()
                .map(repository::get)
                .collect(Collectors.toList());

        IntSummaryStatistics statistics = walks.stream()
                .mapToInt(RandomWalk::getValue)
                .summaryStatistics();

        StatisticsMapper mapper = new StatisticsMapper(statistics);
        RandomWalkBulkResponse response = new RandomWalkBulkResponse(mapper, walks);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/bulk")
    public ResponseEntity<?> randomWalkAsync(@RequestBody List<RandomWalkRequest> requests) {
        counter.increment();
        List<CompletableFuture<Void>> futures = requests.parallelStream()
                .map(request -> CompletableFuture.runAsync(() -> repository.get(request))).toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}