package com.bookMyShow.backend.payloads;

import lombok.Data;

@Data
public class PaymentDto {
	private long id;
	private String cardHolderName;
	private String cardNumber;
	private String expiryDate;

	private BookingsDto bookingsDto;
}