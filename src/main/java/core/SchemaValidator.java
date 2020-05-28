package core;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaClient;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class SchemaValidator {
    public static void validateObject(InputStream schemaInputStream, String json) {
        getSchema(schemaInputStream).validate(new JSONObject(json));
    }

    public static void validateArray(InputStream schemaInputStream, String json) {
        getSchema(schemaInputStream).validate(new JSONArray(json));
    }

    private static Schema getSchema(InputStream schemaInputStream) {
        JSONObject jsonSchema = new JSONObject(new JSONTokener(schemaInputStream));
        return SchemaLoader.builder()
                .schemaClient(SchemaClient.classPathAwareClient())
                .resolutionScope("classpath://../test-classes/schemas/")
                .schemaJson(jsonSchema)
                .build()
                .load()
                .build();
    }
}
