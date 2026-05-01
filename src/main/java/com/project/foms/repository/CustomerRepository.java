package com.project.foms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.foms.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
    
    Optional<Customer> findByContact(int contact);
    boolean existsByEmail(String email);
    boolean existsByContact(String contact);
}
