package com.vsushko.vtp.executorservice;

import com.vsushko.vtp.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author vsushko
 */
public class ExecutorServiceTypes {

    private static final Logger log = LoggerFactory.getLogger(ExecutorServiceTypes.class);

    public static void main(String[] args) {
        // single();
        // fixed();
        // cached();
        // virtual();
        schedule();
    }

    // single thread executor - to execute tasks sequentially
    private static void single() {
        execute(Executors.newSingleThreadExecutor(), 3);
    }

    // fixed thread pool
    private static void fixed() {
        execute(Executors.newFixedThreadPool(5), 5);
    }

    // elastic thread pool
    private static void cached() {
        execute(Executors.newCachedThreadPool(), 5);
    }

    // ExecutorService which creates VirtualThread per task
    private static void virtual() {
        execute(Executors.newVirtualThreadPerTaskExecutor(), 10_000);
    }

    // To schedule tasks periodically
    private static void schedule() {
        try (var executorService = Executors.newSingleThreadScheduledExecutor()) {
            executorService.scheduleAtFixedRate(() -> log.info("Executing task"), 0, 1, TimeUnit.SECONDS);
            CommonUtil.sleep(Duration.ofSeconds(5));
        }
    }

    private static void execute(ExecutorService executorService, int taskCount) {
        try (executorService) {
            for (int i = 0; i < taskCount; i++) {
                int j = i;
                executorService.submit(() -> ioTask(j));
            }
            log.info("Submitted");
        }
    }

    private static void ioTask(int i) {
        log.info("Task started: {}. Thread Info {}", i, Thread.currentThread());
        CommonUtil.sleep(Duration.ofSeconds(2));
        log.info("Task ended: {}. Thread Info {}", i, Thread.currentThread());
    }
}
