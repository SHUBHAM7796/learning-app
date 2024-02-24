package com.bookMyShow.backend.utility;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppConstants {
    public static final List<String> DIAMOND = Arrays.asList("A1","A2","A3","A4","A5","A6","A7","A8","A9","A10","A11","A12");
    public static final List<String> PLATINUM = Arrays.asList("B1","B2","B3","B4","B5","B6","B7","B8","B9","B10","B11","B12",
            "C1","C2","C3","C4","C5","C6","C7","C8","C9","C10","C11","C12");
    public static final List<String> GOLD = Arrays.asList("D1","D2","D3","D4","D5","D6","D7","D8","D9","D10","D11","D12",
            "E1","E2","E3","E4","E5","E6","E7","E8","E9","E10","E11","E12",
            "F1","F2","F3","F4","F5","F6","F7","F8","F9","F10","F11","F12");
    public static final List<String> SILVER = Arrays.asList("G1","G2","G3","G4","G5","G6","G7","G8","G9","G10","G11","G12");

    public static final List<String> TOTAL_SEATS = Stream.of(DIAMOND,PLATINUM,GOLD,SILVER)
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

    public static final Integer SILVER_SEAT_PRICE =300;
    public static final Integer GOLD_SEAT_PRICE =500;
    public static final Integer PLATINUM_SEAT_PRICE =800;
    public static final Integer DIAMOND_SEAT_PRICE =1000;

    public static final String ADMIN_USER="ROLE_ADMIN";
    public static final int ADMIN_USER_ID=501;
    public static final String NORMAL_USER="ROLE_NORMAL";
    public static final int NORMAL_USER_ID=502;

    public static final String [] PUBLIC_URLS={
            "/v3/api-docs",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/webjars/**",
            "/api/v1/auth/**",
            "/api/v1/auth/register",
            "/api/v1/auth/login"
    };
    public static final String AUTHORIZATION_HEADER = "Authorization";

}