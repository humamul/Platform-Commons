package com.platform.service;

import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.platform.entity.Admin;
import com.platform.exception.AdminException;
import com.platform.payloads.request.AdminDto;
import com.platform.repository.AdminRepo;

@Service

public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminRepo adminRepo;
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public AdminDto addAdmin(@Valid AdminDto adminDto) throws AdminException {
		 Optional<Admin> opt = adminRepo.findByEmail(adminDto.getEmail());
		 if(opt.isPresent()) {
			 throw new AdminException("Admin already exist with email : "+adminDto.getEmail());
			 
		 }
		 Admin admin = new Admin();
		 admin.setAdminName(adminDto.getAdminName());
		 admin.setEmail(adminDto.getEmail());
		 admin.setPassword(encoder.encode(adminDto.getPassword()));
		 Admin saved = this.adminRepo.save(admin);
		 AdminDto map = this.modelMapper.map(saved, AdminDto.class);
		 
		 map.setResponse("Admin Registered");
		// TODO Auto-generated method stub
		return map;
	}

}
