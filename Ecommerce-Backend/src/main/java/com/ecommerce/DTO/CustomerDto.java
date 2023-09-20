package com.ecommerce.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private int customer_id;

    @NotEmpty(message = "FirstName must not be empty!!")
    @Size(min=4,message="Username must be minimum of 4 characters !! ")
    private String firstName;

    @NotEmpty(message = "LastName must not be empty!!")
    @Size(min=4,message="Username must be minimum of 4 characters !! ")
    private String lastName;

    @Email(message = "Email is not valid!!")
    private String email;

    @NotEmpty(message = "Password must not be empty!!")
    @Size(min=3,max = 16,message = "Password must be min of 3 characters and max of 14 characters!!")
    private String password;

    @NotEmpty(message = "Password must not be empty!!")
    @Size(min=4,message="Username must be minimum of 4 characters !! ")
    private String address;

    @NotNull(message = "Phone number must not be null")
    @Size(min = 10, max = 10, message = "phone_no should be exact 10 characters.")
    private String phoneNumber;

    private String image;

    private Set<RoleDto> roles=new HashSet<>();

}
