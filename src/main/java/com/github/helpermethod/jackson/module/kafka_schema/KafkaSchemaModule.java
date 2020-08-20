package com.github.helpermethod.jackson.module.kafka_schema;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class KafkaSchemaModule extends SimpleModule {
    public KafkaSchemaModule(String name) {
        super(name);
    }

    public KafkaSchemaModule() {
        super("kafka-schema-module");

        setSerializerModifier(new KafkaSchemaSerializerModifier());
    }
}
