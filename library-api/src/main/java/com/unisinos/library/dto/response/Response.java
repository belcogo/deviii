package com.unisinos.library.dto.response;

import com.unisinos.library.dto.response.ErrorMessageResponse;
import lombok.Builder;

import java.util.List;

@Builder
public class Response<T> {
    public T body;
    public List<ErrorMessageResponse> errorAccumulators;
}
