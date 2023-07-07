package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class FluxAndMonoGeneratorService {

    public static void main(String[] args) {
        FluxAndMonoGeneratorService service = new FluxAndMonoGeneratorService();
        service.namesFlux().subscribe(name -> System.out.println("Name is " + name));
        service.nameMono().subscribe(name -> System.out.println("Name is " + name));
    }

    public Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"));
    }

    public Mono<String> nameMono() {
        return Mono.just("alex");
    }

    public Flux<String> namesFluxMap() {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase);
    }

    public Flux<String> namesFluxImmutability() {
        var namesFlux = Flux.fromIterable(List.of("alex", "ben", "chloe"));
        namesFlux.map(String::toUpperCase);
        return namesFlux;
    }

    public Flux<String> namesFluxMap(int stringLength) {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .map(s -> s.length() + "-" + s)
                .log();
    }

    public Flux<String> namesFluxFlatmap() {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .flatMap(s -> splitString(s))
                .log();
    }

    public Mono<List<String>> namesMonoFlatmap(int stringLength) {
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(this::splitStringMono)
                .log();
    }

    public Flux<String> namesMonoFlatmapMany(int stringLength) {
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMapMany(this::splitString)
                .log();
    }

    public Flux<String> namesFluxTransform(int stringLength) {
        Function<Flux<String>, Flux<String>> filterMap = name ->
                name.map(String::toUpperCase)
                        .filter(s -> s.length() > stringLength);

        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .transform(filterMap)
                .flatMap(s -> splitString(s))
                .defaultIfEmpty("default")
                .log();
    }

    public Flux<String> namesFluxTransformSwitchIfEmpty(int stringLength) {
        Function<Flux<String>, Flux<String>> filterMap = name ->
                name.map(String::toUpperCase)
                        .filter(s -> s.length() > stringLength);

        var defaultFlux = Flux.just("default")
                .transform(filterMap)
                .flatMap(s -> splitString(s));

        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .transform(filterMap)
                .flatMap(s -> splitString(s))
                .switchIfEmpty(defaultFlux)
                .log();
    }

    public Flux<String> exploreConcat() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");

        return Flux.concat(abcFlux, defFlux).log();
    }

    public Flux<String> exploreConcatWith() {
        var aMono = Mono.just("A");
        var bMono = Mono.just("B");

        return aMono.concatWith(bMono).log();
    }

    public Flux<String> exploreConcatMerge() {
        var abcFlux = Flux.just("A", "B", "C")
                .delayElements(Duration.ofMillis(100));
        var defFlux = Flux.just("D", "E", "F")
                .delayElements(Duration.ofMillis(125));

        return Flux.merge(abcFlux, defFlux).log();
    }

    public Flux<String> exploreConcatMergeSequential() {
        var abcFlux = Flux.just("A", "B", "C")
                .delayElements(Duration.ofMillis(100));
        var defFlux = Flux.just("D", "E", "F")
                .delayElements(Duration.ofMillis(125));

        return Flux.mergeSequential(abcFlux, defFlux).log();
    }

    public Flux<String> exploreZip() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");

        return Flux.zip(abcFlux, defFlux, (first, second) -> first + second).log();
    }

    public Flux<String> exploreZip123() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");
        var _123Flux = Flux.just("1", "2", "3");
        var _456Flux = Flux.just("4", "5", "6");

        return Flux.zip(abcFlux, defFlux, _123Flux, _456Flux)
                .map(t4 -> t4.getT1() + t4.getT2() + t4.getT3() + t4.getT4())
                .log();
    }

    public Flux<String> exploreZipWith() {
        var abcFlux = Flux.just("A", "B", "C");
        var defFlux = Flux.just("D", "E", "F");

        return abcFlux.zipWith(defFlux, (first, second) -> first + second).log();
    }

    public Flux<String> exploreMergeWith() {
        var aMono = Mono.just("A");
        var bMono = Mono.just("B");

        return aMono.mergeWith(bMono).log();
    }

    public Mono<String> exploreMergeZipWithMono() {
        var aMono = Mono.just("A");
        var bMono = Mono.just("B");

        return aMono.zipWith(bMono)
                .map(t2 -> t2.getT1() + t2.getT2())
                .log();
    }

    private Mono<List<String>> splitStringMono(String s) {
        var charArray = s.split("");
        var charList = List.of(charArray);
        return Mono.just(charList);
    }

    public Flux<String> namesFluxMapAsync(int stringLength) {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .flatMap(s -> splitStringWithDelay(s))
                .log();
    }

    public Flux<String> namesFluxConcatmap(int stringLength) {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength)
                .concatMap(s -> splitStringWithDelay(s))
                .log();
    }

    public Flux<String> splitString(String name) {
        var charArray = name.split("");

        return Flux.fromArray(charArray);
    }

    public Flux<String> splitStringWithDelay(String name) {
        var charArray = name.split("");
        var delay = new Random().nextInt(1000);
        return Flux.fromArray(charArray).delayElements(Duration.ofMillis(delay));
    }
}
