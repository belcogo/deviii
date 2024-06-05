package com.unisinos.library.dto.request;

import com.unisinos.library.dto.response.ErrorMessageResponse;
import com.unisinos.library.model.BorrowStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BorrowRequest {
    public Long idBook;

    public List<ErrorMessageResponse> validate() {
        var errors = new ArrayList<ErrorMessageResponse>();

        if (Objects.isNull(idBook)) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("INVALID_REQUEST")
                    .field("idBook")
                    .message("Id book request must be provided.")
                    .build();

            errors.add(error);
        }

        return errors;
    }
}
