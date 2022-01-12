package com.vsushko.masterspringbatch.reader;

import com.vsushko.masterspringbatch.model.Product;
import com.vsushko.masterspringbatch.service.ProductService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author vsushko
 */
@Component
public class ProductServiceAdapter implements InitializingBean {

    @Autowired
    private ProductService service;

    private List<Product> products;

    @Override
    public void afterPropertiesSet() throws Exception {

        this.products = service.getProducts();
    }

    public Product nextProduct() {
        if (products.size() > 0) {
            return products.remove(0);
        } else
            return null;
    }

    public ProductService getService() {
        return service;
    }

    public void setService(ProductService service) {
        this.service = service;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}