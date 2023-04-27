package com.javalabs.lab1TSR.records;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.javalabs.lab1TSR.records.RandomWalk;
import java.io.IOException;

public class RandomWalkSerializer extends JsonSerializer<RandomWalk> {

    @Override
    public void serialize(RandomWalk randomWalk, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("value", randomWalk.value());
        jsonGenerator.writeNumberField("random_walk", randomWalk.randomWalk());
        jsonGenerator.writeEndObject();
    }
}