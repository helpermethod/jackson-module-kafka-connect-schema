package com.github.helpermethod.jackson.module.kafka_schema;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;

import java.io.IOException;

public class KafkaSchemaSerializer extends BeanSerializer {
    protected KafkaSchemaSerializer(BeanSerializerBase src) {
        super(src);
    }

    @Override
    protected void serializeFields(Object bean, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeObjectFieldStart("schema");
        writeSchema(gen);
        gen.writeEndObject();

        gen.writeObjectFieldStart("payload");
        super.serializeFields(bean, gen, provider);
        gen.writeEndObject();
    }

    private void writeSchema(JsonGenerator gen) throws IOException {
        gen.writeStringField("type", "struct");
        gen.writeArrayFieldStart("fields");

        Iterable<PropertyWriter> propertyWriters = this::properties;

        for (PropertyWriter propertyWriter: propertyWriters) {
            gen.writeStartObject();
            gen.writeStringField("field", propertyWriter.getName());
            gen.writeEndObject();
        }

        gen.writeEndArray();
    }
}
