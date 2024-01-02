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
public class FirstSuccess {

    public static void main(String[] args) {

        try (var taskScope = new StructuredTaskScope.ShutdownOnSuccess<>()) {
            var subtask1 = taskScope.fork(FirstSuccess::failingTask);
            //var subtask1 = taskScope.fork(FirstSuccess::getDeltaAirfare);
            var subtask2 = taskScope.fork(FirstSuccess::failingTask);
            //var subtask2 = taskScope.fork(FirstSuccess::getFrontierAirfare);

            taskScope.join();

            log.info("Subtask1 status: {}", subtask1.state());
            log.info("Subtask2 status: {}", subtask2.state());

            log.info("Subtask1 result: {}", taskScope.result());
            log.info("Subtask1 result: {}", taskScope.result(ex -> new RuntimeException("All failed")));
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
