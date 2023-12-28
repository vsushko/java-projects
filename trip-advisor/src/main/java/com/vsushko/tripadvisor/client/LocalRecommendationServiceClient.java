package com.vsushko.tripadvisor.client;

import com.vsushko.tripadvisor.dto.LocalRecommendations;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

/**
 * @author vsushko
 */
@RequiredArgsConstructor
public class LocalRecommendationServiceClient {

    private final RestClient client;

    public LocalRecommendations getRecommendations(String airportCode) {
        return client.get()
                .uri("{airportCode}", airportCode)
                .retrieve()
                .body(LocalRecommendations.class);
    }
}
