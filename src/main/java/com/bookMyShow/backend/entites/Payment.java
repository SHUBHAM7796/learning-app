package com.bookMyShow.backend.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private double totalPrice;


	@OneToOne
	private Bookings booking;

	@ManyToOne
	private User user;

	@ManyToOne
	private Card card;
}