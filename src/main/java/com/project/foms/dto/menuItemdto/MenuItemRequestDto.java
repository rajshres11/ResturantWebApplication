package com.project.foms.dto.menuItemdto;

import jakarta.validation.constraints.PositiveOrZero;

public class MenuItemRequestDto {

    private String itemName;

    @PositiveOrZero(message = "Price must be in positive")
    private int price;

    private boolean availability;

    private int resturantId;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getResturantId() {
        return resturantId;
    }

    public void setResturantId(int resturantId) {
        this.resturantId = resturantId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

}
