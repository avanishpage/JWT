package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.dto.ApiResponse;
import com.app.dto.OrderDto;
import com.app.dto.OrderDtoWithCustomerDetails;
import com.app.dto.ServiceDto;
import com.app.entity.Cart;
import com.app.entity.Customer;
import com.app.entity.Order;
import com.app.enums.JobStatus;
import com.app.exceptions.CartNotFoundException;
import com.app.exceptions.CustomerNotFoundException;
import com.app.exceptions.OrderNotFoundException;
import com.app.exceptions.VendorNotFoundException;
import com.app.repository.CartRepository;
import com.app.repository.CustomerRepositoryIF;
import com.app.repository.OrderRepositoryIF;
import com.app.repository.ServiceRepositoryIF;
import com.app.repository.VendorRepositoryIF;

@Service
@Transactional
public class OrderServiceImpl implements OrderServiceIF {

	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private OrderRepositoryIF orderRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private VendorRepositoryIF vendorRepo;
	@Autowired
	private ServiceRepositoryIF serviceRepo;
	@Autowired
	private CustomerRepositoryIF custRepo;

	@Override
	public ApiResponse addOrderFromCart(Long cartId) {
		Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new CartNotFoundException("invalid cart id!!"));

		Order order = new Order();

		com.app.entity.Service service = (com.app.entity.Service) cart.getServices().toArray()[0];

		order.setRelationWithCart(cart.getServices(), cart.getCustomer(), service.getVendor(), JobStatus.SCHEDULED);

		orderRepo.save(order);

		return new ApiResponse("order created successfully");
	}

	@Override
	public List<ServiceDto> getServices(Long orderId,int pageNo,int pageSize) {


		Order order = orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException("invalid order ID"));

		Pageable pageable = PageRequest.of(pageNo, pageSize);

		List<com.app.entity.Service> services = serviceRepo.findAllByOrders(order, pageable).getContent();

		List<ServiceDto> serviceDtos = new ArrayList<ServiceDto>();

		services.forEach(s -> serviceDtos.add(mapper.map(s, ServiceDto.class)));

		return serviceDtos;

	}

	@Override
	public List<OrderDto> getOrdersByJobStatus(JobStatus jobstatus) {

		List<Order> orders = orderRepo.findByJobStatus(jobstatus);

		List<OrderDto> orderdtos = new ArrayList<OrderDto>();

		orders.forEach(o -> orderdtos.add(mapper.map(o, OrderDto.class)));
		return orderdtos;

	}

	@Override
	public ApiResponse changeJobStatus(Long orderId, JobStatus jobStatus) {
		Order order = orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException("invalid order id!!"));
		order.setJobStatus(jobStatus);
		return new ApiResponse("Job status updated successfully!!");
	}

	public List<OrderDtoWithCustomerDetails> getAllOrdersOfVendor(Long vendorId) {
		List<Order> orders = vendorRepo.findById(vendorId)
				.orElseThrow(() -> new VendorNotFoundException("invalid vendor ID!")).getOrders();

		List<OrderDtoWithCustomerDetails> orderDtos = new ArrayList<>();

		orders.forEach(o -> {
		orderDtos.add(mapper.map(o, OrderDtoWithCustomerDetails.class));
		orderDtos.get(orderDtos.size()-1).setOrderId(o.getId());
		mapper.map(o.getCustomer(),orderDtos.get(orderDtos.size()-1) );
		orderDtos.get(orderDtos.size()-1).setCustomerId(o.getCustomer().getId());
		
		});

		return orderDtos;
	}

	@Override
	public OrderDto getOrderBtOrderId(Long orderId) {
		Order order = orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException("Order Not Found"));

		return mapper.map(order, OrderDto.class);
	}

	@Override
	public ApiResponse cancelOrder(Long orderId, JobStatus jobstatus) {
		Order order = orderRepo.findById(orderId).orElseThrow(() -> new OrderNotFoundException("invalid order id!!"));
		if (order.getJobStatus() == JobStatus.SCHEDULED)
			order.setJobStatus(JobStatus.CANCELED);
		else
			throw new OrderNotFoundException("Sorry your order cannot be cancelled!!");
		return new ApiResponse("Your order is canceled !!");
	}

	@Override
	public List<OrderDto> getAllOrdersForCustomer(Long customerId) {
		
		Customer customer=custRepo.findById(customerId).orElseThrow(()->new CustomerNotFoundException("invalid customer id"));
		
		List<Order> order=orderRepo.findByCustomer(customer);
		List<OrderDto> orderDtos=new ArrayList<>();
		order.forEach(o->orderDtos.add(mapper.map(o, OrderDto.class)));
		
		return orderDtos;
	}

}
