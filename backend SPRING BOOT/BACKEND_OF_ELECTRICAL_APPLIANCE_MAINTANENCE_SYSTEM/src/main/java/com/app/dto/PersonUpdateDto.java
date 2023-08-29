
package com.app.dto;


import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
public class PersonUpdateDto {
	
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

	
	
	
	public PersonUpdateDto(@NotBlank(message = "first name cannot be blank!!") String firstName,
			@NotBlank(message = "last name cannot be blank!!") String lastName,
			@NotBlank(message = "email is mandatory!!") @Email String email,
			@NotBlank(message = "flatNo cannot be blank!!") String flatNo,
			@NotBlank(message = "area cannot be blank!!") String area,
			@NotBlank(message = "city cannot be blank!!") String city,
			@NotBlank(message = "district cannot be blank!!") String district,
			@NotBlank(message = "state cannot be blank!!") String state,
			@NotBlank(message = "country cannot be blank!!") String country,
			 Long pincode,
			@NotBlank(message = "phone number cannot be blank!!") String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.flatNo = flatNo;
		this.area=area;
		this.city=city;
		this.district=district;
		this.state=state;
		this.country=country;
		this.pincode=pincode;
		this.phoneNumber = phoneNumber;
	}
	
	
	
}
