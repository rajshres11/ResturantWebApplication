package com.project.foms.service.orderService;

import java.time.LocalDateTime;
import java.util.List;

import com.project.foms.dto.orderdto.OrderRequestDto;
import com.project.foms.dto.orderdto.OrderResponseDto;
import com.project.foms.dto.orderdto.OrderStatusDto;
import com.project.foms.enums.OrderStatus;

public interface OrderService {

    public OrderResponseDto placeOrder(OrderRequestDto o);

    public List<OrderResponseDto> getAllOrders();

    public OrderResponseDto getById(int orderId);

    public List<OrderResponseDto> getAllOrderByCustomer(int customerId);

    public OrderResponseDto updateOrderStatus(int orderId, OrderStatusDto o);

    public OrderResponseDto cancelOrder(int orderId);
    
    public List<OrderResponseDto> getOrderByStatus(OrderStatus status);

    public List<OrderResponseDto> getOrderByDate(LocalDateTime localDateTime);

    public List<OrderResponseDto> getOrderByTotalAmount(int minAmount,int maxAmount);

    public List<OrderResponseDto> getOrdersByResturant(int resturantId);
}
