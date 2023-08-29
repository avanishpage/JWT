package com.app.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ApiResponse;

public interface ImageHandlingIF {
	public byte[] serveImageVendor(Long vendorId) throws IOException;
	public ApiResponse uploadImageVendor(Long vendorId, MultipartFile image) throws IOException;
	public byte[] serveImageCustomer(Long customerId) throws IOException;
	public ApiResponse uploadImageCustomer(Long customerId, MultipartFile image) throws IOException;
	public byte[] serveImageService(Long serviceId) throws IOException;
	public ApiResponse uploadImageService(Long serviceId, MultipartFile image) throws IOException;
	
	
}
