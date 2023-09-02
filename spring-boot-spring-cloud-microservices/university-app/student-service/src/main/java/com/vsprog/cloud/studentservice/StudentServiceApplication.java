package com.vsprog.cloud.studentservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScan({"com.vsprog.cloud.studentservice.controller", "com.vsprog.cloud.studentservice.service"})
@EntityScan("com.vsprog.cloud.studentservice.entity")
@EnableJpaRepositories("com.vsprog.cloud.studentservice.repository")
@EnableFeignClients("com.vsprog.cloud.studentservice.feignclients")
public class StudentServiceApplication {

    @Value("${address.service.url}")
    private String addressServiceUrl;

    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl(addressServiceUrl)
                .build();
    }
}
