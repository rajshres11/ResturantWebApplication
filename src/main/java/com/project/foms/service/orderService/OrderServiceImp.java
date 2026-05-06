package com.project.foms.service.orderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.project.foms.dto.orderItemdto.OrderItemRequestDto;
import com.project.foms.dto.orderItemdto.OrderItemResponseDto;
import com.project.foms.dto.orderdto.OrderRequestDto;
import com.project.foms.dto.orderdto.OrderResponseDto;
import com.project.foms.dto.orderdto.OrderStatusDto;
import com.project.foms.entity.Customer;
import com.project.foms.entity.MenuItem;
import com.project.foms.entity.Order;
import com.project.foms.entity.OrderItem;
import com.project.foms.enums.OrderStatus;
import com.project.foms.repository.CustomerRepository;
import com.project.foms.repository.MenuItemRepository;
import com.project.foms.repository.OrderRepository;

@Service
public class OrderServiceImp implements OrderService {

    private final OrderRepository repo;
    private final CustomerRepository repoc;
    private final MenuItemRepository repom;

    public OrderServiceImp(OrderRepository repo, CustomerRepository repoc, MenuItemRepository repom) {
        this.repo = repo;
        this.repoc = repoc;
        this.repom = repom;
    }

    @Override
    @Transactional
    public OrderResponseDto placeOrder(OrderRequestDto o) {
        Order order = new Order();

        // Checking customer is present or not.
        Customer c = repoc.findById(o.getCustomerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no such customer"));
        order.setCustomer(c);

        // Checking items list is empty or not.
        if (o.getItems().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Need to add item");
        }

        // Every order have list of orderItems.
        List<OrderItem> orderItems = new ArrayList<>();

        // Total amount of all orderItems.
        int totalAmount = 0;

        // Fetching list of orderItems and adding to orderItem as well as adding those
        // items in orderItems List.
        for (OrderItemRequestDto orderItemRequestDto : o.getItems()) {

            // Checking enter menu itemId is exist or not.
            MenuItem menuItem = repom.findById(orderItemRequestDto.getItemId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such item"));

            // Checking item is available or not.
            if (!menuItem.isAvailability()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Item is not avaialable");
            }

            OrderItem orderItem = new OrderItem();
            orderItem.setMenuItem(menuItem);

            // Checking orderItem have atleast one in quantity.
            if (orderItemRequestDto.getQuantity() < 1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantity must be atleat 1");
            }
            orderItem.setQuantity(orderItemRequestDto.getQuantity());
            orderItem.setOrder(order);

            // Total per orderItem.
            int subtotal = menuItem.getPrice() * orderItemRequestDto.getQuantity();
            orderItem.setSubTotal(subtotal);

            // Total of all orderItem total.
            totalAmount += subtotal;
            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);
        order.setOrderStatus(OrderStatus.CREATED);
        order.setLocalDateTime(LocalDateTime.now());

        Order saved = repo.save(order);

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

    @Override
    public List<OrderResponseDto> getAllOrders() {
        List<Order> orders = repo.findAll();
        List<OrderResponseDto> responseList = new ArrayList<>();
        for (Order o : orders) {
            OrderResponseDto response = new OrderResponseDto();
            response.setOrderId(o.getOrderId());
            response.setOrderStatus(o.getOrderStatus());
            response.setTotalAmount(o.getTotalAmount());
            List<OrderItem> orderItemList = o.getOrderItems();
            List<OrderItemResponseDto> orderItemResponse = new ArrayList<>();
            for (OrderItem oi : orderItemList) {
                OrderItemResponseDto oiResponse = new OrderItemResponseDto();
                oiResponse.setOrderItemId(oi.getOrderItemId());
                oiResponse.setItemName(oi.getMenuItem().getItemName());
                oiResponse.setPrice(oi.getMenuItem().getPrice());
                oiResponse.setQuantity(oi.getQuantity());
                oiResponse.setSubTotal(oi.getSubTotal());
                orderItemResponse.add(oiResponse);
            }
            response.setItems(orderItemResponse);
            responseList.add(response);
        }
        return responseList;
    }

    @Override
    public OrderResponseDto getById(int orderId) {
        Order order = repo.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order is not available."));
        OrderResponseDto response = new OrderResponseDto();
        response.setOrderId(order.getOrderId());
        response.setOrderStatus(order.getOrderStatus());
        response.setTotalAmount(order.getTotalAmount());
        List<OrderItemResponseDto> orderItemsResponse = new ArrayList<>();
        List<OrderItem> orderItems = order.getOrderItems();
        for (OrderItem oi : orderItems) {
            OrderItemResponseDto oiResponse = new OrderItemResponseDto();
            oiResponse.setOrderItemId(oi.getOrderItemId());
            oiResponse.setItemName(oi.getMenuItem().getItemName());
            oiResponse.setPrice(oi.getMenuItem().getPrice());
            oiResponse.setQuantity(oi.getQuantity());
            oiResponse.setSubTotal(oi.getSubTotal());
            orderItemsResponse.add(oiResponse);
        }
        response.setItems(orderItemsResponse);
        return response;
    }

    @Override
    public List<OrderResponseDto> getAllOrderByCustomer(int customerId) {

        List<Order> orders = repo.findByCustomerCustomerId(customerId);
        // Unnecessary but for extra checks.
        if (orders.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.OK, "No items in order");
        }
        List<OrderResponseDto> responseList = new ArrayList<>();
        for (Order o : orders) {
            OrderResponseDto response = new OrderResponseDto();
            response.setOrderId(o.getOrderId());
            response.setOrderStatus(o.getOrderStatus());
            response.setTotalAmount(o.getTotalAmount());
            List<OrderItem> orderItemList = o.getOrderItems();
            List<OrderItemResponseDto> orderItemResponse = new ArrayList<>();
            for (OrderItem oi : orderItemList) {
                OrderItemResponseDto oiResponse = new OrderItemResponseDto();
                oiResponse.setOrderItemId(oi.getOrderItemId());
                oiResponse.setItemName(oi.getMenuItem().getItemName());
                oiResponse.setPrice(oi.getMenuItem().getPrice());
                oiResponse.setQuantity(oi.getQuantity());
                oiResponse.setSubTotal(oi.getSubTotal());
                orderItemResponse.add(oiResponse);
            }
            response.setItems(orderItemResponse);
            responseList.add(response);
        }
        return responseList;
    }

    @Override
    @Transactional
    public OrderResponseDto updateOrderStatus(int orderId, OrderStatusDto o) {
        Order order = repo.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order is not available"));

        OrderStatus status = o.getOrderStatus();

        // if orderstatus is empty that is also not allowed.
        if (status == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order status is required");
        }
        // Cancelling the order is not allowed goto cancel method.
        if (status == OrderStatus.CANCELED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not allowed in this method");
        }
        // created -> delivered not allowed directly.
        if (order.getOrderStatus() == OrderStatus.CREATED && status == OrderStatus.DELIVERED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid status transition");
        }
        // if order is cancel or delivered we cant update the order further.
        if (order.getOrderStatus() == OrderStatus.DELIVERED || order.getOrderStatus() == OrderStatus.CANCELED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot update item");
        }
        // after validation orderstatus is updating.
        order.setOrderStatus(status);
        // Order saved = repo.save(order); --> Instead of this i am using @Transactional
        // annotation for auto update by Jpa.

        OrderResponseDto response = new OrderResponseDto();
        response.setOrderId(order.getOrderId());
        response.setOrderStatus(order.getOrderStatus());
        response.setTotalAmount(order.getTotalAmount());
        return response;
    }

    @Override
    @Transactional
    public OrderResponseDto cancelOrder(int orderId) {
        Order order = repo.findById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order is not in record"));

        if (order.getOrderStatus() == OrderStatus.CANCELED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Already canceled");
        }
        if (order.getOrderStatus() == OrderStatus.PREPARING) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Order is already being prepared, cannot cancel");
        }
        if (order.getOrderStatus() == OrderStatus.DELIVERED) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Order is already delivered");
        }
        order.setOrderStatus(OrderStatus.CANCELED);
        OrderResponseDto response = new OrderResponseDto();
        response.setOrderId(orderId);
        response.setOrderStatus(OrderStatus.CANCELED);
        return response;
    }

    public List<OrderResponseDto> getOrderByStatus(OrderStatus status){
        
    }
}
