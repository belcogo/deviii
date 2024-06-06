package com.unisinos.library.dto.request;

import com.unisinos.library.dto.response.ErrorMessageResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageUpdateRequest {
    public List<Long> idMessages;

    public List<ErrorMessageResponse> validate() {
        var errors = new ArrayList<ErrorMessageResponse>();

        if (Objects.isNull(idMessages) || idMessages.isEmpty()) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("INVALID_REQUEST")
                    .field("idMessages")
                    .message("Messages must be provided.")
                    .build();

            errors.add(error);
        }

        return errors;
    }
}
