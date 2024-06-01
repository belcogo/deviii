package com.unisinos.library.dto.response;

import lombok.Builder;

@Builder
public class ErrorMessageResponse {
    public String message;
    public String errorCode;
    public String field;
}
