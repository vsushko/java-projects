package com.vsushko.tripadvisor.service;

import com.vsushko.tripadvisor.client.AccommodationServiceClient;
import com.vsushko.tripadvisor.client.EventServiceClient;
import com.vsushko.tripadvisor.client.FlightReservationServiceClient;
import com.vsushko.tripadvisor.client.FlightSearchServiceClient;
import com.vsushko.tripadvisor.client.LocalRecommendationServiceClient;
import com.vsushko.tripadvisor.client.TransportationServiceClient;
import com.vsushko.tripadvisor.client.WeatherServiceClient;
import com.vsushko.tripadvisor.dto.TripPlan;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author vsushko
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TripPlanService {

    private final AccommodationServiceClient accommodationServiceClient;

    private final EventServiceClient eventServiceClient;

    private final FlightReservationServiceClient flightReservationServiceClient;

    private final FlightSearchServiceClient flightSearchServiceClient;

    private final LocalRecommendationServiceClient localRecommendationServiceClient;

    private final TransportationServiceClient transportationServiceClient;

    private final WeatherServiceClient weatherServiceClient;

    private final ExecutorService executorService;

    public TripPlan getTripPlan(String airportCode) {
        var events = executorService.submit(() -> eventServiceClient.getEvents(airportCode));
        var weather = executorService.submit(() -> weatherServiceClient.getWeather(airportCode));
        var accommodations = executorService.submit(() -> accommodationServiceClient.getAccommodations(airportCode));
        var transportation = executorService.submit(() -> transportationServiceClient.getTransportation(airportCode));
        var recommendations = executorService.submit(() -> localRecommendationServiceClient.getRecommendations(airportCode));

        return new TripPlan(
                airportCode,
                getOrElse(accommodations, Collections.emptyList()),
                getOrElse(weather, null),
                getOrElse(events, null),
                getOrElse(recommendations, null),
                getOrElse(transportation, null)
        );
    }

    private <T> T getOrElse(Future<T> future, T defaultValue) {
        try {
            return future.get();
        } catch (Exception e) {
            log.error("Error", e);
        }
        return defaultValue;
    }
}
