package com.bookMyShow.backend.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String movieName;
	private String poster;
	private LocalDate releaseDate;

	private String category;

	private String genre;
	private float rating;
	private String country;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private List<Show> shows = new ArrayList<> ();

//	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
//	private List<Bookings> bookings;
//
//	//    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
////    private List<Bookings> bookings;
////
//	@ManyToOne
//	@JoinColumn(name = "theater_id")
//	private Theater theater;
}