package com.java.service;

import com.java.dto.OrderResponseDto;
import com.java.dto.PaymentRequestDto;
import com.java.dto.PaymentResponseDto;
import com.java.dto.UserDto;
import com.java.exception.ResourceNotFoundException;
import com.java.fignclients.OrderFeignClient;
import com.java.fignclients.UserFeignClient;
import com.java.models.Payment;
import com.java.repository.PaymentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderFeignClient orderFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) {
        UserDto userDto = userFeignClient.findById(paymentRequestDto.getUserId().intValue());
        if (userDto == null) {
            throw new ResourceNotFoundException("User not found with id: " + paymentRequestDto.getUserId());
        }

        OrderResponseDto orderById = orderFeignClient.getOrderById(paymentRequestDto.getOrderId());
        if (orderById == null) {
            throw new ResourceNotFoundException("Order not found with id: " + paymentRequestDto.getOrderId());
        }
// Todo: Uncomment these validations if needed

//        if (!Objects.equals(orderById.getUserId(), paymentRequestDto.getUserId())) {
//            // The user making the payment does not match the user who placed the order
//            throw new RuntimeException("User ID does not match with order ID: " + paymentRequestDto.getOrderId());
//        }
//
//        if (!Objects.equals(orderById.getTotalPrice(), paymentRequestDto.getAmount())) {
//            throw new RuntimeException("Order amount is not valid with id: " + paymentRequestDto.getOrderId());
//
//        }

        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentRequestDto, payment);
        payment.setStatus("SUCCESS");
        Payment savedPayment = paymentRepository.save(payment);
        return mapToDto(savedPayment);
    }


    private PaymentResponseDto mapToDto(Payment payment) {
        PaymentResponseDto dto = new PaymentResponseDto();
        BeanUtils.copyProperties(payment, dto);
        dto.setPaymentId(payment.getId());
        return dto;
    }
}
