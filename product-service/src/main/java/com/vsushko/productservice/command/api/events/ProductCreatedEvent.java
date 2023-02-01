package com.vsushko.productservice.command.api.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

/**
 * @author vsushko
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreatedEvent {
    @TargetAggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
