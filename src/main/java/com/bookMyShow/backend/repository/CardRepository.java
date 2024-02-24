package com.bookMyShow.backend.repository;

import com.bookMyShow.backend.entites.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,String> {

}
