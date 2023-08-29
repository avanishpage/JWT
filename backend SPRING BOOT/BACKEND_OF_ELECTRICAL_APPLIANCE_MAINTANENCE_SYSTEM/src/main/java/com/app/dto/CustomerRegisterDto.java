
package com.app.dto;


import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import com.app.entity.Address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;



@ToString
@Getter
@Setter

@NoArgsConstructor
public class CustomerRegisterDto {
	
	
	@NotBlank(message = "first name cannot be blank!!")
	private String firstName;

	@NotBlank(message = "last name cannot be blank!!")
	private String lastName;
	
	@NotBlank(message = "email is mandatory!!")
	@Email
	private String email;
	
	@NotBlank(message = "password is mandatory!!")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$",
            message = "password must be min 4 and max 12 length containing atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	private String password;
	
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
	
	
	
	
	
	
	
	
	
}

