package com.project.foms.dto.orderItemdto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemRequestDto {

    @NotNull(message = "ItemId is required")
    private Integer itemId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Quantity must be atleast one")
    private Integer quantity;

    // Getter and Setter

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}
