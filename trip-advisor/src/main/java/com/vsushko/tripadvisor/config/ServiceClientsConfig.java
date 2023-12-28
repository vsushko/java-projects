package com.vsushko.tripadvisor.config;

import com.vsushko.tripadvisor.client.AccommodationServiceClient;
import com.vsushko.tripadvisor.client.EventServiceClient;
import com.vsushko.tripadvisor.client.FlightReservationServiceClient;
import com.vsushko.tripadvisor.client.FlightSearchServiceClient;
import com.vsushko.tripadvisor.client.LocalRecommendationServiceClient;
import com.vsushko.tripadvisor.client.TransportationServiceClient;
import com.vsushko.tripadvisor.client.WeatherServiceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.util.concurrent.Executors;

/**
 * @author vsushko
 */
@Slf4j
@Configuration
public class ServiceClientsConfig {

    @Value("${spring.threads.virtual.enabled}")
    private boolean isVirtualThreadEnabled;

    @Bean
    public AccommodationServiceClient accommodationServiceClient(@Value("${accommodation.service.url}") String baseUrl) {
        return new AccommodationServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public EventServiceClient eventServiceClient(@Value("${event.service.url}") String baseUrl) {
        return new EventServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public WeatherServiceClient weatherServiceClient(@Value("${weather.service.url}") String baseUrl) {
        return new WeatherServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public TransportationServiceClient transportationServiceClient(@Value("${transportation.service.url}") String baseUrl) {
        return new TransportationServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public LocalRecommendationServiceClient recommendationServiceClient(@Value("${local-recommendation.service.url}") String baseUrl) {
        return new LocalRecommendationServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public FlightSearchServiceClient flightSearchServiceClient(@Value("${flight-search.service.url}") String baseUrl) {
        return new FlightSearchServiceClient(buildRestClient(baseUrl));
    }

    @Bean
    public FlightReservationServiceClient reservationServiceClient(@Value("${flight-reservation.service.url}") String baseUrl) {
        return new FlightReservationServiceClient(buildRestClient(baseUrl));
    }

    private RestClient buildRestClient(String baseUrl) {
        log.info("Base url: {}", baseUrl);

        var builder = RestClient.builder().baseUrl(baseUrl);

        if (isVirtualThreadEnabled) {
            builder = builder.requestFactory(new JdkClientHttpRequestFactory(
                    HttpClient.newBuilder()
                            .executor(Executors.newVirtualThreadPerTaskExecutor())
                            .build()
            ));
        }
        return builder.build();
    }
}
