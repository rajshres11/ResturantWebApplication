package com.project.foms.dto.orderdto;

import java.time.LocalDateTime;

import com.project.foms.enums.OrderStatus;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class PlaceOrderRequestDto {
    
    private LocalDateTime localDateTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    
}
