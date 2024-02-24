package com.bookMyShow.backend.entites;

import com.bookMyShow.backend.utility.AppConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "shows")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalTime showTime;

    private List<String> availableSeats = new ArrayList<>(AppConstants.TOTAL_SEATS);

    @ManyToOne
    private Theater theater;

    @ManyToOne
    private Movie movie;
}