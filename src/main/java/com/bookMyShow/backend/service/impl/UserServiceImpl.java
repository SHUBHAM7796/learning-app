package com.bookMyShow.backend.service.impl;

import com.bookMyShow.backend.payloads.UserDto;
import com.bookMyShow.backend.entites.User;
import com.bookMyShow.backend.repository.UserRepository;
import com.bookMyShow.backend.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	public UserDto registerUser(UserDto userDto) {


		User user = this.modelMapper.map ( userDto,User.class );
		user.setPassword(this.bCryptPasswordEncoder.encode(userDto.getPassword()));

		User newUser = this.userRepository.save ( user );
		return this.modelMapper.map ( newUser,UserDto.class );
	}



	public User dtoToUser(UserDto userDto) {
		return modelMapper.map ( userDto, User.class );
	}

	public UserDto userToDto(User user) {
		return modelMapper.map ( user, UserDto.class );
	}
}