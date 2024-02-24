package com.bookMyShow.backend.payloads;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
