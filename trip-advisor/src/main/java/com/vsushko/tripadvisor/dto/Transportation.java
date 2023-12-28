package com.vsushko.tripadvisor.dto;

import java.util.List;

/**
 * @author vsushko
 */
public record Transportation(List<CarRental> carRentals,
                             List<PublicTransportation> publicTransportations) {
}
