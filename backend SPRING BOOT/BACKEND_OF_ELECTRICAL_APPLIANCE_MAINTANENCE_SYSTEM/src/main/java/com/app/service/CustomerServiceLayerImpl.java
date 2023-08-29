package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dto.CustomerRegisterDto;
import com.app.dto.PersonLoginDto;
import com.app.dto.PersonLoginOutDto;
import com.app.dto.PersonUpdateDto;
import com.app.entity.Cart;
import com.app.entity.Customer;
import com.app.entity.UserEntity;
import com.app.entity.UserRole;
import com.app.enums.Role;
import com.app.exceptions.CustomerNotFoundException;
import com.app.exceptions.CustomerPasswordNotMatchingException;
import com.app.repository.CartRepository;
import com.app.repository.CustomerRepositoryIF;
import com.app.repository.UserEntityRepository;
import com.app.security.CustomUserDetailsServiceImpl;

@Service
@Transactional
public class CustomerServiceLayerImpl implements CustomerServiceLayerIF {

	@Autowired
	private CustomerRepositoryIF custRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private UserEntityRepository userRepo;
	@Autowired
	private PasswordEncoder pwdEncoder;
@Autowired
	private CustomUserDetailsServiceImpl userService;
	
	
	
	@Override
	public void addCustomerAndCart(CustomerRegisterDto custDto) {
		// System.out.println(custDto);

		Customer customerEntity = new Customer();
		mapper.map(custDto, customerEntity);
		customerEntity.setRole(UserRole.ROLE_CUSTOMER);

		Cart cart = new Cart();

		cart.addCartToCustomer(customerEntity);
		
		customerEntity.setPassword(pwdEncoder.encode(customerEntity.getPassword()));
		
		custRepo.save(customerEntity);
		cartRepo.save(cart);

	}

	@Override
	public void updateCust(PersonUpdateDto custDto, Long id) {
		System.out.println(id);
		custDto.setId(id);
		Customer customerEntity = custRepo.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException("customer by id " + id + " not present"));
		mapper.map(custDto, customerEntity);
		
		UserEntity user=userRepo.findByEmail(customerEntity.getEmail()).orElseThrow(()->new RuntimeException("enter valid id"));
		
		mapper.map(customerEntity, user);
		
	}

	@Override
	public PersonLoginOutDto getCustomerDetails(Long customerId) {

		Customer customer = custRepo.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Invalid Customer id !!!!!"));
		PersonLoginOutDto person = mapper.map(customer, PersonLoginOutDto.class);
		return person;
	}

	@Override
	public void deleteCustomer(Long customerId) {
		
		Customer customer = custRepo.findById(customerId)
				.orElseThrow(() -> new RuntimeException("Invalid Customer id !!!!!"));
		
		userService.deleteUser(customer.getEmail());
		
		cartRepo.deleteById(customerId);
		
		
		

	}

	// method called during customer login

	@Override


	public PersonLoginOutDto verifyCustomer(PersonLoginDto customerLoginDto) {


		Customer customer = custRepo.findByEmail(customerLoginDto.getEmail());
		if (customer == null)
			throw new CustomerNotFoundException("no such customer exists!");
		if (!customer.getPassword().equals(customerLoginDto.getPassword())) {
			throw new CustomerPasswordNotMatchingException("wrong password");
		}


		
		

		return mapper.map(customer, PersonLoginOutDto.class);



	}

//	
}
