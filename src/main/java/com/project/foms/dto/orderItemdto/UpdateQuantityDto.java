package com.project.foms.dto.orderItemdto;

import java.util.List;

public class UpdateQuantityDto {

    private List<OrderItemRequestDto> orderItems;

    public List<OrderItemRequestDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemRequestDto> orderItems) {
        this.orderItems = orderItems;
    }
    
}
