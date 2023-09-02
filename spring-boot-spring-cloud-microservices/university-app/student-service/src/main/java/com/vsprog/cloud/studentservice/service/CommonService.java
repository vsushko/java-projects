package com.vsprog.cloud.studentservice.service;

import com.vsprog.cloud.studentservice.feignclients.AddressFeignClient;
import com.vsprog.cloud.studentservice.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author vsushko
 */
@Slf4j
@Service
public class CommonService {

    private final AtomicLong count = new AtomicLong(0);

    @Autowired
    private AddressFeignClient addressFeignClient;

    @CircuitBreaker(name = "addressService", fallbackMethod = "fallbackGetAddressById")
    public AddressResponse getAddressById(long addressId) {
        log.info("Count: " + count.incrementAndGet());

        return addressFeignClient.getById(addressId);
    }

    public AddressResponse fallbackGetAddressById(long addressId, Throwable t) {
        log.error("Error: " + t);
        return new AddressResponse();
    }
}

/* web client:
Mono<AddressResponse> addressResponseMono =
        webClient
                .get()
                .uri("/getById/" + addressId)
                .retrieve()
                .bodyToMono(AddressResponse.class);
return addressResponseMono.block();
 */
