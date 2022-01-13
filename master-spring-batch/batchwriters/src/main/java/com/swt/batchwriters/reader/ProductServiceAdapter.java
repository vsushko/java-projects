package com.swt.batchwriters.reader;

import com.swt.batchwriters.model.Product;
import com.swt.batchwriters.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author vsushko
 */
//@Component
public class ProductServiceAdapter {
    private final Logger logger = LoggerFactory.getLogger(ProductServiceAdapter.class);

    @Autowired
    private ProductService service;

    public Product nextProduct() throws InterruptedException {

        Product p = null;
        Thread.sleep(1000);
        try {
            p = service.getProduct();
            logger.info("connected web service .... ok");
        }catch(Exception e){
            logger.info("exception ..." + e.getMessage());
            throw e;
        }
        return p;
    }
}
