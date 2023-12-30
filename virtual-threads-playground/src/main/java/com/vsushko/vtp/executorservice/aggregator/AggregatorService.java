package com.vsushko.vtp.executorservice.aggregator;

import com.vsushko.vtp.executorservice.ProductDto;
import com.vsushko.vtp.executorservice.externalservice.Client;

import java.util.concurrent.ExecutorService;

/**
 * @author vsushko
 */
public class AggregatorService {

    private final ExecutorService executorService;

    public AggregatorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public ProductDto getProductDto(int id) throws Exception {
        var product = executorService.submit(() -> Client.getProduct(id));
        var rating = executorService.submit(() -> Client.getRating(id));
        return new ProductDto(id, product.get(), rating.get());
    }
}
