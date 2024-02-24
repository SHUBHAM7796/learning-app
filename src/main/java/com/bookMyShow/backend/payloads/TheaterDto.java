package com.bookMyShow.backend.payloads;

import lombok.Data;

import java.util.List;

@Data
public class TheaterDto {
	private int id;
	private String theaterName;
	private String location;

	private List<MovieDto> movieDtos;
}