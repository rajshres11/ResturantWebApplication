package com.project.foms.service.customerService;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.project.foms.dto.customerdto.CustomerRequestDto;
import com.project.foms.dto.customerdto.CustomerResponseDto;
import com.project.foms.entity.Customer;
import com.project.foms.exception.NoCustomerException;
import com.project.foms.repository.CustomerRepository;

@Service
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository repo;

    public CustomerServiceImp(CustomerRepository repo) {
        this.repo = repo;
    }

    @Override
    public CustomerResponseDto createCustomer(CustomerRequestDto c) {
        Customer customer = new Customer();
        customer.setCustomerName(c.getCustomerName());
        customer.setEmail(c.getEmail());
        customer.setContact(c.getContact());
        customer.setAddress(c.getAddress());
        // Also implement this method in update
        Customer saved = repo.save(customer);

        CustomerResponseDto response = new CustomerResponseDto();
        response.setCustomerId(saved.getCustomerId());
        response.setCustomerName(saved.getCustomerName());
        response.setEmail(saved.getEmail());
        response.setContact(saved.getContact());
        response.setAddress(saved.getAddress());
        return response;
    }

    @Override
    public List<CustomerResponseDto> getAllCustomer() {
        List<Customer> customerList = repo.findAll();
        List<CustomerResponseDto> responseList = new ArrayList<>();
        for (Customer c : customerList) {
            CustomerResponseDto response = new CustomerResponseDto();
            response.setCustomerId(c.getCustomerId());
            response.setCustomerName(c.getCustomerName());
            response.setEmail(c.getEmail());
            response.setContact(c.getContact());
            response.setAddress(c.getAddress());
            responseList.add(response);
        }
        return responseList;
    }

    @Override
    public CustomerResponseDto getCustomerById(int customerId) {
        CustomerResponseDto response = new CustomerResponseDto();
        Customer customer = repo.findById(customerId).orElseThrow(()-> new NoCustomerException());
        response.setCustomerId(customer.getCustomerId());
        response.setCustomerName(customer.getCustomerName());
        response.setEmail(customer.getEmail());
        response.setContact(customer.getContact());
        response.setAddress(customer.getAddress());
        return response;
    }

    @Override
    public void updateCustomer(int customerId, CustomerRequestDto c) {
        Customer existingCustomer = repo.findById(customerId).orElseThrow(()-> new NoCustomerException());
        existingCustomer.setCustomerName(c.getCustomerName());
        existingCustomer.setEmail(c.getEmail());
        existingCustomer.setContact(c.getContact());
        existingCustomer.setAddress(c.getAddress());
        repo.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(int customerId) {
        repo.deleteById(customerId);
    }

    @Override
    public CustomerResponseDto getCustomerByContact(int contact) {
        CustomerResponseDto response = new CustomerResponseDto();
        Customer c = repo.findByContact(contact);
        response.setCustomerId(c.getCustomerId());
        response.setCustomerName(c.getCustomerName());
        response.setEmail(c.getEmail());
        response.setContact(c.getContact());
        response.setAddress(c.getAddress());
        return response;
    }

}
