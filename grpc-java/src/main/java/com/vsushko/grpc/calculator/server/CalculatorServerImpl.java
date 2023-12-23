package com.vsushko.grpc.calculator.server;

import com.proto.calculator.CalculatorServiceGrpc;
import com.proto.calculator.PrimeRequest;
import com.proto.calculator.PrimeResponse;
import com.proto.calculator.SumRequest;
import com.proto.calculator.SumResponse;
import io.grpc.stub.StreamObserver;

/**
 * @author vsushko
 */
public class CalculatorServerImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase {
    @Override
    public void sum(SumRequest request, StreamObserver<SumResponse> responseObserver) {
        responseObserver.onNext(SumResponse.newBuilder()
                .setResult(request.getFirstNumber() + request.getSecondNumber())
                .build());

        responseObserver.onCompleted();
    }

    @Override
    public void primes(PrimeRequest request, StreamObserver<PrimeResponse> responseObserver) {
        int number = request.getNumber();
        int divisor = 2;

        while (number > 1) {
            if (number % divisor == 0) {
                number = number / divisor;
                responseObserver.onNext(PrimeResponse.newBuilder()
                        .setPrimeFactor(divisor)
                        .build());
            } else {
                ++divisor;
            }
        }
        responseObserver.onCompleted();
    }
}
