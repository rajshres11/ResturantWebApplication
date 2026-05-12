package com.project.foms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.foms.dto.ApiResponse;
import com.project.foms.dto.orderdto.OrderRequestDto;
import com.project.foms.dto.orderdto.OrderResponseDto;
import com.project.foms.service.orderService.OrderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/order")
public class OrderController {
    
    
    private final OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<OrderResponseDto>> placeOrder(@RequestBody OrderRequestDto o){
        OrderResponseDto order = orderService.placeOrder(o);
        ApiResponse<OrderResponseDto> response = new ApiResponse<>(HttpStatus.CREATED.value(),"Order created sussesfully",order);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
    
    
}
