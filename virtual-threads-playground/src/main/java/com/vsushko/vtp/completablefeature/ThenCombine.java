package com.vsushko.vtp.completablefeature;

import com.vsushko.vtp.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class ThenCombine {

    public static void main(String[] args) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var cf1 = getDeltaAirfare(executor);
            var cf2 = getFrontierAirfare(executor);
            var bestDeal = cf1.thenCombine(cf2, (a, b) -> a.amount() <= b.amount ? a : b)
                    .thenApply(af -> new Airfare(af.airline(), (int) (af.amount() * 0.9)))
                    .join();

            log.info("best deal={}", bestDeal);
        }
    }

    private static CompletableFuture<Airfare> getDeltaAirfare(ExecutorService executor) {
        return CompletableFuture.supplyAsync(() -> {
            var random = ThreadLocalRandom.current().nextInt(100, 1000);
            CommonUtil.sleep(Duration.ofMillis(random));
            log.info("Delta-{}", random);
            return new Airfare("Delta", random);
        }, executor);
    }

    private static CompletableFuture<Airfare> getFrontierAirfare(ExecutorService executor) {
        return CompletableFuture.supplyAsync(() -> {
            var random = ThreadLocalRandom.current().nextInt(100, 1000);
            CommonUtil.sleep(Duration.ofMillis(random));
            log.info("Frontier-{}", random);
            return new Airfare("Frontier", random);
        }, executor);
    }

    record Airfare(String airline, int amount) {
    }
}
