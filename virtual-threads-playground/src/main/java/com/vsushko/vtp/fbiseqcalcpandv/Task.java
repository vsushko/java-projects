package com.vsushko.vtp.fbiseqcalcpandv;

import com.vsushko.vtp.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author vsushko
 */
public class Task {

    private static final Logger log = LoggerFactory.getLogger(Task.class);

    public static void main(String[] args) {
        cpuIntensive(45);
    }

    public static void cpuIntensive(int i) {
        //log.info("Starting CPU task. Thread Info: {}", Thread.currentThread());
        var timeTaken = CommonUtil.timer(() -> findFib(i));
        //log.info("Ending CPU task. Time taken: {} ms", timeTaken);
    }

    private static long findFib(long input) {
        if (input < 2) {
            return input;
        }
        return findFib(input - 1) + findFib(input - 2);
    }
}
