package com.bookMyShow.backend.service.impl;

import com.bookMyShow.backend.payloads.MovieDto;
import com.bookMyShow.backend.entites.Movie;
import com.bookMyShow.backend.exception.ResourceNotFoundException;
import com.bookMyShow.backend.repository.MoviesRepository;
import com.bookMyShow.backend.service.Movieservice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoviesServiceImpl implements Movieservice {

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = moviesRepository.findAll();
        List<MovieDto> movieDtoList = movies.stream()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .collect(Collectors.toList());
        return movieDtoList;
    }

    @Override
    public List<MovieDto> findMovieByTitleContaining(String keyword) {
        List<Movie> movies = moviesRepository.findByMovieNameContaining(keyword)
                .orElseThrow(() -> new ResourceNotFoundException("Movie", "Movie Name", keyword));
        List<MovieDto> movieDtoList = movies.stream()
                .map(movie -> modelMapper.map(movie, MovieDto.class))
                .collect(Collectors.toList());
        return movieDtoList;
    }

    @Override
    public MovieDto createMovie(MovieDto movieDto) {

        Movie movie = dtoToMovies(movieDto);
        Movie createdMovie = moviesRepository.save(movie);
        return moviesToDto(createdMovie);
    }


    public Movie dtoToMovies(MovieDto moviesDto) {
        return modelMapper.map(moviesDto, Movie.class);
    }

    public MovieDto moviesToDto(Movie movies) {
        return modelMapper.map(movies, MovieDto.class);
    }

}