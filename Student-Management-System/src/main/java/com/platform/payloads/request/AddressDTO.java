package com.platform.payloads.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.platform.entity.enums.AddressTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	@JsonProperty(access = Access.READ_ONLY)
	private Integer addressId;
	
	private String area;
	private String district;
	private String state;
	private String pincode;
	private AddressTypeEnum addressType; 
}
