package com.github.helpermethod.jackson.module.kafka.connect.schema;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class EmbeddedSchemaModule extends SimpleModule {
    public EmbeddedSchemaModule() {
        super("kafka-schema-module");

        setSerializerModifier(new EmbeddedSerializerModifier());
    }
}
