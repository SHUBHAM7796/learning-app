package com.bookMyShow.backend.service;

import com.bookMyShow.backend.payloads.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService{
	UserDto registerUser(UserDto userDto);
}