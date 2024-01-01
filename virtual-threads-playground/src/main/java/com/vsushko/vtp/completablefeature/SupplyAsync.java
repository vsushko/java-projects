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
public class SupplyAsync {

    public static void main(String[] args) {
        log.info("Main starts");
        var future = slowTask();
        future.thenAccept(v -> log.info("value={}", v));
        log.info("Main ends");
        CommonUtil.sleep(Duration.ofSeconds(2));
    }

    private static CompletableFuture<String> slowTask() {
        log.info("Method starts");
        var future = CompletableFuture.supplyAsync(() -> {
            CommonUtil.sleep(Duration.ofSeconds(1));
            return "hi";
        }, Executors.newVirtualThreadPerTaskExecutor());
        log.info("Method ends");
        return future;
    }
}
