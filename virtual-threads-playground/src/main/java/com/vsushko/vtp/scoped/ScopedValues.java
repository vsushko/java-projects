package com.vsushko.vtp.scoped;

import com.vsushko.vtp.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.UUID;

/**
 * @author vsushko
 */
@Slf4j
public class ScopedValues {

    private static final ScopedValue<String> SESSION_TOKEN = ScopedValue.newInstance();

    public static void main(String[] args) {

        log.info("isBound={}", SESSION_TOKEN.isBound());

        log.info("value={}", SESSION_TOKEN.orElse("Default value"));

        Thread.ofVirtual().name("1").start(() -> processIncomingRequest());
        Thread.ofVirtual().name("2").start(() -> processIncomingRequest());

        CommonUtil.sleep(Duration.ofSeconds(1));
    }

    private static void processIncomingRequest() {
        var token = authenticate();
        ScopedValue.runWhere(SESSION_TOKEN, token, () -> controller());
    }

    private static String authenticate() {
        var token = UUID.randomUUID().toString();
        log.info("Token: {}", token);
        return token;
    }

    private static void controller() {
        log.info("Controller: {}", SESSION_TOKEN.get());
        service();
    }

    private static void service() {
        log.info("before service: {}", SESSION_TOKEN.get());

        ScopedValue.runWhere(SESSION_TOKEN,
                "new-token-" + Thread.currentThread().getName(),
                () -> callExternalService()
        );
        log.info("after service: {}", SESSION_TOKEN.get());
    }

    private static void callExternalService() {
        log.info("Preparing HTTP request with token: {}", SESSION_TOKEN.get());
    }
}
