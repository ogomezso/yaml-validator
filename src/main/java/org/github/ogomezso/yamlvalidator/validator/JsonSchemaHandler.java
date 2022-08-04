package org.github.ogomezso.yamlvalidator.validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.SpecVersionDetector;

public class JsonSchemaHandler {
    
    private final ObjectMapper jsonMapper = new ObjectMapper();
    private final YAMLMapper yamlMapper = new YAMLMapper();
    
    public JsonNode getJsonNodeFromYamlInPath(String path) throws IOException {
        File yamlFile = new File(path);
        return yamlMapper.readTree(yamlFile);
    }
    
    public JsonNode getJsonNodeFromPath(String path) throws IOException {
        File jsonFile = new File(path);
        return jsonMapper.readTree(jsonFile);
    }

    public JsonNode getJsonNodeFromClasspath(String name) throws IOException {
        InputStream is1 = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(name);
        return jsonMapper.readTree(is1);
    }

    public JsonNode getJsonNodeFromStringContent(String content) throws IOException {
        return jsonMapper.readTree(content);
    }

    public JsonNode getJsonNodeFromUrl(String url) throws IOException {
        return jsonMapper.readTree(new URL(url));
    }

    public JsonSchema getJsonSchemaFromPath(String path) throws IOException {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        File configFile = new File(path);
        FileInputStream schemaStream = new FileInputStream(configFile);
        return factory.getSchema(schemaStream);
    }

    public JsonSchema getJsonSchemaFromClasspath(String name) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        InputStream is = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream(name);
        return factory.getSchema(is);
    }

    public JsonSchema getJsonSchemaFromStringContent(String schemaContent) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        return factory.getSchema(schemaContent);
    }

    public JsonSchema getJsonSchemaFromUrl(String uri) throws URISyntaxException {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        return factory.getSchema(new URI(uri));
    }

    public JsonSchema getJsonSchemaFromJsonNode(JsonNode jsonNode) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        return factory.getSchema(jsonNode);
    }

    public JsonSchema getJsonSchemaFromJsonNodeAutomaticVersion(JsonNode jsonNode) {
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersionDetector.detect(jsonNode));
        return factory.getSchema(jsonNode);
    }
}
