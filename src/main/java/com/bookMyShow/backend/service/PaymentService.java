package com.bookMyShow.backend.service;

import com.bookMyShow.backend.entites.Payment;

public interface PaymentService {
	Payment createPayment(Payment payment);

	Payment getPayment(Integer paymentId);
}