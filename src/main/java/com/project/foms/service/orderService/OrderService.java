package com.project.foms.service.orderService;

import java.util.List;

import com.project.foms.dto.orderdto.OrderRequestDto;
import com.project.foms.dto.orderdto.OrderResponseDto;
import com.project.foms.dto.orderdto.OrderStatusDto;

public interface OrderService {

    public OrderResponseDto placeOrder(OrderRequestDto o);

    public List<OrderResponseDto> getAllOrders();

    public OrderResponseDto getById(int orderId);

    public List<OrderResponseDto> getAllOrderByCustomer(int customerId);

    public OrderResponseDto updateOrderStatus(int orderId, OrderStatusDto o);

    public OrderResponseDto cancelOrder(int orderId);
    
}
