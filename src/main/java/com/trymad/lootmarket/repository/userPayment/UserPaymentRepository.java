package com.trymad.lootmarket.repository.userPayment;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trymad.lootmarket.model.UserPayment;

public interface UserPaymentRepository extends JpaRepository<UserPayment, Long> {

    List<UserPayment> findByUserId(UUID id);

}
