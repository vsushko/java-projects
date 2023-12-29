package com.vsushko.vtp.executorservice;

import com.vsushko.vtp.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.Executors;

/**
 * @author vsushko
 */
public class AutoCloseable {

    private static final Logger log = LoggerFactory.getLogger(AutoCloseable.class);

    public static void main(String[] args) {
        withoutAutoCloseable();
        // withAutoClosable();
    }

    // w/o autocloseable - we have to issue shutdown for short-lived applications
    private static void withoutAutoCloseable() {
        var executorService = Executors.newSingleThreadExecutor();
        executorService.submit(AutoCloseable::task);
        log.info("Submitted");
        executorService.shutdown();
    }

    private static void withAutoClosable() {
        try (var executorService = Executors.newSingleThreadExecutor()) {
            executorService.submit(AutoCloseable::task);
            executorService.submit(AutoCloseable::task);
            executorService.submit(AutoCloseable::task);
            executorService.submit(AutoCloseable::task);
            log.info("Submitted");
        }
    }

    private static void task() {
        CommonUtil.sleep(Duration.ofSeconds(1));
        log.info("Task executed");
    }
}
