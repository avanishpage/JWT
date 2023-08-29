package com.app.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@Embeddable
public class Address {
	@NotBlank(message = "flat no cant be blank!!")
	private String flatNo;
	
	@NotBlank(message = "area cant be blank!!")
	private String area;
	
	@NotBlank(message = "city cant be blank!!")
	private String city;
	
	@NotBlank(message = "district cant be blank!!")
	private String district;
	
	@NotBlank(message = "state cant be blank!!")
	private String state;
	
	@NotBlank(message = "country cant be blank!!")
	private String country;
	
	@NotBlank(message = "pincode cant be blank!!")
	private String pincode;
	
	
}
