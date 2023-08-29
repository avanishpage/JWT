package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.OrderDto;
import com.app.dto.OrderDtoWithCustomerDetails;
import com.app.dto.ServiceDto;
import com.app.enums.JobStatus;


public interface OrderServiceIF {

	public ApiResponse addOrderFromCart(Long cartId);

	public List<ServiceDto> getServices(Long orderId,int pageNo,int pageSize);
	
	public List<OrderDto> getOrdersByJobStatus(JobStatus jobstatus);

	public ApiResponse changeJobStatus(Long orderId, JobStatus jobStatus);

	public List<OrderDtoWithCustomerDetails> getAllOrdersOfVendor(Long vendorId);
	
	public OrderDto getOrderBtOrderId(Long orderId);

	public ApiResponse cancelOrder(Long orderId,JobStatus jobstatus);

	public List<OrderDto> getAllOrdersForCustomer(Long customerId);
}
