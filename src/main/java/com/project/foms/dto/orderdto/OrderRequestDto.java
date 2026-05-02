package com.project.foms.dto.orderdto;

import java.util.List;

import com.project.foms.dto.orderItemdto.OrderItemRequestDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class OrderRequestDto {

    @NotNull(message = "Customer Id is required")
    private int CustomerId;

    @NotEmpty(message = "Order must have atleast one item")
    private List<OrderItemRequestDto> items;

    // Getter and Setter

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public List<OrderItemRequestDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestDto> items) {
        this.items = items;
    }

}
