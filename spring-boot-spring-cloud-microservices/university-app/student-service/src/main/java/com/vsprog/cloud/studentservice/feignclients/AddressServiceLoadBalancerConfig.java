package com.vsprog.cloud.studentservice.feignclients;

import feign.Feign;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

/**
 * @author vsushko
 */
//@LoadBalancerClient(value = "address-service")
public class AddressServiceLoadBalancerConfig {

//    @LoadBalanced
//    @Bean
//    public Feign.Builder feignBuilder() {
//        return Feign.builder();
//    }
}
