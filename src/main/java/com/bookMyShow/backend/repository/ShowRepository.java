package com.bookMyShow.backend.repository;

import com.bookMyShow.backend.entites.Movie;
import com.bookMyShow.backend.entites.Show;
import com.bookMyShow.backend.entites.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.Optional;

public interface ShowRepository extends JpaRepository<Show,Integer> {
    Optional<Show> findByShowTime(LocalTime showTime);

    Optional<Show> findByShowTimeAndTheaterAndMovie(LocalTime showTime, Theater theater, Movie movie);
}
