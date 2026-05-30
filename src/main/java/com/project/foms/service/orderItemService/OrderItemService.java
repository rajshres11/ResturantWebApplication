package com.project.foms.service.orderItemService;

import com.project.foms.dto.orderItemdto.AddOrderItemsDto;
import com.project.foms.dto.orderItemdto.UpdateQuantityDto;
import com.project.foms.dto.orderdto.OrderResponseDto;

public interface OrderItemService {
    
    public OrderResponseDto addItemInExistingOrder(int orderId, AddOrderItemsDto newOrder);

    public OrderResponseDto updateItemQuantity(int orderItemId, UpdateQuantityDto updateQuantityDto);
}
