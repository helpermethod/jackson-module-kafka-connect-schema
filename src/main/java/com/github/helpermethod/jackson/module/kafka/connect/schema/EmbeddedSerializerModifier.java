package com.github.helpermethod.jackson.module.kafka.connect.schema;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;

public class EmbeddedSerializerModifier extends BeanSerializerModifier {
    @Override
    public JsonSerializer<?> modifySerializer(SerializationConfig config, BeanDescription beanDesc, JsonSerializer<?> serializer) {
        if (beanDesc.getBeanClass().isAnnotationPresent(EmbeddedSchema.class) && serializer instanceof BeanSerializerBase) {
            return new EmbeddedSchemaSerializer((BeanSerializerBase) serializer);
        }

        return super.modifySerializer(config, beanDesc, serializer);
    }
}
