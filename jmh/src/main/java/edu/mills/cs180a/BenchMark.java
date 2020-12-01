package edu.mills.cs180a;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class BenchMark {

    @State(Scope.Benchmark)
    public static class Log {
        public int x = 8;
    }

    @Benchmark
    @Fork(value = 1, warmups = 1)
    @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.Throughput)
    public void init() {
        // Do nothing
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void doNothing() {
        // Do nothing
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void objectCreation() {
        new Object();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public Object pillarsOfCreation() {
        return new Object();
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void blackHole(Blackhole blackhole) {
        blackhole.consume(new Object());
    }

    @Benchmark
    @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public double foldedLog() {
        int x = 8;

        return Math.log(x);
    }

    @Benchmark
    @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.MILLISECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public double log(Log input) {
        return Math.log(input.x);
    }

}
