package com.vsushko.tripadvisor.client;

import com.vsushko.tripadvisor.dto.FlightReservationRequest;
import com.vsushko.tripadvisor.dto.FlightReservationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.client.RestClient;

/**
 * @author vsushko
 */
@RequiredArgsConstructor
public class FlightReservationServiceClient {

    private final RestClient client;

    public FlightReservationResponse reserve(FlightReservationRequest request) {
        return this.client.post()
                .body(request)
                .retrieve()
                .body(FlightReservationResponse.class);
    }
}
