package com.project.foms.service.orderItemService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.project.foms.dto.orderItemdto.AddOrderItemsDto;
import com.project.foms.dto.orderItemdto.OrderItemRequestDto;
import com.project.foms.dto.orderItemdto.OrderItemResponseDto;
import com.project.foms.dto.orderItemdto.UpdateQuantityDto;
import com.project.foms.dto.orderdto.OrderResponseDto;
import com.project.foms.entity.MenuItem;
import com.project.foms.entity.Order;
import com.project.foms.entity.OrderItem;
import com.project.foms.enums.OrderStatus;
import com.project.foms.repository.MenuItemRepository;
import com.project.foms.repository.OrderRepository;

@Service
public class OrderItemServiceImp implements OrderItemService {

    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;

    public OrderItemServiceImp(OrderRepository orderRepository,
            MenuItemRepository menuItemRepository) {
        this.orderRepository = orderRepository;
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    @Transactional
    public OrderResponseDto addItemInExistingOrder(int orderId, AddOrderItemsDto newOrder) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such order"));
        if (existingOrder.getOrderStatus() == OrderStatus.DELIVERED ||
                existingOrder.getOrderStatus() == OrderStatus.CANCELED) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot modify this order");
        }
        // int newTotalAmount = 0;
        List<OrderItem> existingOrderItems = existingOrder.getOrderItems();
        // List<OrderItem> newOrderItems = new ArrayList<>();
        int oldTotalAmount = existingOrder.getTotalAmount();
        for (OrderItemRequestDto orderItem : newOrder.getOrderItems()) {

            MenuItem menuItem = menuItemRepository.findById(orderItem.getItemId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such item"));
            if (!menuItem.isAvailability()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item is not avaialable");
            }
            OrderItem orderItemorg = new OrderItem();
            orderItemorg.setMenuItem(menuItem);
            if (orderItem.getQuantity() < 1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity must be atleat 1");
            }
            orderItemorg.setQuantity(orderItem.getQuantity());
            orderItemorg.setOrder(existingOrder);
            int subtotal = menuItem.getPrice() * orderItem.getQuantity();
            orderItemorg.setSubTotal(subtotal);

            oldTotalAmount += subtotal;
            existingOrderItems.add(orderItemorg);
        }
        existingOrder.setOrderItems(existingOrderItems);
        existingOrder.setTotalAmount(oldTotalAmount);

        Order saved = orderRepository.save(existingOrder);

        OrderResponseDto response = new OrderResponseDto();
        response.setOrderId(saved.getOrderId());
        response.setTotalAmount(saved.getTotalAmount());
        response.setOrderStatus(saved.getOrderStatus());
        List<OrderItemResponseDto> oiList = new ArrayList<>();
        for (OrderItem oi : saved.getOrderItems()) {
            OrderItemResponseDto orderItemResponse = new OrderItemResponseDto();
            orderItemResponse.setOrderItemId(oi.getOrderItemId());
            orderItemResponse.setItemName(oi.getMenuItem().getItemName());
            orderItemResponse.setPrice(oi.getMenuItem().getPrice());
            orderItemResponse.setQuantity(oi.getQuantity());
            orderItemResponse.setSubTotal(oi.getSubTotal());
            oiList.add(orderItemResponse);
        }
        response.setItems(oiList);

        return response;
    }

    public OrderResponseDto updateOrderItemQuantity(int orderId, UpdateQuantityDto orderRequest) {
        Order existingOrder = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No such order"));
        if (existingOrder.getOrderStatus() == OrderStatus.DELIVERED ||
                existingOrder.getOrderStatus() == OrderStatus.CANCELED) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot modify this order");
        }
        List<OrderItem> existingOrderItems = existingOrder.getOrderItems();
        List<OrderItem> newOrderItems = new ArrayList<>();
        int newTotal = 0;
        for (OrderItemRequestDto orderItem : orderRequest.getOrderItems()) {
            MenuItem menuItem = menuItemRepository.findById(orderItem.getItemId()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "This item is not listed"));
            if(!menuItem.isAvailability()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This item is not avaialble");
            }
            
    }
}
