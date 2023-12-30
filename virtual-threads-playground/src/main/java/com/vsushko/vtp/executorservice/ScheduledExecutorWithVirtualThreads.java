package com.vsushko.vtp.executorservice;

import com.vsushko.vtp.executorservice.externalservice.Client;
import com.vsushko.vtp.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author vsushko
 */
@Slf4j
public class ScheduledExecutorWithVirtualThreads {

    public static void main(String[] args) {
        scheduled();
    }

    private static void scheduled() {
        var scheduler = Executors.newSingleThreadScheduledExecutor();
        var executor = Executors.newVirtualThreadPerTaskExecutor();

        try (scheduler; executor) {
            scheduler.scheduleAtFixedRate(
                    () -> executor.submit(() -> printProductInfo(1)), 0, 3, TimeUnit.SECONDS
            );
            CommonUtil.sleep(Duration.ofSeconds(15));
        }
    }

    private static void printProductInfo(int id) {
        log.info("{} => {}", id, Client.getProduct(id));
    }
}
