package com.bookMyShow.backend.repository;

import com.bookMyShow.backend.entites.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FoodRepository extends JpaRepository<FoodItem, String> {
    Optional<FoodItem> findByFoodPrice(Integer foodPrice);
}