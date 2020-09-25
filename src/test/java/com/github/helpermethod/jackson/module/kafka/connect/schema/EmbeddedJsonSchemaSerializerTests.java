package com.github.helpermethod.jackson.module.kafka.connect.schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

@DisplayNameGeneration(ReplaceUnderscores.class)
class EmbeddedJsonSchemaSerializerTests {
    @Test
    void should_create_payload_and_schema_properties() throws JsonProcessingException {
        ObjectMapper mapper =
            new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new EmbeddedJsonSchemaModule());
        String expected = "{ schema: { type: 'struct', optional: false, fields: [{ field: 'title', type: 'string', optional: true }, { field: 'year', type: 'int32', optional: true }] }, payload: { title: 'Army of Darkness', year: 1992 } }";

        assertThatJson(mapper.writeValueAsString(new Movie("Army of Darkness", 1992))).isEqualTo(expected);
    }
}