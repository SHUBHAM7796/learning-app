package com.bookMyShow.backend.repository;

import com.bookMyShow.backend.entites.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,String> {

}