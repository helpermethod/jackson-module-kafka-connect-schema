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
        gen.writeBooleanField("optional", false);
        gen.writeArrayFieldStart("fields");

        for (PropertyWriter propertyWriter : (Iterable<PropertyWriter>) this::properties) {
            gen.writeStartObject();
            gen.writeStringField("field", propertyWriter.getName());
            writeType(gen, propertyWriter.getType().getRawClass().getName());
            gen.writeEndObject();
        }

        gen.writeEndArray();
    }

    private void writeType(JsonGenerator gen, String name) throws IOException {
        switch (name) {
            case "int":
                gen.writeStringField("type", "int32");
                break;
            case "java.lang.String":
                gen.writeStringField("type", "string");
                break;
            case "java.sql.Timestamp":
                gen.writeStringField("type", "int64");
                gen.writeStringField("name", "org.apache.kafka.connect.data.Timestamp");
                gen.writeNumberField("version", 1);
        }
    }
}