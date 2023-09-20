package com.ecommerce.services.Impl;

import com.ecommerce.DTO.CustomerDto;
import com.ecommerce.config.AppConstants;
import com.ecommerce.exceptions.ApiException;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.models.Customer;
import com.ecommerce.models.Role;
import com.ecommerce.repositories.CustomerRepository;
import com.ecommerce.repositories.RoleRepository;
import com.ecommerce.services.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public CustomerDto registerNewCustomer(CustomerDto customer) {
        Customer customer1=this.modelMapper.map(customer,Customer.class);

        Optional<Customer> existingCustomer=customerRepository.findByEmail(customer1.getEmail());

        if(existingCustomer.isPresent()){
            throw new ApiException("Customer with the same email already exists!");
        }

        customer1.setPassword(this.passwordEncoder.encode(customer1.getPassword()));
        customer1.setImage("default.png");

        Role role=this.roleRepository.findById(AppConstants.NORMAL_USER).get();
        customer1.getRoles().add(role);

        Customer newCustomer=this.customerRepository.save(customer1);
        return this.modelMapper.map(newCustomer,CustomerDto.class);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customer, Integer userId) {
        Customer customer1=this.customerRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("Customer","id",customer.getCustomer_id()));

        customer1.setFirstName(customer.getFirstName());
        customer1.setLastName(customer.getLastName());
        customer1.setEmail(customer.getEmail());
        customer1.setPassword(this.passwordEncoder.encode(customer1.getPassword()));
        customer1.setAddress(customer.getAddress());
        customer1.setPhoneNumber(customer.getPhoneNumber());
        customer1.setImage("default.png");
        Customer updatedCustomer=this.customerRepository.save(customer1);
        return this.modelMapper.map(updatedCustomer,CustomerDto.class);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers=this.customerRepository.findAll();
        List<CustomerDto> customerDtos=customers.stream().map(customer ->this.modelMapper.map(customer,CustomerDto.class))
                .collect(Collectors.toList());
        return customerDtos;
    }
}
