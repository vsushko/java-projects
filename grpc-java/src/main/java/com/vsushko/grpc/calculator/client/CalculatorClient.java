package com.vsushko.grpc.calculator.client;

import com.proto.calculator.AvgRequest;
import com.proto.calculator.AvgResponse;
import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.PrimeRequest;
import com.proto.calculator.SumRequest;
import com.proto.calculator.SumResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author vsushko
 */
public class CalculatorClient {

    public static void doSum(ManagedChannel channel) {
        System.out.println("Enter doSum");
        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);
        SumResponse response = stub.sum(SumRequest.newBuilder().setFirstNumber(1).setSecondNumber(1).build());
        System.out.println("Sum 1 + 1 = " + response);
    }

    public static void doPrimes(ManagedChannel channel) {
        System.out.println("Enter doPrimes");
        CalculatorServiceGrpc.CalculatorServiceBlockingStub stub = CalculatorServiceGrpc.newBlockingStub(channel);

        stub.primes(PrimeRequest.newBuilder()
                .setNumber(567890).build()).forEachRemaining(response -> {
            System.out.println(response.getPrimeFactor());
        });
    }

    public static void doAvg(ManagedChannel channel) throws InterruptedException {
        System.out.println("Enter doPrimes");
        CalculatorServiceGrpc.CalculatorServiceStub stub = CalculatorServiceGrpc.newStub(channel);

        CountDownLatch latch = new CountDownLatch(1);

        StreamObserver<AvgRequest> streamObserver = stub.avg(new StreamObserver<AvgResponse>() {
            @Override
            public void onNext(AvgResponse value) {
                System.out.println("Avg = " + value.getResult());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                latch.countDown();
            }
        });
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).forEach(number -> {
            streamObserver.onNext(AvgRequest.newBuilder()
                    .setNumber(number)
                    .build());
        });

        streamObserver.onCompleted();
        latch.await(3, TimeUnit.SECONDS);
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length == 0) {
            System.out.println("Need one argument to work");
            return;
        }

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        switch (args[0]) {
            case "sum":
                doSum(channel);
                break;
            case "primes":
                doPrimes(channel);
                break;
            case "avg":
                doAvg(channel);
                break;
            default:
                System.out.println("Keyword Invalid: " + args[0]);
        }
    }
}