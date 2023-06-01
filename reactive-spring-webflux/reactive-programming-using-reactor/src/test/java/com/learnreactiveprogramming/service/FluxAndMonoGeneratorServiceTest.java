package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class FluxAndMonoGeneratorServiceTest {

    private final FluxAndMonoGeneratorService service = new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {

        var namesFlux = service.namesFlux();

        StepVerifier.create(namesFlux).expectNext("alex", "been", "chloe");
        StepVerifier.create(namesFlux)
                .expectNext("alex")
                .expectNextCount(2)
                .verifyComplete();
    }
}