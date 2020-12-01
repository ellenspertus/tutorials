# Java Microbenchmark Harness


### Building at command line
```
mvn clean compile install  assembly:single
java -jar target/jmh-1.0-SNAPSHOT-jar-with-dependencies.jar
 ```
 
### Building in Eclipse
 
 1. `Run as > Maven clean`.
 2. `Run as > Maven install`.
 3. `Run as > Java application` with main class `org.openjdk.jmh.Main`.

### Relevant articles:

- [Microbenchmarking with Java](https://www.baeldung.com/java-microbenchmark-harness)
