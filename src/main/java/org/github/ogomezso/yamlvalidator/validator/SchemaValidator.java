package org.github.ogomezso.yamlvalidator.validator;

import java.io.IOException;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.ValidationMessage;

public class SchemaValidator {

    private final JsonSchemaHandler schemaHandler = new JsonSchemaHandler();

    public Set<ValidationMessage> validateFile(String schema, String fileToValidate) throws IOException {

        return switch (fileToValidate.substring(fileToValidate.lastIndexOf(".")+1)) {
            case "yaml" -> validateYamlFile(schema, fileToValidate);
            case "yml" -> validateYamlFile(schema, fileToValidate);
            case "json" -> validateJsonFile(schema, fileToValidate);
            default -> throw new IllegalArgumentException("Not Supported file Extension");
        };
    }

    public Set<ValidationMessage> validateJsonFile(String schema, String jsonToValidate) throws IOException {

        JsonSchema schemaTemplate = schemaHandler.getJsonSchemaFromPath(schema);
        JsonNode dataToValidate = schemaHandler.getJsonNodeFromPath(jsonToValidate);
        return schemaTemplate.validate(dataToValidate);
    }

    public Set<ValidationMessage> validateYamlFile(String schema, String yamlToValidate) throws IOException {

        JsonSchema schemaTemplate = schemaHandler.getJsonSchemaFromPath(schema);
        JsonNode dataToValidate = schemaHandler.getJsonNodeFromYamlInPath(yamlToValidate);
        return schemaTemplate.validate(dataToValidate);
    }
}
