package com.github.helpermethod.jackson.module.kafka_schema;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@KafkaConnectSchema
public class PersonWithSchema {
    private final String firstname;
    private final String lastname;

    @JsonCreator
    public PersonWithSchema(@JsonProperty String firstname, @JsonProperty String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
