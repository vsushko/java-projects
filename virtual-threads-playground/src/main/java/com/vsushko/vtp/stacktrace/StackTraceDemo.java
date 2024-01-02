package com.vsushko.vtp.stacktrace;

import com.vsushko.vtp.util.CommonUtil;

import java.time.Duration;

/**
 * @author vsushko
 */
public class StackTraceDemo {

    public static void main(String[] args) {
        demo(Thread.ofVirtual().name("virtual-", 1));

        CommonUtil.sleep(Duration.ofSeconds(2));
    }

    private static void demo(Thread.Builder builder) {
        for (int i = 1; i < 20; i++) {
            int j = i;
            builder.start(() -> Task.execute(j));
        }
    }
}