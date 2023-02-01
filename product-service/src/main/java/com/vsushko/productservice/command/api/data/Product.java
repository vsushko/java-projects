package com.vsushko.productservice.command.api.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @author vsushko
 */
@Data
@Entity
public class Product {
    @Id
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
