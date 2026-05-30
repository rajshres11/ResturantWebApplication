package com.project.foms.dto.orderdto;

import java.util.List;

import com.project.foms.dto.orderItemdto.OrderItemRequestDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class OrderRequestDto {

    @NotNull(message = "Customer Id is required")
    private Integer customerId;

    @Valid
    @NotEmpty(message = "Order must have atleast one item")
    private List<OrderItemRequestDto> items;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemRequestDto> getItems() {
        return items;
    }

    public void setItems(List<OrderItemRequestDto> items) {
        this.items = items;
    }
}