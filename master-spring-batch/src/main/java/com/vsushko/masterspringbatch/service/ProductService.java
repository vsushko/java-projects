package com.vsushko.masterspringbatch.service;

import com.vsushko.masterspringbatch.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author vsushko
 */
@Service
public class ProductService {
    public List<Product> getProducts() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/products";
        Product[] products = restTemplate.getForObject(url, Product[].class);

        return new ArrayList<>(Arrays.asList(products));
    }
}
