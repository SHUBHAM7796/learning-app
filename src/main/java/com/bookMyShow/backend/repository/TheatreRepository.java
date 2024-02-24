package com.bookMyShow.backend.repository;

import com.bookMyShow.backend.entites.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheatreRepository extends JpaRepository<Theater,String> {

}