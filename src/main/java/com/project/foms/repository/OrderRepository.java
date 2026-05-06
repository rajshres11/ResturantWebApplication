package com.project.foms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.foms.entity.Order;
import com.project.foms.enums.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByCustomerCustomerId(int customerId);

    List<Order> findByOrderStatus(OrderStatus orderStatus);
}
