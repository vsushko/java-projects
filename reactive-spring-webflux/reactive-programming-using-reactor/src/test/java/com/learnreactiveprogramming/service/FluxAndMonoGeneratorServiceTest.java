package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

class FluxAndMonoGeneratorServiceTest {

    private final FluxAndMonoGeneratorService service = new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {
        var namesFlux = service.namesFlux();

        StepVerifier.create(namesFlux).expectNext("alex", "ben", "chloe");
        StepVerifier.create(namesFlux)
                .expectNext("alex")
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    void namesFluxMap() {
        var namesFlux = service.namesFluxMap();

        StepVerifier.create(namesFlux).expectNext("ALEX", "BEN", "CHLOE");
    }

    @Test
    void namesFluxImmutability() {
        var namesFlux = service.namesFluxMap();

        StepVerifier.create(namesFlux).expectNext("alex", "ben", "chloe");
    }

    @Test
    void namesFluxLength() {
        var namesFlux = service.namesFluxMap(3);

        StepVerifier.create(namesFlux)
                .expectNext("4-ALEX", "5-CHLOE")
                .verifyComplete();
    }
}