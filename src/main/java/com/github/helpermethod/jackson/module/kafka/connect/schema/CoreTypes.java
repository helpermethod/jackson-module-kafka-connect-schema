package com.github.helpermethod.jackson.module.kafka.connect.schema;

import java.util.HashMap;
import java.util.Map;

class CoreTypes {
    private CoreTypes() {
    }

    static Map<String, Object> getSchemaForInteger(String field) {
        Map<String, Object> schema = new HashMap<>();
        schema.put("field", field);
        schema.put("type", "int32");
        schema.put("optional", true);

        return schema;
    }

    static Map<String, Object> getSchemaForString(String field) {
        Map<String, Object> schema = new HashMap<>();
        schema.put("field", field);
        schema.put("type", "string");
        schema.put("optional", true);

        return schema;
    }
}
