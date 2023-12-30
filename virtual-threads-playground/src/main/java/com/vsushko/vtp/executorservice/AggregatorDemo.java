package com.vsushko.vtp.executorservice;

import com.vsushko.vtp.executorservice.aggregator.AggregatorService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * @author vsushko
 */
@Slf4j
public class AggregatorDemo {

    public static void main(String[] args) throws Exception {
        // beans / singletons
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var aggregator = new AggregatorService(executor);

        var futures = IntStream.rangeClosed(1, 50)
                .mapToObj(id -> executor.submit(() -> aggregator.getProductDto(id)))
                .toList();

        var list = futures.stream()
                .map(AggregatorDemo::toProductDto)
                .toList();

        log.info("list: {}", list);
    }

    private static ProductDto toProductDto(Future<ProductDto> future) {
        try {
            return future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
