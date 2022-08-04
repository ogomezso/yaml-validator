package org.github.ogomezso.yamlvalidator;

import java.util.Set;
import java.util.concurrent.Callable;

import org.github.ogomezso.yamlvalidator.validator.SchemaValidator;

import com.networknt.schema.ValidationMessage;

import lombok.extern.slf4j.Slf4j;
import picocli.CommandLine;
import picocli.CommandLine.Parameters;

@Slf4j
public class Cli implements Callable<Integer> {
    @Parameters(index = "0", description = "Absolute path to JsonSchema file for topology validation.")
    private String pathToJsonSchemaFile;

    @Parameters(index = "1", description = "Absolute path to topology file to validate. Must be one of json or yaml file")
    private String pathToTopologyFile;

    @Override
    public Integer call() throws Exception {

        SchemaValidator validator = new SchemaValidator();
        Set<ValidationMessage> result = validator.validateFile(pathToJsonSchemaFile, pathToTopologyFile);

        if (result.isEmpty()) {
            log.info("Validation Completed Successfully");
        } else {
            result.forEach(msg -> log.info(msg.getMessage()));
        };
        return 0;
    }

    public static void main(String... args) {

        int exitCode = new CommandLine(new Cli()).execute(args);
        System.exit(exitCode);
    }
}
