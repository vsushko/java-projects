package com.vsprog.cloud.addressservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.vsprog.cloud.addressservice.controller", "com.vsprog.cloud.addressservice.service"})
@EntityScan("com.vsprog.cloud.addressservice.entity")
@EnableJpaRepositories("com.vsprog.cloud.addressservice.repository")
public class AddressServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressServiceApplication.class, args);
    }
}
