package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

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
}
