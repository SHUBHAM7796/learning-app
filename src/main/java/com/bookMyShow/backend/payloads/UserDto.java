package com.bookMyShow.backend.payloads;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
	private int id;


	@NotBlank
	@Size(min = 3, message = "First name should have at least 3 characters")
//	@Pattern ( regexp = "^[A-Za-z]+$",message = "First name should only contain alphabets")
	private String firstName;


	@NotBlank
	@Size(min = 3, message = "Last name should have at least 3 characters")
//	@Pattern ( regexp = "^[A-Za-z]+$",message = "Last name should only contain alphabets")
	private String lastName;


	@NotNull
	@PastOrPresent
	private LocalDate dob;


	@NotBlank
//	@Pattern ( regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "Email should be valid")
	@Email(message = "Email should be valid")
	private String email;


	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
			message = "Password must contain at least one digit, one lowercase and uppercase letter, one special character, and no whitespaces")
	private String password;


}