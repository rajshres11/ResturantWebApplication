package com.project.foms.dto.orderItemdto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemRequestDto {

    @NotNull(message = "ItemId is required")
    private int itemId;

    @Min(value = 1, message = "Quantity must be atleast one")
    private int quantity;

    // Getter and Setter

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
