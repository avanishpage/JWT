package com.app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.PersonDtoWithRole;
import com.app.dto.PersonLoginDto;
import com.app.dto.PersonLoginOutDto;
import com.app.dto.PersonRegisterDto;
import com.app.dto.PersonUpdateDto;
import com.app.dto.ServiceDto;
import com.app.entity.UserEntity;
import com.app.entity.UserRole;
import com.app.entity.Vendor;
import com.app.enums.Role;
import com.app.exceptions.ResourceNotFound;
import com.app.exceptions.ServiceNotFoundException;
import com.app.exceptions.VendorNotFoundException;
import com.app.exceptions.VendorPasswordNotMatchingException;
import com.app.repository.ServiceRepositoryIF;
import com.app.repository.UserEntityRepository;
import com.app.repository.VendorRepositoryIF;
import com.app.security.CustomUserDetailsServiceImpl;

@Service
@Transactional
public class VendorServiceLayerImpl implements VendorServiceLayerIF {

	@Autowired
	private VendorRepositoryIF vendorRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ServiceRepositoryIF serviceRepo;
	@Autowired
	private ImageHandlingIF imgService;
	@Autowired
	private PasswordEncoder pwdEncoder;
	@Autowired
	private UserEntityRepository userRepo;
	@Autowired
	private CustomUserDetailsServiceImpl userService;

	@Override
	public void addVendor(PersonRegisterDto vendorDto) {

		Vendor vendorEntity = new Vendor();
		mapper.map(vendorDto, vendorEntity);
		vendorEntity.setRole(UserRole.ROLE_VENDOR);
		vendorEntity.setPassword(pwdEncoder.encode(vendorEntity.getPassword()));
		
		
		
		
		vendorRepo.save(vendorEntity);
		
		try {
			System.out.println(imgService.uploadImageVendor(vendorEntity.getId(), vendorDto.getAddImage()).getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public void updateVendor(PersonUpdateDto vendorDto, Long id) {
		System.out.println(id);

		Vendor vendorEntity = vendorRepo.findById(id)
				.orElseThrow(() -> new VendorNotFoundException("vendor by id " + id + " not present"));
		vendorDto.setId(id);
		mapper.map(vendorDto, vendorEntity);
		
		UserEntity user=userRepo.findByEmail(vendorEntity.getEmail()).orElseThrow(()->new RuntimeException("enter valid id"));
		
		mapper.map(vendorEntity, user);
	}

	@Override
	public PersonLoginOutDto getVendorDetails(Long vendorId) {

		return mapper.map(
				vendorRepo.findById(vendorId).orElseThrow(() -> new VendorNotFoundException("Invalid vendor id !!!!!")),
				PersonLoginOutDto.class);

	}

	@Override
	public void deleteVendor(Long vendorId) {
		
		Vendor vendor = vendorRepo.findById(vendorId)
				.orElseThrow(() -> new VendorNotFoundException("invalid vendor id"));
		
		userService.deleteUser(vendor.getEmail());
	

		vendorRepo.deleteById(vendorId);

	}

	// method called during vendor login
	@Override
	public PersonDtoWithRole verifyVendor(PersonLoginDto vendorLoginDto) {

		Vendor vendor = vendorRepo.findByEmail(vendorLoginDto.getEmail());
		if (vendor == null)
			throw new VendorNotFoundException("invalid email!!");
		if (!vendor.getPassword().equals(vendorLoginDto.getPassword()))
			throw new VendorPasswordNotMatchingException("wrong password!!");

		return mapper.map(vendor, PersonDtoWithRole.class);

	}

	@Override
	public List<ServiceDto> getAllServicesOf(Long vendorId) {

		Vendor vendor = vendorRepo.findById(vendorId)
				.orElseThrow(() -> new VendorNotFoundException("invalid vendor id"));

		List<com.app.entity.Service> services = vendor.getServices();
		services.size();

		List<ServiceDto> servicesDto = new ArrayList<>();

		services.forEach((s) -> servicesDto.add(mapper.map(s, ServiceDto.class)));
		return servicesDto;
	}

	@Override
	public void updateServiceofVendor(ServiceDto servicedto, Long vendorId, Long serviceId) {

		servicedto.setId(serviceId);
		Vendor vendor = vendorRepo.findById(vendorId)
				.orElseThrow(() -> new VendorNotFoundException("invalid vendor id"));

		com.app.entity.Service service = vendor.getServices().stream().filter(p -> p.getId().equals(serviceId))
				.findFirst().orElseThrow(() -> new ResourceNotFound("Service not found for this vendor"));

		mapper.map(servicedto, service);
		serviceRepo.save(service);

	}

	@Override
	public ServiceDto getSingleService(Long serviceId) {
		
		return mapper.map(serviceRepo.findById(serviceId).orElseThrow(()-> new ServiceNotFoundException("service id not valid")), ServiceDto.class);
	}

}
