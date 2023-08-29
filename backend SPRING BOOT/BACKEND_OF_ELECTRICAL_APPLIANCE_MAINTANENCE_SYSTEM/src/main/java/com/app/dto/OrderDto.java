package com.app.dto;

import java.time.LocalDateTime;
import java.util.Set;

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
public class OrderDto {


	private Long id;

	private JobStatus jobStatus; 

	private LocalDateTime timeStamp;
	
}
