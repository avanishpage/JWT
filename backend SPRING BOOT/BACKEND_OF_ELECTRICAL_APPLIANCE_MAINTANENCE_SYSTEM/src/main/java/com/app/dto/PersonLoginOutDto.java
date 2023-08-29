
package com.app.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.app.entity.UserRole;
import com.app.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonLoginOutDto {
	
	private Long id;
	
	@NotBlank(message = "first name cannot be blank!!")
	private String firstName;

	@NotBlank(message = "last name cannot be blank!!")
	private String lastName;
	
	@NotBlank(message = "email is mandatory!!")
	@Email
	private String email;
	
	@NotBlank
	private String flatNo;
	
	@NotBlank
	private String area;
	
	@NotBlank
	private String city;

	@NotBlank
	private String district;
	
	@NotBlank
	private String state;
	
	@NotBlank
	private String country;
	
	
	private Long pincode;
	
	@NotBlank(message = "phone number cannot be blank!!")
	private String phoneNumber;

	private UserRole role;
	
	
	private String jwt;
	
	
	
}
