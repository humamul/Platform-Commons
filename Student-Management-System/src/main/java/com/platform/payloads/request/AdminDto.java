package com.platform.payloads.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdminDto {

	private String adminName;
	private String email;
	private String password;
	@JsonProperty(access = Access.READ_ONLY)
	private String response;
}
