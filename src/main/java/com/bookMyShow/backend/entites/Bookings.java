package com.bookMyShow.backend.entites;

import jakarta.persistence.*;
import jdk.dynalink.linker.LinkerServices;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bookings {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private List<String> bookedSeats;

	private double price;



	@ManyToOne
	private User user;

	@ManyToOne
	private Movie movie;

	@ManyToOne
	private Theater theater;

	@OneToOne
	private Payment payment;

	@ManyToOne
	private Show show;

	@ManyToOne
	private FoodItem food;
}