package com.trymad.lootmarket.repository.paymentSystem;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trymad.lootmarket.model.PaymentSystem;

public interface PaymentSystemRepository extends JpaRepository <PaymentSystem, Long> {
    
}
