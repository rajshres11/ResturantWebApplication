package com.project.foms.dto.orderdto;

import com.project.foms.enums.OrderStatus;

import jakarta.validation.constraints.NotNull;

public class OrderStatusDto {

    @NotNull(message = "Order status cannot be empty")
    private OrderStatus orderStatus;

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

}
