package com.vsprog.cloud.addressservice.request;

import lombok.Getter;
import lombok.Setter;

/**
 * @author vsushko
 */
@Getter
@Setter
public class CreateAddressRequest {

    private String street;

    private String city;
}
