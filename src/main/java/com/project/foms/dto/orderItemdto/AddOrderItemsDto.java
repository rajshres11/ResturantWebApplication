package com.project.foms.dto.orderItemdto;

import java.util.List;

import com.project.foms.entity.OrderItem;

public class AddOrderItemsDto {
    
    List<OrderItem> orderItems;

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    
}
