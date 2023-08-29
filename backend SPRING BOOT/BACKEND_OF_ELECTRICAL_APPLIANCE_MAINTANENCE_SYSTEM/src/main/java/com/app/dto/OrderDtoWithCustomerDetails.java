package com.app.dto;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.app.entity.Customer;
import com.app.entity.Rating;
import com.app.entity.Service;
import com.app.entity.Vendor;
import com.app.enums.JobStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDtoWithCustomerDetails {

	private Long orderId;

	private JobStatus jobStatus;

	private LocalDateTime timeStamp;
	
	//customer details

	private Long customerId;

	private String firstName;

	private String lastName;

	private String email;

	
	private String flatNo;
	
	
	private String area;
	
	
	private String city;

	
	private String district;
	
	
	private String state;
	
	
	private String country;
	
	
	private Long pincode;
	
	private String phoneNumber;

}
