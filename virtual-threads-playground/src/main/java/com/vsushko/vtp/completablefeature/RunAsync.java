package com.vsushko.vtp.completablefeature;

import com.vsushko.vtp.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * @author vsushko
 */
@Slf4j
public class RunAsync {

    public static void main(String[] args) {
        log.info("Main starts");

        runAsync()
                .thenRun(() -> log.info("It is done"))
                .exceptionally(ex -> {
                    log.info("Error - {}", ex.getMessage());
                    return null;
                });

        log.info("Main ends");
        CommonUtil.sleep(Duration.ofSeconds(2));
    }

    private static CompletableFuture<Void> runAsync() {
        log.info("Method starts");

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            CommonUtil.sleep(Duration.ofSeconds(1));
//            log.info("Task completed");
            throw new RuntimeException("oops");
        }, Executors.newVirtualThreadPerTaskExecutor());

        log.info("Method ends");
        return future;
    }
}
