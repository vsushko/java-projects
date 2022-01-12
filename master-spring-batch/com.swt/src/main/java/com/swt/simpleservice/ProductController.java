package com.swt.simpleservice;

import com.swt.simpleservice.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vsushko
 */
@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getProducts() {
        List<Product> list = new ArrayList<>();
        list.add(new Product(1, "Apple", "Apple Cell from service", BigDecimal.valueOf(300.00), 10));
        list.add(new Product(1, "Dell", "Apple Cell from service", BigDecimal.valueOf(700.00), 10));
        return list;
    }
}
