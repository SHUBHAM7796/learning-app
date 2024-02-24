package com.bookMyShow.backend.repository;

import com.bookMyShow.backend.entites.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MoviesRepository extends JpaRepository<Movie, Integer> {
	 Optional<Movie> findByMovieName(String movieName) ;

	 Optional<List<Movie>> findByMovieNameContaining(String Keyword) ;

}