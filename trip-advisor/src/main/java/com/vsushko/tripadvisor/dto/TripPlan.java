package com.vsushko.tripadvisor.dto;

import java.util.List;

/**
 * @author vsushko
 */
public record TripPlan(String airportCode,
                       List<Accommodation> accommodations,
                       Weather weather,
                       List<Event> events,
                       LocalRecommendations localRecommendations,
                       Transportation transportation) {
}
