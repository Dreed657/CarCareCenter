package com.Impl.DTOs;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private String message;
    private String[] details;
}
