package com.vsushko.vtp.completablefeature;

import com.vsushko.vtp.completablefeature.aggregator.AggregatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;

/*
    Aggregate and error handling
 */
public class AggregatorDemo {

    private static final Logger log = LoggerFactory.getLogger(AggregatorDemo.class);

    public static void main(String[] args) throws Exception {
        // beans / singletons
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var aggregator = new AggregatorService(executor);

        log.info("product={}", aggregator.getProductDto(50));
    }
}
