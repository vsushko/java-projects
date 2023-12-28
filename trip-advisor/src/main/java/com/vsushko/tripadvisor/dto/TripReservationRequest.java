package com.vsushko.tripadvisor.dto;

import java.time.LocalDate;

/**
 * @author vsushko
 */
public record TripReservationRequest(String departure,
                                     String arrival,
                                     LocalDate date) {
}
