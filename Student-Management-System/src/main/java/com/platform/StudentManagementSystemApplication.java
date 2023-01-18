package com.platform;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.platform.entity.Admin;
import com.platform.repository.AdminRepo;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class StudentManagementSystemApplication implements CommandLineRunner {
	
	@Autowired
	private AdminRepo adminRepo;
	@Autowired
	private PasswordEncoder enc;
	
	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		Admin superAdmin = new Admin();
		Optional<Admin> opt = adminRepo.findByEmail("humam.alam19@gmail.com");
		if(opt.isPresent()) {
			
		}else {
		superAdmin.setAdminName("Humam");
		superAdmin.setEmail("humam.alam19@gmail.com");
		superAdmin.setPassword(enc.encode("password"));
		adminRepo.save(superAdmin);
		// TODO Auto-generated method stub
		}
	}
}
