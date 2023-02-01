package com.vsushko.productservice.command.api.events;

import com.vsushko.productservice.command.api.data.Product;
import com.vsushko.productservice.command.api.data.ProductRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author vsushko
 */
@ProcessingGroup("product")
@Component
public class ProductEventsHandler {

    protected ProductRepository productRepository;

    public ProductEventsHandler(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent) {
        Product product = new Product();
        BeanUtils.copyProperties(productCreatedEvent, product);
        productRepository.save(product);
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }
}
