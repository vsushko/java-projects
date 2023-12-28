package com.vsushko.tripadvisor.dto;

import java.time.LocalDate;

/**
 * @author vsushko
 */
public record FlightReservationRequest(String departure,
                                       String arrival,
                                       String flightNumber,
                                       LocalDate tripDate) {
}
