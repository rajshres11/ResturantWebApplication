package com.project.foms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.foms.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
    
    Customer findByContact(int contact);
}
