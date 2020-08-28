package com.github.helpermethod.jackson.module.kafka_schema;

import java.sql.Timestamp;

@KafkaConnectSchema
public class Movie {
    private final String title;
    private final int year;
    private final Timestamp released;

    public Movie(String title, int year, Timestamp released) {
        this.title = title;
        this.year = year;
        this.released = released;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public Timestamp getReleased() {
        return released;
    }
}