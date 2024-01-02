package com.vsushko.vtp.scoped;

import com.vsushko.vtp.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.UUID;

/**
 * @author vsushko
 */
@Slf4j
public class ThreadLocalWithChildThreadExample {

    private static final ThreadLocal<String> SESSION_TOKEN = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        Thread.ofVirtual().name("virtual-1").start(() -> processIncomingRequest());
        Thread.ofVirtual().name("virtual-2").start(() -> processIncomingRequest());

        CommonUtil.sleep(Duration.ofSeconds(1));
    }

    private static void processIncomingRequest() {
        authenticate();
        controller();
    }

    private static void authenticate() {
        var token = UUID.randomUUID().toString();
        log.info("Token: {}", token);
        SESSION_TOKEN.set(token);
    }

    private static void controller() {
        log.info("Controller: {}", SESSION_TOKEN.get());
        service();
    }

    private static void service() {
        log.info("Service: {}", SESSION_TOKEN.get());
        var threadName = "child-of-" + Thread.currentThread().getName();
        Thread.ofVirtual().name(threadName).start(() -> callExternalService());
    }

    private static void callExternalService() {
        log.info("Preparing HTTP request with token: {}", SESSION_TOKEN.get());
    }
}
