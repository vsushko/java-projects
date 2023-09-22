package com.vsushko.edmcore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vsushko
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {

    private String message;

    private String status;

    private Order order;
}
