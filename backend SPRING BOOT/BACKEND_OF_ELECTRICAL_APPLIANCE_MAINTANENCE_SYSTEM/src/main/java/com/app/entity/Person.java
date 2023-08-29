package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.app.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(unique = true)
	private String email;

	@Column
	private String password;


	@Column
	private String flatNo;
	
	@Column
	private String area;
	
	@Column
	private String city;

	@Column
	private String district;
	
	@Column
	private String state;
	
	@Column
	private String country;
	
	@Column
	private Long pincode;



	


	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "image_path")
	private String imagePath;


@Enumerated
	private UserRole role;


}
