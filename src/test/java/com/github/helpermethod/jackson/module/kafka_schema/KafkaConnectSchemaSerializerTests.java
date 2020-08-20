package com.github.helpermethod.jackson.module.kafka_schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

@DisplayNameGeneration(ReplaceUnderscores.class)
class KafkaConnectSchemaSerializerTests {
    @Test
    void should_create_payload_and_schema_properties() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper().registerModule(new KafkaSchemaModule());

        String expected = "{ schema: { type: 'struct', fields: [{ field: 'firstname' }, { field: 'lastname'}] }, payload: { firstname: 'Oliver', lastname: 'Weiler' }}";

        assertThatJson(mapper.writeValueAsString(new PersonWithSchema("Oliver", "Weiler"))).isEqualTo(expected);
    }
}