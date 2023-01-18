package com.platform.service;

import javax.validation.Valid;

import com.platform.exception.AdminException;
import com.platform.payloads.request.AdminDto;

public interface AdminService {

	AdminDto addAdmin(@Valid AdminDto adminDto)throws AdminException;

}
