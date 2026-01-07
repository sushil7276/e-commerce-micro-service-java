package com.java.service;

import com.java.dto.PaymentRequestDto;
import com.java.dto.PaymentResponseDto;

public interface PaymentService {

    PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto);

}
