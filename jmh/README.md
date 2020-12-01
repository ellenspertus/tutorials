## Java Microbenchmark Harness

This module contains articles about the Java Microbenchmark Harness (JMH).

I haven't been able to get this to work in Eclipse, but it does work at the command line:

```
mvn clean compile install  assembly:single
java -jar target/jmh-1.0-SNAPSHOT-jar-with-dependencies.jar
 ```

### Relevant articles:

- [Microbenchmarking with Java](https://www.baeldung.com/java-microbenchmark-harness)
