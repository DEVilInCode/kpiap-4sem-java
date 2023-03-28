package com.javalabs.lab1TSR.records;

import java.util.IntSummaryStatistics;

public record StatisticsMapper(int min, int max, double average) {
    public StatisticsMapper(IntSummaryStatistics stat){
        this(stat.getMin(), stat.getMax(), stat.getAverage());
    }
}
