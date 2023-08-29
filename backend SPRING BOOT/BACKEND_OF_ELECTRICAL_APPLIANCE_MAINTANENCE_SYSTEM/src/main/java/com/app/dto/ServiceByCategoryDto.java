package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.app.entity.Address;
import com.app.enums.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceByCategoryDto {
	private Long serviceId;
	private String name;
	private String description;
	private Long price;
	private Category category;
	
	//vendor details
	private Long vendorId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String address;
	private String city;
	private String phoneNumber;
	
	private double averageRating;
}
