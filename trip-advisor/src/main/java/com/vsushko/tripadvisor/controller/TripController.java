package com.vsushko.tripadvisor.controller;

import com.vsushko.tripadvisor.dto.FlightReservationResponse;
import com.vsushko.tripadvisor.dto.TripPlan;
import com.vsushko.tripadvisor.dto.TripReservationRequest;
import com.vsushko.tripadvisor.service.TripPlanService;
import com.vsushko.tripadvisor.service.TripReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vsushko
 */
@RestController
@RequestMapping("trip")
@RequiredArgsConstructor
public class TripController {

    private final TripPlanService tripPlanService;

    private final TripReservationService reservationService;

    @GetMapping("{airportCode}")
    public TripPlan tripPlan(@PathVariable String airportCode) {
        return tripPlanService.getTripPlan(airportCode);
    }

    @PostMapping("reserve")
    public FlightReservationResponse reserveFlight(@RequestBody TripReservationRequest request) {
        return reservationService.reserve(request);
    }
}
