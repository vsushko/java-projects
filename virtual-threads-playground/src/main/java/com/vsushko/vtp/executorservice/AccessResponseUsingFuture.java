package com.vsushko.vtp.executorservice;

import com.vsushko.vtp.executorservice.externalservice.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;

/**
 * @author vsushko
 */
public class AccessResponseUsingFuture {

    private static final Logger log = LoggerFactory.getLogger(AccessResponseUsingFuture.class);

    public static void main(String[] args) throws Exception {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var product1 = executor.submit(() -> Client.getProduct(1));
            var product2 = executor.submit(() -> Client.getProduct(2));
            var product3 = executor.submit(() -> Client.getProduct(3));

            log.info("product-1: {}", product1.get());
            log.info("product-2: {}", product2.get());
            log.info("product-3: {}", product3.get());
        }
    }
}
