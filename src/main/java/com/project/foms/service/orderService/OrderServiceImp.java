package com.project.foms.service.orderService;

import org.springframework.stereotype.Service;

import com.project.foms.repository.OrderRepository;

@Service
public class OrderServiceImp implements OrderService{
    
    private final OrderRepository repo;
    public OrderServiceImp (OrderRepository repo){
        this.repo=repo;
    }

    
}
