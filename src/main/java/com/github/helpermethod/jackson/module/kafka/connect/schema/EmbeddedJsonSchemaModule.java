package com.github.helpermethod.jackson.module.kafka.connect.schema;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class EmbeddedJsonSchemaModule extends SimpleModule {
    public EmbeddedJsonSchemaModule() {
        super("kafka-connect-schema-module");

        setSerializerModifier(new EmbeddedJsonSerializerModifier());
    }
}
