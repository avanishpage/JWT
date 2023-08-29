package com.app.service;

import java.util.List;

import com.app.dto.PersonDtoWithRole;
import com.app.dto.PersonLoginDto;
import com.app.dto.PersonLoginOutDto;
import com.app.dto.PersonRegisterDto;
import com.app.dto.PersonUpdateDto;
import com.app.dto.ServiceDto;

public interface VendorServiceLayerIF {
	
	public void addVendor(PersonRegisterDto custDto);

	public void updateVendor(PersonUpdateDto custDto, Long id);

	public PersonLoginOutDto getVendorDetails(Long vendorId);

	public void deleteVendor(Long vendorId);

	public PersonDtoWithRole verifyVendor(PersonLoginDto vendorLoginDto);

	public List<ServiceDto> getAllServicesOf(Long vendorId);
	
	public void updateServiceofVendor(ServiceDto servicedto,Long vendorId, Long serviceId);

	public ServiceDto getSingleService(Long serviceId);

}
