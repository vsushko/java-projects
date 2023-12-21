package com.vsushko.webfluxdemo.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author vsushko
 */
@Data
@ToString
public class InputFailedValidationResponse {
    private int errorCode;
    private int input;
    private String message;
}
