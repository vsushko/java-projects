package com.simpleproductservice.simpleproductservice;

import com.simpleproductservice.simpleproductservice.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author vsushko
 */
@RestController
public class ProductController {

    @GetMapping("/product")
    public Product getProduct() {
        return new Product(1, "Apple watch", "Apple watch", BigDecimal.valueOf(20.0), 10);
    }
}
