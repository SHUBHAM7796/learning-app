package com.bookMyShow.backend.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "card_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Card {
	@Id
	private String cardNumber;

	private String cardHolderName;

	private LocalDate expiryDate;

	@Transient
	private Integer cvv;

	@ManyToOne
	private User user;
}