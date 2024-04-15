# Organization structure analyzer application

## What it does

This application reads a CSV file and reports about

- which managers earn less than they should, and by how much
- which managers earn more than they should, and by how much
- which employees have a reporting line which is too long, and by how much

## How to build

### Get from the git repository

```shell
git clone https://github.com/farrukhjon/org-structure-analyzer.git
cd org-structure-analyzer
```

### Setup JAVA_HOME and run maven

Set up JAVA_HOME environment variable to JDK11 or change the source and target of the maven-compiler-plugin
configuration in the [pom.xml](./pom.xml) to your JDK.

```shell
mvn --version
mvn clean install
```

## How to run

```shell
java -jar target/org-structure-analyzer-1.0-SNAPSHOT.jar --file src/test/resources/employees.csv
```

### Supported program options

    Usage: java -jar target/org-structure-analyzer-1.0-SNAPSHOT.jar --file/-f <path to domain csv file>