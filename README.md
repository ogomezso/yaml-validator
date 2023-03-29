[![early access](https://github.com/ogomezso/yaml-validator/actions/workflows/early-access.yaml/badge.svg)](https://github.com/ogomezso/yaml-validator/actions/workflows/early-access.yaml)}

# YAML Validator

## Introduction

A simple command line tool based on [picocli](https://picocli.info/) and  [networknt JSON Schema Validator Library](https://github.com/networknt/json-schema-validator) that allows you to validate a Yaml or (json) input file against a JSONSchema one.

## Distribution

The application will be distributed as [GraalVM Native Image](https://www.graalvm.org/22.1/reference-manual/native-image/) on 3 principal OS Distributions (Linux, OSx, Windows). You can find the available releases on:

[Release Page](https://github.com/ogomezso/yaml-validator/releases)

## Compilation and generation of native image

The app is wrapped on a maven project, you can build the app running 

~~~shell
mvn clean install
~~~

or build a native image running:

~~~shell
mcn clean package -Pnative
~~~

after running you can find the native image executable file `yaml-validator` under `target` folder.

## Running the app

To run the application as main class with maven:

~~~shell
mvn exec:java -Dexec.mainClass="org.github.ogomezso.yamlvalidator.Cli" -Dexec.args="<absolute path to json schema file> <absolute path to file to validate>"
~~~

or using the native image executable:

~~~shell
./yaml-validator <absolute path to json schema file> <absolute path to file to validate>
~~~