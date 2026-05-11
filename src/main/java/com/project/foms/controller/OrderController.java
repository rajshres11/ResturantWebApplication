package com.project.foms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.foms.service.orderService.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    
    private final OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    
}
