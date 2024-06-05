package com.unisinos.library.dto.request;

import com.unisinos.library.dto.response.ErrorMessageResponse;
import com.unisinos.library.model.BorrowStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BorrowUpdateRequest {
    public String borrowStatus;
    public Long id;

    public List<ErrorMessageResponse> validate() {
        var errors = new ArrayList<ErrorMessageResponse>();

        if (borrowStatus != null && !BorrowStatus.canCovert(borrowStatus)) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("INVALID_REQUEST")
                    .field("borrowStatus")
                    .message("Borrow status provided must be valid.")
                    .build();

            errors.add(error);
        }

        if (Objects.isNull(id)) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("INVALID_REQUEST")
                    .field("id")
                    .message("Id must be provided.")
                    .build();

            errors.add(error);
        }

        return errors;
    }
}
