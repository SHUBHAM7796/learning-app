package com.bookMyShow.backend.service;

import com.bookMyShow.backend.payloads.BookingsDto;
import com.bookMyShow.backend.payloads.TicketDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookingService {
    BookingsDto createBooking(BookingsDto bookingsDto);

    TicketDto createTicket(TicketDto ticketDto);

    List<TicketDto> getAllTickets();

    List<TicketDto> getTicketByUserEmail(String userEmail);
}