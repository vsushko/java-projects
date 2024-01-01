package com.vsushko.vtp.completablefeature;

import com.vsushko.vtp.completablefeature.aggregator.AggregatorService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/*
    Demo the use of all-of
 */
@Slf4j
public class AllOf {

    public static void main(String[] args) {

        // beans / singletons
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var aggregator = new AggregatorService(executor);

        var futures = IntStream.rangeClosed(1, 50)
                .mapToObj(id -> CompletableFuture.supplyAsync(() -> aggregator.getProductDto(id), executor))
                .toList();

        CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();

        var list = futures.stream().map(CompletableFuture::join).toList();

        log.info("list: {}", list);
    }
}
