package com.bookMyShow.backend.payloads;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;


@Data
public class MovieDto {
	private int id;

	@NotBlank
	private String movieName;

	@NotBlank
	private String poster;

	@NotNull
	private LocalDate releaseDate;

	@NotBlank
	private String category;

	@NotBlank
	private String genre;

	@NotNull(message = "Film Rating Cannot be null")
	@DecimalMin(value = "0.0",inclusive = true,message = "Film Rating Must Be greater than or equal to 0.0")
	@DecimalMax(value = "10.0", inclusive = true, message = "Film rating must be less than or equal to 5.0")
	private float rating;

	@NotBlank
	private String country;

	private Integer v;


//	private List<Bookings> bookings;
//	public List<Bookings> getBookings() {
//		return bookings;
//	}
//
//	public void setBookings(List<Bookings> bookings) {
//		this.bookings = bookings;
//	}
//
//	private TheaterDto theater;
}