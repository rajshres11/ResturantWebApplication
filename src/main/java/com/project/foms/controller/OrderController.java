package com.project.foms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.foms.dto.ApiResponse;
import com.project.foms.dto.orderdto.OrderRequestDto;
import com.project.foms.dto.orderdto.OrderResponseDto;
import com.project.foms.dto.orderdto.OrderStatusDto;
import com.project.foms.enums.OrderStatus;
import com.project.foms.service.orderService.OrderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/order")
public class OrderController {
    
    
    private final OrderService orderService;
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<OrderResponseDto>> placeOrder(@RequestBody OrderRequestDto o){
        OrderResponseDto order = orderService.placeOrder(o);
        ApiResponse<OrderResponseDto> response = new ApiResponse<>(HttpStatus.CREATED.value(),"Order created sussesfully",order);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<OrderResponseDto>>> getAll(){
        List<OrderResponseDto> orderList = orderService.getAllOrders();
        ApiResponse<List<OrderResponseDto>> response = new ApiResponse<List<OrderResponseDto>>(HttpStatus.FOUND.value(),"All order fetched sussesfully", orderList);
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }
    
    @GetMapping("/get/orderId/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> getById(@PathVariable int orderId){
        OrderResponseDto order = orderService.getById(orderId);
        ApiResponse<OrderResponseDto> response = new ApiResponse<OrderResponseDto>(HttpStatus.FOUND.value(),"Order found sussesfully", order);
        return new ResponseEntity<>(response,HttpStatus.FOUND);
    }

    @GetMapping("/getall/customerId/{customerId}")
    public ResponseEntity<ApiResponse<List<OrderResponseDto>>> getAllOrderByCustomerId(@PathVariable int customerId){
        List<OrderResponseDto> orderList = orderService.getAllOrderByCustomer(customerId);
        ApiResponse<List<OrderResponseDto>> response = new ApiResponse<List<OrderResponseDto>>(HttpStatus.OK.value(),"All order by customer Id", orderList);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> updateOrderStatus(@PathVariable int orderId,@RequestBody OrderStatusDto o){
        OrderResponseDto order = orderService.updateOrderStatus(orderId, o);
        ApiResponse<OrderResponseDto> response = new ApiResponse<OrderResponseDto>(HttpStatus.OK.value(),"OrderStatus updated", order);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/cancelOrder/{orderId}")
    public ResponseEntity<ApiResponse<OrderResponseDto>> cancelOrder(@PathVariable int orderId){
        OrderResponseDto order = orderService.cancelOrder(orderId);
        ApiResponse<OrderResponseDto> response = new ApiResponse<OrderResponseDto>(HttpStatus.OK.value(),"Order cancelled", order);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/getOrderBy/{status}")
    public ResponseEntity<ApiResponse<List<OrderResponseDto>>> getOrderByStatus(@PathVariable OrderStatus status){
        List<OrderResponseDto> orderList = orderService.getOrderByStatus(status);
        ApiResponse<List<OrderResponseDto>> response = new ApiResponse<List<OrderResponseDto>>(HttpStatus.OK.value(),"All order fetched sussesfully", orderList);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
   
}
