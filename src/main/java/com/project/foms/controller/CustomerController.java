package com.project.foms.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.foms.dto.ApiResponse;
import com.project.foms.dto.customerdto.CustomerRequestDto;
import com.project.foms.dto.customerdto.CustomerResponseDto;
import com.project.foms.service.customerService.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    
    private final CustomerService service;
    public CustomerController(CustomerService service){
        this.service=service;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> createCustomer(@Valid @RequestBody CustomerRequestDto c){
        CustomerResponseDto customer = service.createCustomer(c);
        ApiResponse<CustomerResponseDto> response= new ApiResponse<>(HttpStatus.CREATED.value(),"Customer Created",customer);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<ApiResponse<List<CustomerResponseDto>>> getAllCustomer(){
        List<CustomerResponseDto> list = service.getAllCustomer();
        ApiResponse<List<CustomerResponseDto>> response = new ApiResponse<List<CustomerResponseDto>>(HttpStatus.OK.value(),"All Customer Fetched", list);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/get/{customerId}")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> getByCustomerId(@PathVariable int customerId){
        CustomerResponseDto customer = service.getCustomerById(customerId);
        ApiResponse<CustomerResponseDto> response= new ApiResponse<>(HttpStatus.OK.value(),"Feteched sussefully", customer);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> updateCustomer(@Valid @PathVariable int customerId,@RequestBody CustomerRequestDto c){
        service.updateCustomer(customerId, c);
        CustomerResponseDto customer = service.getCustomerById(customerId);
        ApiResponse<CustomerResponseDto> response = new ApiResponse<CustomerResponseDto>(HttpStatus.OK.value(),"Customer updated", customer);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    // @DeleteMapping("/delete/{customerId}")
    // public ResponseEntity<ApiResponse<String>> deleteCustomer(@PathVariable int customerId){
    //     service.deleteCustomer(customerId);
    //     return new ResponseEntity<>(new ApiResponse<>(HttpStatus.OK.value(),"Customer deleted sussesfully", null),HttpStatus.OK);
    // }

    // If we want to give NO_CONTENT there is no body with this.
    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId){
        service.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/bycontact/{contact}")
    public ResponseEntity<ApiResponse<CustomerResponseDto>> getByContact(@PathVariable int contact){
        CustomerResponseDto customer = service.getCustomerByContact(contact);
        ApiResponse<CustomerResponseDto> response = new ApiResponse<CustomerResponseDto>(HttpStatus.OK.value(),"Customer fetched", customer);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    
}
