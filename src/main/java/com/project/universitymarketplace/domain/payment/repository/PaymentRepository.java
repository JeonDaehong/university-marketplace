package com.project.universitymarketplace.domain.payment.repository;

import com.project.universitymarketplace.domain.payment.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository <Payment, Long> { }
