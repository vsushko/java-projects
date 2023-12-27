package com.vsushko.vtp.concurrencyproblems;

import com.vsushko.vtp.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vsushko
 */
public class RaceCondition {

    private static final Logger log = LoggerFactory.getLogger(RaceCondition.class);

    private static final List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        demo(Thread.ofPlatform());

        CommonUtil.sleep(Duration.ofSeconds(2));

        log.info("list size: {}", list.size());
    }

    public static void demo(Thread.Builder builder) {
        for (int i = 0; i < 50; i++) {
            builder.start(() -> {
                log.info("Task started. {}", Thread.currentThread());
                for (int j = 0; j < 200; j++) {
                    inMemoryTask();
                }
                log.info("Task ended. {}", Thread.currentThread());
            });
        }
    }

    private static void inMemoryTask() {
        list.add(1);
    }
}
