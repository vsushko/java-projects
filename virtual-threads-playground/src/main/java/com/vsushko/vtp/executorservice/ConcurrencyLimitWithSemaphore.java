package com.vsushko.vtp.executorservice;

import com.vsushko.vtp.executorservice.concurrencylimit.ConcurrencyLimiter;
import com.vsushko.vtp.executorservice.externalservice.Client;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;

/**
 * @author vsushko
 */
@Slf4j
public class ConcurrencyLimitWithSemaphore {

    public static void main(String[] args) throws Exception {
        var factory = Thread.ofVirtual().name("vsushko-", 1).factory();
        var limiter = new ConcurrencyLimiter(Executors.newThreadPerTaskExecutor(factory), 3);
        execute(limiter, 20);
    }

    public static void execute(ConcurrencyLimiter concurrencyLimiter, int taskCount) throws Exception {
        try (concurrencyLimiter) {
            for (int i = 1; i <= taskCount; i++) {
                int j = i;
                concurrencyLimiter.submit(() -> printProductInfo(j));
            }
            log.info("Submitted");
        }
    }

    // 3rd party service
    // contract: 3 concurrent calls are allowed
    private static String printProductInfo(int id) {
        var product = Client.getProduct(id);
        log.info("{} => {}", id, product);
        return product;
    }
}
