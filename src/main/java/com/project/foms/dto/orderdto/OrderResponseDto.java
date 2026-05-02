package com.project.foms.dto.orderdto;

import java.util.List;

import com.project.foms.dto.orderItemdto.OrderItemResponseDto;
import com.project.foms.enums.OrderStatus;

public class OrderResponseDto {

    private int orderId;

    private int totalAmount;

    private OrderStatus orderStatus;

    private List<OrderItemResponseDto> items;

    // Getter and Setter

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<OrderItemResponseDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemResponseDto> items) {
        this.items = items;
    }

}
