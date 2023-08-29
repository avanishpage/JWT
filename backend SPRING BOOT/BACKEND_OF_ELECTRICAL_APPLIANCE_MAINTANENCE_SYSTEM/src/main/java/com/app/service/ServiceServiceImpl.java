package com.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dto.ApiResponse;
import com.app.dto.ServiceByCategoryDto;
import com.app.dto.ServiceDto;
import com.app.entity.Vendor;
import com.app.enums.Category;
import com.app.exceptions.ServiceNotFoundException;
import com.app.exceptions.VendorNotFoundException;
import com.app.repository.ServiceRepositoryIF;
import com.app.repository.VendorRepositoryIF;

@Service
@Transactional
public class ServiceServiceImpl implements ServiceServiceLayerIF {
	@Autowired
	private ServiceRepositoryIF serviceRepo;
	@Autowired
	private VendorRepositoryIF vendorRepo;
	@Autowired
	private ModelMapper mapper;

	@Override
	public void attachServiceToVendor(ServiceDto serviceDto, Long vendorId) {

		System.out.println(serviceDto);

		Vendor vendor = vendorRepo.findById(vendorId)
				.orElseThrow(() -> new VendorNotFoundException("invalid vendor id!!"));

		com.app.entity.Service service = new com.app.entity.Service();
		service = mapper.map(serviceDto, com.app.entity.Service.class);
		serviceRepo.save(service);

		vendor.addService(service);

	}

	@Override
	public ApiResponse deleteServiceUsingVendorId(Long vendorId, Long serviceId) {

		Vendor vendor = vendorRepo.findById(vendorId)
				.orElseThrow(() -> new VendorNotFoundException("invalid vendor id!!"));
		com.app.entity.Service service = serviceRepo.findById(serviceId)
				.orElseThrow(() -> new ServiceNotFoundException("invalid serviceW id!!"));

		vendor.deleteService(service);
		serviceRepo.delete(service);

		return new ApiResponse("Service deleted successfully");
	}

	@Override
	public List<ServiceByCategoryDto> getServicesByCategory(Category category) {

		List<com.app.entity.Service> services = serviceRepo.findByCategory(category);

		List<ServiceByCategoryDto> serviceDtos = new ArrayList<>();

		services.forEach(s -> {
			serviceDtos.add(mapper.map(s, ServiceByCategoryDto.class));
			serviceDtos.get(serviceDtos.size()-1).setServiceId(s.getId());
			mapper.map(s.getVendor(),serviceDtos.get(serviceDtos.size()-1));
			serviceDtos.get(serviceDtos.size()-1).setVendorId(s.getVendor().getId());
			
		} );
		return serviceDtos;
	}

	@Override
	public List<Category> getAllCategories() {
		return Arrays.asList(Category.values());

	}

	@Override
	public List<ServiceDto> getServicesByCity(String city) {
		List<com.app.entity.Service> services = serviceRepo.findAll().stream()
				.filter(s -> s.getVendor().getCity().equals(city)).collect(Collectors.toList());
		if (services.size() == 0)
			throw new ServiceNotFoundException("invalid city name,cannot find services for city " + city);
		List<ServiceDto> serviceDtos = new ArrayList<>();

		services.forEach(s -> serviceDtos.add(mapper.map(s, ServiceDto.class)));

		return serviceDtos;
	}
}
