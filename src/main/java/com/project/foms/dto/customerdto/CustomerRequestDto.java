package com.project.foms.dto.customerdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class CustomerRequestDto {
    
    @NotNull(message = "Enter name is mandatory")
    private String customerName;

    @Email(message = "Enter valid email format")
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message="Enter valid PhoneNumber")
    private String contact;

    @NotBlank(message = "Mandatory to give address")
    private String address;

    // Getter and Setter
   
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
