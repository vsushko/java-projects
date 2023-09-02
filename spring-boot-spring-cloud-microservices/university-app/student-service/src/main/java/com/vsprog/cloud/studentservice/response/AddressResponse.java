package com.vsprog.cloud.studentservice.response;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vsushko
 */
@Getter
@Setter
public class AddressResponse {

    private long addressId;

    private String street;

    private String city;
}
