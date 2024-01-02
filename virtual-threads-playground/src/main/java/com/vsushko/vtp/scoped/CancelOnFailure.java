package com.vsushko.vtp.scoped;

import com.vsushko.vtp.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.StructuredTaskScope;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author vsushko
 */
@Slf4j
public class CancelOnFailure {

    public static void main(String[] args) {

        try (var taskScope = new StructuredTaskScope.ShutdownOnFailure()) {
            var subtask1 = taskScope.fork(CancelOnFailure::getDeltaAirfare);
            var subtask2 = taskScope.fork(CancelOnFailure::failingTask);

            taskScope.join();
            // taskScope.throwIfFailed(ex -> new RuntimeException("Something went wrong"));

            log.info("Subtask1 status: {}", subtask1.state());
            log.info("Subtask2 status: {}", subtask2.state());

            // log.info("Subtask1 result: {}", subtask1.get());
            // log.info("Subtask2 result: {}", subtask2.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String getDeltaAirfare() {
        var random = ThreadLocalRandom.current().nextInt(100, 1000);
        log.info("delta: {}", random);
        CommonUtil.sleep("delta", Duration.ofSeconds(1));
        return "Delta-$" + random;
    }

    private static String getFrontierAirfare() {
        var random = ThreadLocalRandom.current().nextInt(100, 1000);
        log.info("frontier: {}", random);
        CommonUtil.sleep("frontier", Duration.ofSeconds(1));
        return "Frontier-$" + random;
    }

    private static String failingTask() {
        throw new RuntimeException("oops");
    }
}
