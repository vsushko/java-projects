package com.vsushko.tripadvisor.dto;

import java.time.LocalDate;

/**
 * @author vsushko
 */
public record Event(String name,
                    String description,
                    LocalDate date) {
}
