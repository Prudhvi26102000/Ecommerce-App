package com.ecommerce;

import com.ecommerce.config.AppConstants;
import com.ecommerce.models.Role;
import com.ecommerce.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class EcommerceApplication implements CommandLineRunner{

	@Bean
	ModelMapper modelMapper()
	{
		return new ModelMapper();
	}

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {

		SpringApplication.run(EcommerceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		try{
			Role role=new Role();
			role.setId(AppConstants.ADMIN_USER);
			role.setName("ROLE_ADMIN");

			Role role1=new Role();
			role1.setId(AppConstants.NORMAL_USER);
			role1.setName("ROLE_NORMAL");

			List<Role> roles=List.of(role,role1);
			List <Role> result=this.roleRepository.saveAll(roles);
 		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
