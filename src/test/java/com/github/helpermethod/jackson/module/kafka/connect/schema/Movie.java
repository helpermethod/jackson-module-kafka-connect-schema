package com.github.helpermethod.jackson.module.kafka.connect.schema;

@EmbeddedJsonSchema
public class Movie {
    private final String title;
    private final Integer year;

    public Movie(String title, Integer year) {
        this.title = title;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public Integer getYear() {
        return year;
    }
}