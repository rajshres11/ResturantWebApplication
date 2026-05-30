package com.project.foms.service.customerService;

import java.util.List;

import com.project.foms.dto.customerdto.CustomerRequestDto;
import com.project.foms.dto.customerdto.CustomerResponseDto;

public interface CustomerService {

    public CustomerResponseDto createCustomer(CustomerRequestDto c);

    public List<CustomerResponseDto> getAllCustomer();

    public CustomerResponseDto getCustomerById(int customerId);

    public void updateCustomer(int customerId, CustomerRequestDto c);

    public void deleteCustomer(int customerId);

    public CustomerResponseDto getCustomerByContact(String contact);
}
