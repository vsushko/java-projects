package com.vsushko.vtp.completablefeature;

import com.vsushko.vtp.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

/**
 * @author vsushko
 */
@Slf4j
public class SimpleCompletableFuture {

    public static void main(String[] args) {
        log.info("Main starts");
        var future = slowTask();
        future.thenAccept(v -> log.info("value={}", v));
        //log.info("value={}", future.join());
        log.info("Main ends");
        CommonUtil.sleep(Duration.ofSeconds(2));
    }

    private static CompletableFuture<String> fastTask() {
        log.info("Method starts");
        CompletableFuture<String> future = new CompletableFuture<>();
        future.complete("hi");
        log.info("Method ends");
        return future;
    }

    private static CompletableFuture<String> slowTask() {
        log.info("Method starts");
        CompletableFuture<String> future = new CompletableFuture<>();
        Thread.ofVirtual().start(() -> {
            CommonUtil.sleep(Duration.ofSeconds(1));
            future.complete("hi");
        });
        log.info("Method ends");
        return future;
    }
}
