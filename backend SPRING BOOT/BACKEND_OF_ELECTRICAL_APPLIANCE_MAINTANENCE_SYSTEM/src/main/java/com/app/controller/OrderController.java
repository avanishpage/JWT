package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.CardDto;
import com.app.dto.OrderDto;
import com.app.dto.OrderDtoWithCustomerDetails;
import com.app.dto.ServiceDto;
import com.app.enums.JobStatus;
import com.app.service.CardService;
import com.app.service.OrderServiceIF;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
	
	@Autowired
	private OrderServiceIF orderService;
	@Autowired
	private CardService cardService;
	
	
	
	@PostMapping("/add/cart/{cartId}")
	public ApiResponse createOrderFromCart(@PathVariable Long cartId,@RequestBody CardDto cardDto) {	
		
		if(cardService.verifyCardDetails(cardDto))
			return orderService.addOrderFromCart(cartId);
		return new ApiResponse("order not created!!");
		
		
		
	}
	
	@GetMapping("/{orderId}/services")
	public List<ServiceDto> getServicesOfOrder(@PathVariable Long orderId,@RequestParam int pageNo,@RequestParam int pageSize) {
		return orderService.getServices(orderId,pageNo,pageSize);
	}
	
	@GetMapping("/jobstatus")
	public List<OrderDto> getOrdersByJobStatus(@RequestParam JobStatus jobstatus){
		return orderService.getOrdersByJobStatus(jobstatus);		
	}
	

	@PatchMapping("/changeJobStatus/{orderId}")
	public ApiResponse changeJobStatus(@PathVariable Long orderId,@RequestParam JobStatus jobStatus) {
		return orderService.changeJobStatus(orderId,jobStatus );		
	}
	
	@GetMapping("/vendor/{vendorId}")
	public List<OrderDtoWithCustomerDetails> getOrdersByVendorId(@PathVariable Long vendorId){	
		return orderService.getAllOrdersOfVendor(vendorId);
	}
	
	@GetMapping("/{orderid}")
	public OrderDto getOrderById(@PathVariable Long orderId) {
		return orderService.getOrderBtOrderId(orderId);
	}

	@PatchMapping("/cancelOrder/{orderId}")
	public ApiResponse cancelOrder(@PathVariable Long orderId ) {
		return orderService.cancelOrder(orderId,JobStatus.COMPLETED );		
	}
	
	@GetMapping("/customer/{customerId}")
	public List<OrderDto> getAllOrdersOfCustomer(@PathVariable Long customerId){
		return orderService.getAllOrdersForCustomer(customerId);
	}
	

}
