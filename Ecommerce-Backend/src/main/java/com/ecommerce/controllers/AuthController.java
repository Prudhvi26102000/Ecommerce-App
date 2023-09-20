package com.ecommerce.controllers;

import com.ecommerce.DTO.CustomerDto;
import com.ecommerce.DTO.JwtAuthRequest;
import com.ecommerce.DTO.JwtAuthResponse;
import com.ecommerce.exceptions.ApiException;
import com.ecommerce.security.JwtTokenHelper;
import com.ecommerce.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class AuthController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<CustomerDto> registerNewCustomer(@RequestBody @Valid CustomerDto customerDto){
        CustomerDto registerNewCustomer=this.customerService.registerNewCustomer(customerDto);
        return new ResponseEntity<>(registerNewCustomer,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception

    {
        this.authenticate(request.getUsername(),request.getPassword());
        UserDetails userDetails= this.userDetailsService.loadUserByUsername(request.getUsername());
        String token=this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response=new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception{

        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username, password);
        try {
            this.authenticationManager.authenticate(authenticationToken);
        }
        catch(BadCredentialsException e)
        {
            throw new ApiException("Invalid username or password !!");
        }
    }
}
