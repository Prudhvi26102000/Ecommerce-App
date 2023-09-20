package com.ecommerce.controllers;

import com.ecommerce.DTO.ApiResponse;
import com.ecommerce.DTO.CustomerDto;
import com.ecommerce.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PutMapping("/{userId}")
    public ResponseEntity<CustomerDto> updateCustomer(@RequestBody @Valid CustomerDto customerDto,@PathVariable("userId") Integer uid){
        CustomerDto updatedCustomer=this.customerService.updateCustomer(customerDto,uid);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        List<CustomerDto> customerDtos=this.customerService.getAllCustomers();
        return new ResponseEntity<>(customerDtos,HttpStatus.OK);
    }


}
