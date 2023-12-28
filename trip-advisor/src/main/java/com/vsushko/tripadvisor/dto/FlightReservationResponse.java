package com.vsushko.tripadvisor.dto;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author vsushko
 */
public record FlightReservationResponse(UUID reservationId,
                                        String departure,
                                        String arrival,
                                        String flightNumber,
                                        LocalDate tripDate,
                                        int price) {
}
