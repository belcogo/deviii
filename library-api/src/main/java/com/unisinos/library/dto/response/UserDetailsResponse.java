package com.unisinos.library.dto.response;

import lombok.Builder;

@Builder
public class UserDetailsResponse {
    public String name;
    public String email;
    public Long id;
    public int score = 0;
}
