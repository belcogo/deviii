package com.unisinos.library.dto.request;

import com.unisinos.library.dto.response.ErrorMessageResponse;
import com.unisinos.library.model.Role;
import com.unisinos.library.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRequest implements RequestBody {
    public String name;
    public String password;
    public String email;
    public String role;

    public User convertToUser() {
        return User.builder()
                .name(name)
                .email(email)
                .password(password)
                .role(Role.valueOf(role))
                .build();
    }

    @Override
    public List<ErrorMessageResponse> validate() {
        var erros = new ArrayList<ErrorMessageResponse>();

        if (name == null || name.isEmpty()) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("INVALID_REQUEST")
                    .field("name")
                    .message("Name must be provided")
                    .build();

            erros.add(error);
        }

        if (email == null || email.isEmpty()) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("INVALID_REQUEST")
                    .field("email")
                    .message("Email must be provided")
                    .build();

            erros.add(error);
        }

        if (password == null || password.isEmpty()) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("INVALID_REQUEST")
                    .field("author")
                    .message("Author must be provided")
                    .build();

            erros.add(error);
        }

        if (role == null || !Role.canCovert(role)) {
            var error = ErrorMessageResponse
                    .builder()
                    .errorCode("INVALID_REQUEST")
                    .field("role")
                    .message("Role must be provided or valid")
                    .build();

            erros.add(error);
        }

        return erros;
    }
}
