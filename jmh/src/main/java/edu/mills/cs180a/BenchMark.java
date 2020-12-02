package edu.mills.cs180a;

import org.openjdk.jmh.annotations.*;
import edu.mills.cs180a.refactory.Bag;
import edu.mills.cs180a.refactory.Bagel;
import edu.mills.cs180a.refactory.Order;
import edu.mills.cs180a.refactory.TextReceiptGenerator1;
import edu.mills.cs180a.refactory.TextReceiptGenerator2;
import org.openjdk.jmh.infra.Blackhole;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BenchMark {
    private static final int NUM_CONCATS = 1_000;

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        public final Order order;

        public BenchmarkState() {
            Bag[] bags = new Bag[Bagel.Type.values().length];
            for (int i = 0; i < bags.length; i++) {
                bags[i] = new Bag(new Bagel(Bagel.Type.values()[i]), 1 + (i % 13));
            }
            order = Order.of(bags);
        }
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Fork(value = 2)
    @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void generateReceipt1(Blackhole blackhole, BenchmarkState state) {
        blackhole.consume(
                TextReceiptGenerator1.INSTANCE.generateReceipt(state.order));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Fork(value = 2)
    @Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void generateReceipt2(Blackhole blackhole, BenchmarkState state) {
        blackhole.consume(
                TextReceiptGenerator2.INSTANCE.generateReceipt(state.order));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Fork(value = 1)
    @Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void concatenateStrings(Blackhole blackhole) {
        String s = "hello";
        for (int i = 0; i < NUM_CONCATS; i++) {
            s += "hello";
        }
        blackhole.consume(s);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Fork(value = 1)
    @Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void concatenateWithStringBuilder(Blackhole blackhole) {
        StringBuilder sb = new StringBuilder("hello");
        for (int i = 0; i < NUM_CONCATS; i++) {
            sb.append("hello");
        }
        blackhole.consume(sb.toString());
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Fork(value = 1)
    @Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void concatenateWithStringBuilder2(Blackhole blackhole) {
        StringBuilder sb = new StringBuilder(5 * (NUM_CONCATS + 1));
        for (int i = 0; i < NUM_CONCATS; i++) {
            sb.append("hello");
        }
        blackhole.consume(sb.toString());
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Fork(value = 1)
    @Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void concatenateWithJoin1(Blackhole blackhole) {
        List<String> greetings = new ArrayList<String>();
        for (int i = 0; i < NUM_CONCATS; i++) {
            greetings.add("hello");
        }
        blackhole.consume(String.join("", greetings));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @Fork(value = 1)
    @Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS)
    @Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public void concatenateWithJoin2(Blackhole blackhole) {
        List<String> greetings = new ArrayList<String>(NUM_CONCATS + 1);
        for (int i = 0; i < NUM_CONCATS; i++) {
            greetings.add("hello");
        }
        blackhole.consume(String.join("", greetings));
    }
}
