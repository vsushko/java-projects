package com.vsushko.webfluxdemo.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author vsushko
 */
@Data
@ToString
public class MultiplyRequestDto {
    private int first;
    private int second;
}
