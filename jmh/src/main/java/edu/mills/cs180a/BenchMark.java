package edu.mills.cs180a;

import org.openjdk.jmh.annotations.*;
import edu.mills.cs180a.refactory.Bag;
import edu.mills.cs180a.refactory.Bagel;
import edu.mills.cs180a.refactory.Order;
import edu.mills.cs180a.refactory.TextReceiptGenerator;
import org.openjdk.jmh.infra.Blackhole;

import java.util.concurrent.TimeUnit;

public class BenchMark {

    @State(Scope.Benchmark)
    public static class BenchmarkState {
        public final Order order;

        public BenchmarkState() {
            Bag[] bags = new Bag[Bagel.Type.values().length];
            for (int i = 0; i < bags.length; i++) {
                bags[i] = new Bag(new Bagel(Bagel.Type.values()[i]), 1 + (i % 14));
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
    public void generateReceipt(Blackhole blackhole, BenchmarkState state) {
        blackhole.consume(
                TextReceiptGenerator.INSTANCE.generateReceipt(state.order));
    }
}
