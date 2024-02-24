package com.bookMyShow.backend.controller;

import com.bookMyShow.backend.entites.User;
import com.bookMyShow.backend.exception.ApiException;
import com.bookMyShow.backend.payloads.JwtAuthRequest;
import com.bookMyShow.backend.payloads.JwtAuthResponse;
import com.bookMyShow.backend.payloads.UserDto;
import com.bookMyShow.backend.repository.UserRepository;
import com.bookMyShow.backend.security.JwtUtil;
import com.bookMyShow.backend.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/")
@Validated
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody JwtAuthRequest authenticationRequest) throws Exception {
        User user = new User();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
            user = userRepository.findByEmail(authenticationRequest.getUsername())
                    .orElseThrow(() -> new ApiException("Incorrect UserName or Password"));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect UserName or password");
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setAuth(jwt);
        response.setUser(modelMapper.map(user, UserDto.class));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
        UserDto newUser = userService.registerUser(userDto);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}
