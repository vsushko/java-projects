package com.vsushko.vtp.scoped;

import com.vsushko.vtp.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author vsushko
 */
@Slf4j
public class ScopedValuesWithStructuredTaskScope {

    private static final ScopedValue<String> SESSION_TOKEN = ScopedValue.newInstance();

    public static void main(String[] args) {
        ScopedValue.runWhere(SESSION_TOKEN, "token-123", () -> task());
    }

    private static void task() {
        try (var taskScope = new StructuredTaskScope<>()) {

            log.info("Token: {}", SESSION_TOKEN.get());

            var subtask1 = taskScope.fork(ScopedValuesWithStructuredTaskScope::getDeltaAirfare);
            var subtask2 = taskScope.fork(ScopedValuesWithStructuredTaskScope::getFrontierAirfare);

             taskScope.join();

            log.info("Subtask1 status: {}", subtask1.state());
            log.info("Subtask2 status: {}", subtask2.state());

            log.info("Subtask1 result: {}", subtask1.get());
            log.info("Subtask2 result: {}", subtask2.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getDeltaAirfare() {
        var random = ThreadLocalRandom.current().nextInt(100, 1000);
        log.info("delta: {}", random);
        log.info("token: {}", SESSION_TOKEN.get());
        CommonUtil.sleep("delta", Duration.ofSeconds(1));
        return "Delta-$" + random;
    }

    private static String getFrontierAirfare() {
        var random = ThreadLocalRandom.current().nextInt(100, 1000);
        log.info("frontier: {}", random);
        log.info("token: {}", SESSION_TOKEN.get());
        CommonUtil.sleep("frontier", Duration.ofSeconds(1));
        return "Frontier-$" + random;
    }

    private static String failingTask() {
        throw new RuntimeException("oops");
    }
}
