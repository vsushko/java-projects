package com.vsushko.productservice.command.api.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author vsushko
 */
@Data
@Builder
public class ProductRestModel {
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
