package com.github.helpermethod.jackson.module.kafka.connect.schema;

import java.util.HashMap;
import java.util.Map;

class Schema {
    private Schema() {
    }

    static Map<String, Object> create(String fieldname, String typename) {
        Map<String, Object> schema = new HashMap<>();
        schema.put("field", fieldname);

        switch (typename) {
            case "int":
                schema.put("type", "int32");
                break;
            case "java.lang.String":
                schema.put("type", "string");
                break;
            case "java.sql.Timestamp":
                schema.put("type", "int64");
                schema.put("name", "org.apache.kafka.connect.data.Timestamp");
                schema.put("version", 1);
        }

        return schema;
    }
}
