package com.vsushko.vtp.concurrencyproblems;

import com.vsushko.vtp.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.ThreadFactory;

/**
 * @author vsushko
 */
public class ThreadFactoryExample {

    private static final Logger log = LoggerFactory.getLogger(ThreadFactoryExample.class);

    public static void main(String[] args) {
        demo(Thread.ofVirtual().name("virtual-", 1).factory());

        CommonUtil.sleep(Duration.ofSeconds(3));
    }

    /* Create few threads. Each thread creates 1 child thread.
       It is a simple demo. In the real life, lets use ExecutorService etc.
       Virtual threads are cheap to create.
     */

    private static void demo(ThreadFactory factory) {
        for (int i = 0; i < 30; i++) {
            var t = factory.newThread(() -> {
                log.info("Task started. {}", Thread.currentThread());

                var ct = factory.newThread(() -> {
                    log.info("Child task started. {}", Thread.currentThread());

                    CommonUtil.sleep(Duration.ofSeconds(2));
                    log.info("Child task ended. {}", Thread.currentThread());
                });
                ct.start();
                log.info("Task ended. {}", Thread.currentThread());
            });
            t.start();
        }
    }
}
