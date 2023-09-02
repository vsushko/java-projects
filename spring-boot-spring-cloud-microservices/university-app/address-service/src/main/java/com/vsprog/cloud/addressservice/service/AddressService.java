package com.vsprog.cloud.addressservice.service;

import com.vsprog.cloud.addressservice.entity.Address;
import com.vsprog.cloud.addressservice.repository.AddressRepository;
import com.vsprog.cloud.addressservice.request.CreateAddressRequest;
import com.vsprog.cloud.addressservice.response.AddressResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author vsushko
 */
@Slf4j
@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public AddressResponse createAddress(CreateAddressRequest createAddressRequest) {
        Address address = new Address();
        address.setStreet(createAddressRequest.getStreet());
        address.setCity(createAddressRequest.getCity());

        addressRepository.save(address);

        return new AddressResponse(address);
    }

    public AddressResponse getById(long id) {
        log.info("Inside getById " + id);

        Address address = addressRepository.findById(id).get();

        return new AddressResponse(address);
    }
}
