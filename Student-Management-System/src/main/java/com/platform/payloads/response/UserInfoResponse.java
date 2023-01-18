package com.platform.payloads.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfoResponse {
//	private Integer id;
	private String email;
	private List<String> roles;
}
