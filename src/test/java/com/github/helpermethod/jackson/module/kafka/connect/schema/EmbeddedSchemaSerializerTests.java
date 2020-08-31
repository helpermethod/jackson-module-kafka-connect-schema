package com.github.helpermethod.jackson.module.kafka.connect.schema;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

@DisplayNameGeneration(ReplaceUnderscores.class)
class EmbeddedSchemaSerializerTests {
    @Test
    void should_create_payload_and_schema_properties() throws JsonProcessingException {
        ObjectMapper mapper =
            new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new EmbeddedSchemaModule());

        String expected = "{ schema: { type: 'struct', optional: false, fields: [{ field: 'title', type: 'string' }, { field: 'year', type: 'int32'}, { field: 'released', type: 'int64', name: 'org.apache.kafka.connect.data.Timestamp', version: 1 } ] }, payload: { title: 'Army of Darkness', year: 1992, released: 730108800000 } }";

        assertThatJson(mapper.writeValueAsString(new Movie("Army of Darkness", 1992, Timestamp.from(LocalDate.of(1993, 02, 19).atStartOfDay(ZoneId.of("America/Los_Angeles")).toInstant())))).isEqualTo(expected);
    }
}