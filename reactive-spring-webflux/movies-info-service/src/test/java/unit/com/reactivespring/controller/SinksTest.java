package com.reactivespring.controller;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

/**
 * @author vsushko
 */
public class SinksTest {

    @Test
    void sink() {
        Sinks.Many<Integer> replaySink = Sinks.many().replay().all();

        replaySink.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        replaySink.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        Flux<Integer> integerFlux = replaySink.asFlux();
        integerFlux.subscribe((i) -> {
            System.out.println("Subscriber 1: " + i);
        });

        Flux<Integer> integerFlux2 = replaySink.asFlux();
        integerFlux2.subscribe((i) -> {
            System.out.println("Subscriber 2: " + i);
        });

        replaySink.tryEmitNext(3);

        Flux<Integer> integerFlux3 = replaySink.asFlux();
        integerFlux3.subscribe((i) -> {
            System.out.println("Subscriber 3: " + i);
        });
    }

    @Test
    void sinkMulticasts() {
        Sinks.Many<Integer> multiCast = Sinks.many().multicast().onBackpressureBuffer();

        multiCast.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        multiCast.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        Flux<Integer> integerFlux = multiCast.asFlux();
        integerFlux.subscribe((i) -> {
            System.out.println("Subscriber 1: " + i);
        });

        Flux<Integer> integerFlux2 = multiCast.asFlux();
        integerFlux2.subscribe((i) -> {
            System.out.println("Subscriber 2: " + i);
        });

        multiCast.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);
    }

    @Test
    void sinkUniCasts() {
        Sinks.Many<Integer> multiCast = Sinks.many().unicast().onBackpressureBuffer();

        multiCast.emitNext(1, Sinks.EmitFailureHandler.FAIL_FAST);
        multiCast.emitNext(2, Sinks.EmitFailureHandler.FAIL_FAST);

        Flux<Integer> integerFlux = multiCast.asFlux();
        integerFlux.subscribe((i) -> {
            System.out.println("Subscriber 1: " + i);
        });

        Flux<Integer> integerFlux2 = multiCast.asFlux();
        integerFlux2.subscribe((i) -> {
            System.out.println("Subscriber 2: " + i);
        });

        multiCast.emitNext(3, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}
