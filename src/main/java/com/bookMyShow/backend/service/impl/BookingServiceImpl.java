package com.bookMyShow.backend.service.impl;

import com.bookMyShow.backend.payloads.BookingsDto;
import com.bookMyShow.backend.payloads.TicketDto;
import com.bookMyShow.backend.entites.*;
import com.bookMyShow.backend.exception.ResourceNotFoundException;
import com.bookMyShow.backend.repository.*;
import com.bookMyShow.backend.service.BookingService;
import com.bookMyShow.backend.utility.UtilityMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BokkingRepository bookingsRepository;
    @Autowired
    private MoviesRepository moviesRepository;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UtilityMethods utilityMethods;

    @Override
    public BookingsDto createBooking(BookingsDto bookingsDto) {

        Bookings bookings = new Bookings();

        User user = userRepository.findByEmail(bookingsDto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User", "email", bookingsDto.getEmail()));
        bookings.setUser(user);

        Movie movie = moviesRepository.findByMovieName(bookingsDto.getMovieName()).orElseThrow(() -> new ResourceNotFoundException("Movie", "Movie Name", bookingsDto.getMovieName()));
        bookings.setMovie(movie);

        Show show = utilityMethods.createShow(bookingsDto);
        bookings.setShow(show);

        boolean allSeatsAvailable = new HashSet<>(show.getAvailableSeats()).containsAll(bookingsDto.getBookedSeats());
        if (allSeatsAvailable) {
            show.getAvailableSeats().removeAll(bookingsDto.getBookedSeats());
        } else {
            throw new ResourceNotFoundException(" Any One of this Seats are Not Available Please Try Again Later , Available Seats are" + show.getAvailableSeats());
        }

        bookings.setBookedSeats(bookingsDto.getBookedSeats());

        if (bookingsDto.getTotalPrice().intValue() == utilityMethods.calculateTotalPrice(bookingsDto)) {
            bookings.setPrice(bookingsDto.getPrice().doubleValue());
        } else {
            throw new ResourceNotFoundException("Total Price Mismatch");
        }

        Payment payment = utilityMethods.createPayment(bookingsDto);
        bookings.setPayment(payment);

        FoodItem food = utilityMethods.saveFoodItem(bookingsDto);
        bookings.setFood(food);

        Theater theater = theatreRepository.findById(bookingsDto.getTheaterName()).orElseThrow(() -> new ResourceNotFoundException("Theater", "Theater Name", bookingsDto.getTheaterName()));
        bookings.setTheater(theater);

        bookingsRepository.save(bookings);


        return bookingsToDto(bookings);
    }

    @Override
    public TicketDto createTicket(TicketDto ticketDto) {
        Bookings bookings = bookingsRepository.findById(Integer.valueOf(ticketDto.getBookingId()))
                .orElseThrow(() -> new ResourceNotFoundException("Booking", "Booking Id", ticketDto.getBookingId()));

        Payment payment = bookings.getPayment();
        if (!bookings.getBookedSeats().equals(ticketDto.getBookedSeats())) {
            throw new ResourceNotFoundException("Seats Doesn't Match");
        }
        if (payment != null) {
            payment.setCard(utilityMethods.createCard(ticketDto));
            paymentRepository.save(payment);
        } else {
            payment = utilityMethods.createPayment(ticketDto);
            payment.setBooking(bookings);
            paymentRepository.save(payment);
            bookings.setPayment(payment);
            bookingsRepository.save(bookings);
        }
        return bookingToDto1(bookings);
    }


    @Override
    public List<TicketDto> getTicketByUserEmail(String userEmail) {
        List<Bookings> byUserEmail = bookingsRepository.findByUserEmail(userEmail);
        if (userEmail.isEmpty()) {
            throw new ResourceNotFoundException("Ticket", "user Email", userEmail);
        } else {
            return byUserEmail.stream().map(bookings -> bookingToDto1(bookings)).collect(Collectors.toList());

        }

    }

    private BookingsDto bookingsToDto(Bookings bookings) {
        BookingsDto bookingsDto = new BookingsDto();

        bookingsDto.setEmail(bookings.getUser().getEmail());

        User user = userRepository.findByEmail(bookingsDto.getEmail()).orElseThrow(() -> new ResourceNotFoundException("User", "User ID", bookings.getUser().getEmail()));

        bookingsDto.setId(String.valueOf(UUID.randomUUID()));

        bookingsDto.setFirstName(bookings.getUser().getFirstName());

        bookingsDto.setMovieName(bookings.getMovie().getMovieName());

        bookingsDto.setPoster(bookings.getMovie().getPoster());

        bookingsDto.setLocation(bookings.getTheater().getLocation());

        bookingsDto.setTheaterName(bookings.getTheater().getTheaterName());

        bookingsDto.setBookedSeats(bookings.getBookedSeats());

        bookingsDto.setShowTime(bookings.getShow().getShowTime().format(DateTimeFormatter.ISO_LOCAL_TIME));

        bookingsDto.setFood(bookings.getFood().getName());

        bookingsDto.setPrice(BigDecimal.valueOf(bookings.getPrice()));

        bookingsDto.setTotalPrice(BigDecimal.valueOf(bookings.getPayment().getTotalPrice()));

        return bookingsDto;

    }

    private TicketDto bookingToDto1(Bookings bookings) {
        TicketDto ticketDto = new TicketDto();

        ticketDto.setBookingId(String.valueOf(UUID.randomUUID()));

        ticketDto.setEmail(bookings.getUser().getEmail());

        User user = userRepository.findByEmail(ticketDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User", "User ID", bookings.getUser().getEmail()));

        ticketDto.setId(String.valueOf(UUID.randomUUID()));

        ticketDto.setFirstName(bookings.getUser().getFirstName());
        ticketDto.setMovieName(bookings.getMovie().getMovieName());
        ticketDto.setPoster(bookings.getMovie().getPoster());

        ticketDto.setLocation(bookings.getTheater().getLocation());
        ticketDto.setTheaterName(bookings.getTheater().getTheaterName());
        ticketDto.setBookedSeats(bookings.getBookedSeats());

        ticketDto.setShowTime(bookings.getShow().getShowTime().format(DateTimeFormatter.ISO_LOCAL_TIME));
        ticketDto.setFood(bookings.getFood().getName());
        ticketDto.setPrice(BigDecimal.valueOf(bookings.getPrice()));
        ticketDto.setTotalPrice(BigDecimal.valueOf(bookings.getPayment().getTotalPrice()));
        ticketDto.setCardHolderName(bookings.getPayment().getCard().getCardHolderName());
        ticketDto.setCardNo(bookings.getPayment().getCard().getCardNumber());
        ticketDto.setExpiryDate(bookings.getPayment().getCard().getExpiryDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        ticketDto.setV(0);

        return ticketDto;
    }

    //    @Override
//    public List<TicketDto> getAllTickets() {
//        List<Bookings> ticketList = bookingsRepository.findAll();
//        if (ticketList.isEmpty()) {
//            return new ArrayList<TicketDto>();
//        } else {
//            List<TicketDto> ticketDtoList = ticketList
//                    .stream()
//                    .map(bookings -> bookingToDto1(bookings))
//                    .collect(Collectors.toList());
//
//            return ticketDtoList;
//        }
    @Override
    public List<TicketDto> getAllTickets() {
        List<Bookings> ticketList = bookingsRepository.findAll();

        return ticketList.stream()
                .map(this::bookingToDto1)
                .collect(Collectors.toList());
    }


}
