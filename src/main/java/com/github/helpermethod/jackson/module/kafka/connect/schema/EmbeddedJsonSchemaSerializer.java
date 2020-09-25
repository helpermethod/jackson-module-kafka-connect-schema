package com.github.helpermethod.jackson.module.kafka.connect.schema;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanSerializer;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class EmbeddedJsonSchemaSerializer extends BeanSerializer {
    private static final HashMap<Class<?>, Function<String, Map<String, Object>>> schemaForType = new HashMap<>();

    static {
        schemaForType.put(Integer.class, CoreTypes::getSchemaForInteger);
        schemaForType.put(String.class, CoreTypes::getSchemaForString);
    }

    protected EmbeddedJsonSchemaSerializer(BeanSerializerBase src) {
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

        for (PropertyWriter property : (Iterable<PropertyWriter>) this::properties) {
            gen.writeObject(schemaForType.get(property.getType().getRawClass()).apply(property.getName()));
        }

        gen.writeEndArray();
    }
}