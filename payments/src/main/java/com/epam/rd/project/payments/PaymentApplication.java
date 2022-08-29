package com.epam.rd.project.payments;

import com.epam.rd.project.payments.model.dtos.RoleDTO;
import com.epam.rd.project.payments.model.dtos.UserDTO;
import com.epam.rd.project.payments.model.enums.RoleType;
import com.epam.rd.project.payments.services.RoleService;
import com.epam.rd.project.payments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@EnableAutoConfiguration
@SpringBootApplication
public class PaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentApplication.class, args);
	}

	@Bean
	CommandLineRunner run(@Autowired UserService userService, @Autowired RoleService roleService){
		return args -> {
			Arrays.stream(RoleType.values()).forEach(roleType -> roleService.createRole(RoleDTO.builder().name(roleType).build()));
			userService.createUser(UserDTO.builder().email("admin@admin.com").password("12459").username("admin").build());
			userService.assignRoleToUser("admin", RoleType.ADMIN);
		};


	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


}
