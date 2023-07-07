package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import java.util.List;

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

    @Test
    void namesFluxFlatmap() {
        var namesFlux = service.namesFluxFlatmap();

        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X", "B", "E", "N", "C", "H", "L", "O", "E")
                .verifyComplete();
    }

    @Test
    void namesFluxMapAsync() {
        var namesFlux = service.namesFluxMapAsync(3);

        StepVerifier.create(namesFlux)
                .expectNextCount(9)
                .verifyComplete();
    }

    @Test
    void namesFluxConcatmap() {
        var namesFlux = service.namesFluxConcatmap(3);

        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .verifyComplete();
    }

    @Test
    void namesMonoFlatmap() {
        var namesFlux = service.namesMonoFlatmap(3);

        StepVerifier.create(namesFlux)
                .expectNext(List.of("A", "L", "E", "X"))
                .verifyComplete();
    }

    @Test
    void namesMonoFlatmapMany() {
        var namesFlux = service.namesMonoFlatmapMany(3);

        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X")
                .verifyComplete();
    }

    @Test
    void namesFluxTransform() {
        var namesFlux = service.namesFluxTransform(3);

        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X", "C", "H", "L", "O", "E")
                .verifyComplete();
    }

    @Test
    void namesFluxTransformDefault() {
        var namesFlux = service.namesFluxTransform(6);

        StepVerifier.create(namesFlux)
                .expectNext("default")
                .verifyComplete();
    }

    @Test
    void namesFluxTransformDefaultSwitchIfEmpty() {
        var namesFlux = service.namesFluxTransformSwitchIfEmpty(6);

        StepVerifier.create(namesFlux)
                .expectNext("D", "E", "F", "A", "U", "L", "T")
                .verifyComplete();
    }

    @Test
    void exploreConcat() {
        var namesFlux = service.exploreConcat();

        StepVerifier.create(namesFlux)
                .expectNext("A", "B", "C", "D", "E", "F")
                .verifyComplete();
    }

    @Test
    void exploreConcatWith() {
        var namesFlux = service.exploreConcatWith();

        StepVerifier.create(namesFlux)
                .expectNext("A", "B")
                .verifyComplete();
    }

    @Test
    void exploreConcatMerge() {
        var namesFlux = service.exploreConcatMerge();

        StepVerifier.create(namesFlux)
                .expectNext("A", "D", "B", "E", "C", "F")
                .verifyComplete();
    }

    @Test
    void exploreConcatMergeSequential() {
        var namesFlux = service.exploreConcatMergeSequential();

        StepVerifier.create(namesFlux)
                .expectNext("A", "B", "C", "D", "E", "F")
                .verifyComplete();
    }

    @Test
    void exploreConcatMergeWith() {
        var namesFlux = service.exploreMergeWith();

        StepVerifier.create(namesFlux)
                .expectNext("A", "B")
                .verifyComplete();
    }

    @Test
    void exploreZip() {
        var namesFlux = service.exploreZip();

        StepVerifier.create(namesFlux)
                .expectNext("AD", "BE", "CF")
                .verifyComplete();
    }

    @Test
    void exploreZip123() {
        var namesFlux = service.exploreZip123();

        StepVerifier.create(namesFlux)
                .expectNext("AD14", "BE25", "CF36")
                .verifyComplete();
    }

    @Test
    void exploreZipWith() {
        var namesFlux = service.exploreZipWith();

        StepVerifier.create(namesFlux)
                .expectNext("AD", "BE", "CF")
                .verifyComplete();
    }

    @Test
    void exploreMergeZipWithMono() {
        var namesFlux = service.exploreMergeZipWithMono();

        StepVerifier.create(namesFlux)
                .expectNext("AB")
                .verifyComplete();
    }
}