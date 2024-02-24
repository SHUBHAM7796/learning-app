package com.bookMyShow.backend.utility;

import com.bookMyShow.backend.payloads.BookingsDto;
import com.bookMyShow.backend.payloads.TicketDto;
import com.bookMyShow.backend.entites.*;
import com.bookMyShow.backend.exception.ResourceNotFoundException;
import com.bookMyShow.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Component
public class UtilityMethods {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private MoviesRepository moviesRepository;

    @Autowired
    private TheatreRepository theatreRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private CardRepository cardRepository;

    @Transactional
    public Payment createPayment(BookingsDto bookingsDto) {
        User user = userRepository.findByEmail(bookingsDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", bookingsDto.getEmail()));

        Payment payment = new Payment();
        payment.setTotalPrice(Double.valueOf(calculateTotalPrice(bookingsDto)));
        payment.setUser(user);
        paymentRepository.save(payment);
        return payment;
    }

    @Transactional
    public Payment createPayment(TicketDto ticketDto) {
        User user = userRepository.findByEmail(ticketDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User", "User ID", ticketDto.getEmail()));
        Payment payment = new Payment();
        payment.setTotalPrice(ticketDto.getTotalPrice().doubleValue());
        payment.setUser(user);
        payment.setCard(createCard(ticketDto));
        paymentRepository.save(payment);
        return payment;
    }

    @Transactional
    public Show createShow (TicketDto ticketDto){
        Movie movie = moviesRepository.findByMovieName(ticketDto.getMovieName())
                .orElseThrow(() -> new ResourceNotFoundException("Movie","Movie Name", ticketDto.getMovieName()));
        Theater theater = theatreRepository.findById(ticketDto.getTheaterName())
                .orElseThrow(()-> new ResourceNotFoundException("Theater","Theater Name", ticketDto.getTheaterName()));

        LocalTime showTime = LocalTime.parse(ticketDto.getShowTime());

        Show show =showRepository.findByShowTimeAndTheaterAndMovie(showTime,theater,movie)
                .orElseThrow(()->new ResourceNotFoundException("Show","Show time",ticketDto.getShowTime()));
        return show;
    }
    @Transactional
    public Show createShow (BookingsDto bookingsDto){
        Movie movie = moviesRepository.findByMovieName(bookingsDto.getMovieName())
                .orElseThrow(() -> new ResourceNotFoundException("Movie","Movie Name", bookingsDto.getMovieName()));
        Theater theater = theatreRepository.findById(bookingsDto.getTheaterName())
                .orElseThrow(()-> new ResourceNotFoundException("Theater","Theater Name", bookingsDto.getTheaterName()));

        LocalTime localTime1 = LocalTime.parse(bookingsDto.getShowTime());
        Optional<Show> optionalShow = showRepository.findByShowTime(localTime1);

        if (optionalShow.isPresent()){
            return optionalShow.get();
        }
        else {
            Show show = new Show();
            LocalTime localTime = LocalTime.parse(bookingsDto.getShowTime());
            show.setShowTime(localTime);
            show.setMovie(movie);
            show.setTheater(theater);
            showRepository.save(show);
            return show;
        }
    }

    @Transactional
    public Card createCard(TicketDto ticketDto){
        User user = userRepository.findByEmail(ticketDto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User", "User ID", ticketDto.getEmail()));

        Optional<Card> card  = cardRepository.findById(ticketDto.getCardNo());
        if (card.isPresent()){
            return card.get();
        }
        else {
            Card newCard =new Card();
            newCard.setCardNumber(ticketDto.getCardNo());
            newCard.setCardHolderName(ticketDto.getCardHolderName());
            newCard.setExpiryDate(LocalDate.parse(ticketDto.getExpiryDate()));
            newCard.setUser(user);
            cardRepository.save(newCard);
            return newCard;
        }
    }


    public Integer calculateFoodPrice(BookingsDto bookingsDto) {

        String[] array = bookingsDto.getFood().split("\\s");
        String s = array[array.length - 1];
        int foodPrice = Integer.parseInt(s);
        FoodItem food = foodRepository.findById(bookingsDto.getFood())
                .orElseThrow(() -> new ResourceNotFoundException("food", "food name", bookingsDto.getFood()));
        if (food.getFoodPrice() == foodPrice) {
            return foodPrice;

        } else {
            throw new ResourceNotFoundException("Food Price Does Not Match");
        }
    }

    @Transactional
    public FoodItem saveFoodItem(BookingsDto bookingsDto) {
        FoodItem foodItem = foodRepository.findById(bookingsDto.getFood())
                .orElseThrow(() -> new ResourceNotFoundException("Food", "Food Name", bookingsDto.getFood()));
        return foodItem;
    }


    public Integer calculateSeatPrice(BookingsDto bookingsDto) {
        Integer priceOfBookedSeat = bookingsDto.getBookedSeats().stream().map(seat -> AppConstants.SILVER.contains(seat) ? AppConstants.SILVER_SEAT_PRICE :
                AppConstants.GOLD.contains(seat) ? AppConstants.GOLD_SEAT_PRICE : AppConstants.PLATINUM.contains(seat) ? AppConstants.PLATINUM_SEAT_PRICE :
                        AppConstants.DIAMOND.contains(seat) ? AppConstants.DIAMOND_SEAT_PRICE : 0).reduce(0, Integer::sum);
        return priceOfBookedSeat;
    }


    public Integer calculateTotalPrice(BookingsDto bookingsDto) {
        return (calculateSeatPrice(bookingsDto) + calculateFoodPrice(bookingsDto));

    }
}

