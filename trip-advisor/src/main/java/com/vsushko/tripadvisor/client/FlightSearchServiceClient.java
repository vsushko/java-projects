package com.vsushko.tripadvisor.client;

import com.vsushko.tripadvisor.dto.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * @author vsushko
 */
@RequiredArgsConstructor
public class FlightSearchServiceClient {

    private final RestClient client;

    public List<Flight> getFlights(String departure, String arrival) {
        return this.client.get()
                .uri("/{departure}/{arrival}", departure, arrival)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
