package com.vsushko.vtp.executorservice;

import com.vsushko.vtp.executorservice.externalservice.Client;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author vsushko
 */
@Slf4j
public class ConcurrencyLimit {

    public static void main(String[] args) {
        var factory = Thread.ofVirtual().name("vsushko-", 1).factory();
        execute(Executors.newFixedThreadPool(3, factory), 20);
    }

    public static void execute(ExecutorService executorService, int taskCount) {
        try (executorService) {
            for (int i = 1; i <= taskCount; i++) {
                int j = i;
                executorService.submit(() -> printProductInfo(j));
            }
            log.info("Submitted");
        }
    }

    // 3rd party service
    // contract: 3 concurrent calls are allowed
    private static void printProductInfo(int id) {
        log.info("{} => {}", id, Client.getProduct(id));
    }
}
