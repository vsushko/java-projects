package com.vsushko.tripadvisor.service;

import com.vsushko.tripadvisor.client.FlightReservationServiceClient;
import com.vsushko.tripadvisor.client.FlightSearchServiceClient;
import com.vsushko.tripadvisor.dto.Flight;
import com.vsushko.tripadvisor.dto.FlightReservationRequest;
import com.vsushko.tripadvisor.dto.FlightReservationResponse;
import com.vsushko.tripadvisor.dto.TripReservationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;

/**
 * @author vsushko
 */
@Service
@RequiredArgsConstructor
public class TripReservationService {

    private final FlightSearchServiceClient searchServiceClient;

    private final FlightReservationServiceClient reservationServiceClient;

    public FlightReservationResponse reserve(TripReservationRequest request) {
        var flights = searchServiceClient.getFlights(request.departure(), request.arrival());

        var bestDeal = flights.stream().min(Comparator.comparingInt(Flight::price));

        var flight = bestDeal.orElseThrow(() -> new IllegalStateException("No flights found"));

        var reservationRequest = new FlightReservationRequest(
                request.departure(), request.arrival(), flight.flightNumber(), request.date()
        );
        return reservationServiceClient.reserve(reservationRequest);
    }
}
