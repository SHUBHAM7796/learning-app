package com.bookMyShow.backend.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "food_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {
	@Id
	private String name;
	private double foodPrice;

//	@OneToMany(mappedBy = "food")
//	private List<Bookings> booking = new ArrayList<> ();


}