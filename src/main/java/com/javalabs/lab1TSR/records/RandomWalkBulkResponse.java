package com.javalabs.lab1TSR.records;

import java.util.List;

public record RandomWalkBulkResponse(StatisticsMapper stats , List<RandomWalk> values) {
}
