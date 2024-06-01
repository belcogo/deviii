package com.unisinos.library.dto.request;

import com.unisinos.library.dto.response.ErrorMessageResponse;

import java.util.List;

public interface RequestBody {
    List<ErrorMessageResponse> validate();
}
