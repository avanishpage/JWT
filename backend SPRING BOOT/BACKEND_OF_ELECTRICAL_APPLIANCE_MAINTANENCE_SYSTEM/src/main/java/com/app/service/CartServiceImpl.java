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
import com.app.dto.ServiceDto;
import com.app.entity.Cart;
import com.app.exceptions.CartNotFoundException;
import com.app.exceptions.ServiceNotFoundException;
import com.app.exceptions.VendorNotMatchingException;
import com.app.repository.CartRepository;
import com.app.repository.ServiceRepositoryIF;

@Service
@Transactional
public class CartServiceImpl implements CartServiceIF {
	@Autowired
	private ServiceRepositoryIF serviceRepo;
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public ApiResponse addServiceToACart(Long cartId, Long serviceId) {

		com.app.entity.Service service = serviceRepo.findById(serviceId)
				.orElseThrow(() -> new com.app.exceptions.ServiceNotFoundException("invalid service ID"));

		Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new CartNotFoundException("no such cart exists"));

		if (cart.getServices().size() == 0)
			cart.associateServiceWithCart(service);
		else if (((com.app.entity.Service) cart.getServices().toArray()[0]).getVendor().getId() == service.getVendor()
				.getId())
			cart.associateServiceWithCart(service);
		else
			throw new VendorNotMatchingException(
					"cart must contain services from same vendor,no 2 vendors are allowed!!");
		
//		if (((com.app.entity.Service) cart.getServices().toArray()[0]).getVendor().getId() == service.getVendor()
//				.getId() || cart.getServices().size() == 0)
//			cart.associateServiceWithCart(service);
//		else
//			throw new VendorNotMatchingException(
//					"cart must contain services from same vendor,no 2 vendors are allowed!!");

		return new ApiResponse("service added successfully to the cart");

	}

	@Override
	public List<ServiceDto> getAllServicesFromCart(Long cartId, int pageNo, int pageSize) {

		Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new CartNotFoundException("invalid cart id!!"));

		Pageable pageable = PageRequest.of(pageNo, pageSize);

		List<com.app.entity.Service> services = serviceRepo.findAllByCarts(cart, pageable).getContent();

		List<ServiceDto> serviceDtos = new ArrayList<>();

		services.forEach(s -> serviceDtos.add(mapper.map(s, ServiceDto.class)));

		return serviceDtos;

	}

	@Override
	public ApiResponse removeServiceFromCart(Long cartId, Long serviceId) {
		Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new CartNotFoundException("Invalid Cart Id"));

		com.app.entity.Service service = serviceRepo.findById(serviceId)
				.orElseThrow(() -> new ServiceNotFoundException("Invalid Service Id"));

		cart.disassociateServiceWithCart(service);

		return new ApiResponse("service removed Successfully!!");
	}

	@Override
	public Long addAllCostOfServicesInCart(Long cartId) {
		Cart cart = cartRepo.findById(cartId).orElseThrow(() -> new CartNotFoundException("Invalid Cart Id"));

		if (cart.getServices().size() != 0) {
			Long totalCost = 0L;

			for (com.app.entity.Service s : cart.getServices()) {
				totalCost += s.getPrice();
			}

			return totalCost;
		} else
			throw new ServiceNotFoundException("no services are present");

	}

}
