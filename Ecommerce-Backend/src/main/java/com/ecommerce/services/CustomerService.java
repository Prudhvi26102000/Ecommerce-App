package com.ecommerce.services;

import com.ecommerce.DTO.CustomerDto;

import java.util.List;

public interface CustomerService {

    CustomerDto registerNewCustomer(CustomerDto customer);
    CustomerDto updateCustomer(CustomerDto customer, Integer userId);
    List<CustomerDto> getAllCustomers();

}
