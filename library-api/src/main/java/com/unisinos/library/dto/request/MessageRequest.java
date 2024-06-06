package com.unisinos.library.dto.request;

import com.unisinos.library.dto.response.ErrorMessageResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageRequest {
    public String message;
    public Long idReceiver;

    public List<ErrorMessageResponse> validate() {
        var errors = new ArrayList<ErrorMessageResponse>();

        if (Objects.isNull(message) || message.isEmpty()) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("INVALID_REQUEST")
                    .field("message")
                    .message("Message must be provided.")
                    .build();

            errors.add(error);
        }

        if (Objects.isNull(idReceiver)) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("INVALID_REQUEST")
                    .field("idReceiver")
                    .message("Id Receiver must be provided.")
                    .build();

            errors.add(error);
        }

        return errors;
    }
}
