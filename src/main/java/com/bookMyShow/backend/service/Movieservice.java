package com.bookMyShow.backend.service;

import com.bookMyShow.backend.payloads.MovieDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Movieservice {
	List<MovieDto> getAllMovies();

	List<MovieDto> findMovieByTitleContaining(String keyword);

	MovieDto createMovie(MovieDto movieDto);
}