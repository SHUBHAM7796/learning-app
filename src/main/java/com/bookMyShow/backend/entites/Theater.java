package com.bookMyShow.backend.entites;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "theatre_name")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theater {

	@Id
	private String theaterName;

	private String location;

	@OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
	private Set<Show> shows = new HashSet<> ();

//    @OneToMany(mappedBy = "theater",cascade = CascadeType.ALL)
//    private List<Movies> movies = new ArrayList<> ();
}