package com.vsprog.cloud.addressservice.response;

import com.vsprog.cloud.addressservice.entity.Address;
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

    public AddressResponse(Address address) {
        this.addressId = address.getId();
        this.street = address.getStreet();
        this.city = address.getCity();
    }
}
