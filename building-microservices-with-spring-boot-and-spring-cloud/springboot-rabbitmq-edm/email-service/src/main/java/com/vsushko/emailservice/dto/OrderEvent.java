package com.vsushko.emailservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author vsushko
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {
    // pending, progress, completed
    private String status;
    private String message;
    private Order order;
}