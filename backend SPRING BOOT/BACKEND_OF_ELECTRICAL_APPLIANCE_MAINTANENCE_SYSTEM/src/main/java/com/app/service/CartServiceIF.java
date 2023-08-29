package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.ServiceDto;





public interface CartServiceIF {

	public ApiResponse addServiceToACart(Long cartId, Long serviceId);

	public List<ServiceDto> getAllServicesFromCart(Long cartId,int pageNo,int pageSize);

	public ApiResponse removeServiceFromCart(Long cartId, Long serviceId);

	public Long addAllCostOfServicesInCart(Long cartId);
	
	

}
