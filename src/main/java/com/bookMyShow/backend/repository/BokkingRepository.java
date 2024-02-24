package com.bookMyShow.backend.repository;

import com.bookMyShow.backend.entites.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BokkingRepository extends JpaRepository<Bookings, Integer> {
    List<Bookings> findByUserEmail(String email);
}