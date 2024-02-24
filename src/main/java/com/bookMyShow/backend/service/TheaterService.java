package com.bookMyShow.backend.service;

import com.bookMyShow.backend.entites.Theater;

public interface TheaterService {
	Theater createTheater(Theater theater);

	Theater getTheater(Integer theaterId);
}