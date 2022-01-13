package com.swt.batchwriters.service;

import com.swt.batchwriters.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author vsushko
 */
//@Service
public class ProductService {

    public Product getProduct() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/product";
        return restTemplate.getForObject(url, Product.class);
    }
}
