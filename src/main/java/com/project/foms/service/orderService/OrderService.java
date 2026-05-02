package com.project.foms.service.orderService;

import java.util.List;

import com.project.foms.dto.orderdto.OrderRequestDto;
import com.project.foms.dto.orderdto.OrderResponseDto;

public interface OrderService {

    public OrderResponseDto placeOrder(OrderRequestDto o);

    public List<OrderResponseDto> getAllOrders();
}
