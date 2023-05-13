package com.javalabs.lab1TSR.records;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class RandomWalkSerializer extends JsonSerializer<RandomWalk> {

    @Override
    public void serialize(RandomWalk randomWalk, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("value", randomWalk.getValue());
        jsonGenerator.writeNumberField("random_walk", randomWalk.getRandomWalk());
        jsonGenerator.writeEndObject();
    }
}