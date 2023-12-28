package com.vsushko.tripadvisor.client;

import com.vsushko.tripadvisor.dto.Accommodation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * @author vsushko
 */
@RequiredArgsConstructor
public class AccommodationServiceClient {

    private final RestClient client;

    public List<Accommodation> getAccommodations(String airportCode) {
        return client.get()
                .uri("{airportCode}", airportCode)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }
}
