package com.ecommerce.security;

import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.models.Customer;
import com.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer=this.customerRepository.findByEmail(username)
                .orElseThrow(()->new ResourceNotFoundException("Customer","email"+username,0));
        return customer;
    }
}
