package com.project.foms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.foms.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
