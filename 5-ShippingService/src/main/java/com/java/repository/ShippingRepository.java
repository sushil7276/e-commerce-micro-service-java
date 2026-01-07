package com.java.repository;

import com.java.models.ShippingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingRepository extends JpaRepository<ShippingInfo, Long> {
    ShippingInfo findByOrderId(Long orderId);
}
