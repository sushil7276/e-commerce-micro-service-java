package com.java.model;

import java.util.Arrays;

public enum OrderStatus {
    STATUS_PLACED("PLACED"),
    STATUS_SHIPPED("SHIPPED"),
    STATUS_CANCELLED("CANCELLED"),
    STATUS_RETURNED("RETURNED"),
    STATUS_DELIVERED("DELIVERED");

    private String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static OrderStatus from(String value) {
        return Arrays.stream(values())
                .filter(s -> s.status.equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid order status: " + value));

    }
}
