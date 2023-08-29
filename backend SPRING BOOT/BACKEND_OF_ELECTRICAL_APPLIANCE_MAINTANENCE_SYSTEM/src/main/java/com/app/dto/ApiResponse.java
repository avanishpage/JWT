package com.app.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
	private LocalDateTime timeStamp;
	private String message;
	
	public ApiResponse(String message) {
		super();
		timeStamp=LocalDateTime.now();
		this.message=message;
	}

}
