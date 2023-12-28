package com.vsushko.tripadvisor.dto;

import java.time.LocalDate;

/**
 * @author vsushko
 */
public record Flight(String flightNumber,
                     String airline,
                     int price,
                     LocalDate date,
                     int flightDurationInMinutes) {
}
