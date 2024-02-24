package com.bookMyShow.backend.controller;

import com.bookMyShow.backend.payloads.BookingsDto;
import com.bookMyShow.backend.payloads.TicketDto;
import com.bookMyShow.backend.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    @Autowired
	private BookingService bookingService;


//    @PreAuthorize("hasRole('NORMAL')")
    @PostMapping("/pay-now")
    public ResponseEntity<BookingsDto>createBooking(@Valid @RequestBody BookingsDto bookingsDto){
        BookingsDto booking = bookingService.createBooking(bookingsDto);
        return new ResponseEntity<BookingsDto>(booking,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('NORMAL')")
    @PostMapping("/ticket")
    public ResponseEntity<TicketDto> createTicket(@Valid @RequestBody TicketDto ticketDto){
        TicketDto ticket = bookingService.createTicket(ticketDto);
        return new ResponseEntity<TicketDto>(ticket,HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/view-tickets")
	public ResponseEntity<List<TicketDto>> getAllBookings() {

		List<TicketDto> bookingsDtoList= bookingService.getAllTickets();
		return new ResponseEntity<List<TicketDto>>(bookingsDtoList, HttpStatus.OK);
	}

    @PreAuthorize("hasRole('NORMAL')")
    @GetMapping("/ticket-details/{userEmail}")
    public ResponseEntity<List<TicketDto>> getTicketByEmailName(@PathVariable ("userEmail") String userEmail){
        List<TicketDto> ticketByUserEmail = bookingService.getTicketByUserEmail(userEmail);
        return new ResponseEntity<List<TicketDto>>(ticketByUserEmail,HttpStatus.OK);
    }
}