package com.app.service;

import com.app.dto.CustomerRegisterDto;
import com.app.dto.PersonLoginDto;
import com.app.dto.PersonLoginOutDto;
import com.app.dto.PersonUpdateDto;



public interface CustomerServiceLayerIF {

	void addCustomerAndCart(CustomerRegisterDto custDto);

	void updateCust(PersonUpdateDto custDto, Long id);

	PersonLoginOutDto getCustomerDetails(Long customerId);

	void deleteCustomer(Long customerId);



	PersonLoginOutDto verifyCustomer(PersonLoginDto customerLoginDto);



}
