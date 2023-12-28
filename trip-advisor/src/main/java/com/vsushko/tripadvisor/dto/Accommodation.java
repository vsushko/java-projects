package com.vsushko.tripadvisor.dto;

/**
 * @author vsushko
 */
public record Accommodation(String name,
                            String type,
                            int price,
                            double rating) {
}
