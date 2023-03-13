package com.learnjava.completablefuture;

import com.learnjava.service.HelloWorldService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CompletableFutureHelloWorldTest {

    private final HelloWorldService hws = new HelloWorldService();
    private final CompletableFutureHelloWorld cfhw = new CompletableFutureHelloWorld(hws);

    @Test
    void helloWorld() {
        //when
        CompletableFuture<String> completableFuture = cfhw.helloWorld();
        //then
        completableFuture
                .thenAccept(s -> {
                    //assertEquals("hello world", s);
                    assertEquals("HELLO WORLD", s);
                })
                .join();
    }

//    @Test
//    void helloWorld_1() {
//        //when
//        String hw = cfhw.helloWorld_1();
//        //then
//        assertEquals("HELLO WORLD", hw);
//    }

    @Test
    void helloWorld_multiple_async_calls() {
        //when
        String hw = cfhw.helloWorld_multiple_async_calls();
        //then
        assertEquals("HELLO WORLD!", hw);
    }

    @Test
    void helloWorld_3_async_calls() {
        //when
        String hw = cfhw.helloWorld_3_async_calls();
        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", hw);
    }

    @Test
    void helloWorld_3_async_calls_log_async() {
        //when
        String hw = cfhw.helloWorld_3_async_calls_log_async();
        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", hw);
    }

    @Test
    void helloWorld_3_async_calls_custom_threadPool() {
        //when
        String hw = cfhw.helloWorld_3_async_calls_custom_threadPool();
        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", hw);
    }

    @Test
    void helloWorld_3_async_calls_custom_threadpool_async() {
        //when
        String hw = cfhw.helloWorld_3_async_calls_custom_threadpool_async();
        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE!", hw);
    }

    @Test
    void helloWorld_4_async_calls() {
        //when
        String hw = cfhw.helloWorld_4_async_calls();
        //then
        assertEquals("HELLO WORLD! HI COMPLETABLEFUTURE! BYE!", hw);
    }

    @Test
    void helloWorld_thenCompose() {
        //when
        startTimer();

        CompletableFuture<String> completableFuture = cfhw.helloWorld_thenCompose();

        //then
        completableFuture
                .thenAccept(s -> {
                    //assertEquals("hello world", s);
                    assertEquals("HELLO WORLD!", s);
                })
                .join();
        timeTaken();
    }

    @Test
    void anyOf() {
        //when
        String result = cfhw.anyOf();
        //then
        assertEquals("hello world", result);
    }
}