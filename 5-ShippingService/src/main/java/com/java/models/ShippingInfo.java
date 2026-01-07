package com.java.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "shipping_info")
public class ShippingInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long orderId;
    private String shippingMethod; // STANDARD, EXPRESS,NEXT_DAY
    private LocalDateTime shippedAt;
    private LocalDateTime deliveryDate;
    private String status; // SHIPPED, IN_TRANSIT ,DELIVERED
    private String carrier; // FedEx, UPS, DHL, BLUE_DART etc.
}
