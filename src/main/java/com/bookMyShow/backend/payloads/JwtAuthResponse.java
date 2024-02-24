package com.bookMyShow.backend.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthResponse {
    private UserDto user;
    private String auth;
}
