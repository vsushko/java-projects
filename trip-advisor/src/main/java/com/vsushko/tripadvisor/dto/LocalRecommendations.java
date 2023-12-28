package com.vsushko.tripadvisor.dto;

import java.util.List;

/**
 * @author vsushko
 */
public record LocalRecommendations(List<String> restaurants, List<String> sightseeing) {
}
