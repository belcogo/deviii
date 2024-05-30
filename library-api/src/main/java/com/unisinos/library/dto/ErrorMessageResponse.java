package com.unisinos.library.dto;

import lombok.Builder;

@Builder
public class ErrorMessageResponse {
    public String message;
    public String errorCode;
}
