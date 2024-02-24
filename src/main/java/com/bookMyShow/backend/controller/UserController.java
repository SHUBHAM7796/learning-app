package com.bookMyShow.backend.controller;

import com.bookMyShow.backend.payloads.UserDto;
import com.bookMyShow.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/register")
@Validated
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Build a map of field errors for a clearer response
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }

            // Create a custom error message based on your requirements
            StringBuilder errorMessage = new StringBuilder("Validation Error: ");
            errors.forEach((field, message) -> errorMessage.append(field).append(" - ").append(message).append("; "));

            return ResponseEntity.badRequest().body(errorMessage.toString());
        }

        try {
            userService.registerUser(userDto);
            return new ResponseEntity<>("User Registered", HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Email Already Registered", HttpStatus.CONFLICT);
        }
    }
}