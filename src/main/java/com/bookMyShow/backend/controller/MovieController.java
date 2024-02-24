package com.bookMyShow.backend.controller;

import com.bookMyShow.backend.payloads.MovieDto;
import com.bookMyShow.backend.service.Movieservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@Validated
public class MovieController {
	@Autowired
	private Movieservice movieservice;

	@PostMapping
	public ResponseEntity<MovieDto> createMovies(@Valid @RequestBody MovieDto movieDto) {
		MovieDto movies = movieservice.createMovie ( movieDto );
		return new ResponseEntity<MovieDto> ( movies, HttpStatus.CREATED );
	}

	@GetMapping
	public ResponseEntity<List<MovieDto>> getAllMovies(){
        List<MovieDto> allMovies = movieservice.getAllMovies();
        return new ResponseEntity<List<MovieDto>>(allMovies,HttpStatus.OK);
    }

	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<MovieDto>> getMovieByTitle(@PathVariable("keyword") String keyword) {
		List<MovieDto> moviesByTitleContaining = movieservice.findMovieByTitleContaining(keyword);
		return new ResponseEntity<List<MovieDto>>(moviesByTitleContaining, HttpStatus.OK);
	}
}