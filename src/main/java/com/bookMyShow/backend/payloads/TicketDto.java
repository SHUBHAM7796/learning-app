package com.bookMyShow.backend.payloads;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {

    @NotBlank
    private String bookingId;

    private String id;

    @NotBlank
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$", message = "Invalid email address")
    private String email;

    @NotBlank
    private String movieName;

    @NotBlank
    private String poster;

    @NotBlank
    private String location;

    @NotBlank
    private String theaterName;

    @Size(min = 1, max = 4, message = "Select at least One Seat")
    private List<String> bookedSeats;

    @NotBlank
    private String showTime;

    @NotBlank
    private String food;

    @Positive
    @DecimalMin(value = "0.01", message = "Price Must be greater than 0")
    private BigDecimal price;

    @Positive
    @DecimalMin(value = "0.01", message = "Price Must be greater than 0")
    private BigDecimal totalPrice;

    @NotBlank
    private String cardNo;

    @NotBlank
    private String cardHolderName;

    @NotBlank
    private String expiryDate;

    private Integer v;
}
