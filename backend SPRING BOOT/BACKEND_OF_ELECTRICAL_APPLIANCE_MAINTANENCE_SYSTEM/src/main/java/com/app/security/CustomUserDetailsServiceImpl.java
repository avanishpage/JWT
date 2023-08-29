package com.app.security;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.entity.Customer;
import com.app.entity.UserEntity;
import com.app.entity.Vendor;
import com.app.repository.CustomerRepositoryIF;
import com.app.repository.UserEntityRepository;
import com.app.repository.VendorRepositoryIF;

@Service
@Transactional
public class CustomUserDetailsServiceImpl implements UserDetailsService {
	// dep user dao
	@Autowired
	private UserEntityRepository userRepo;
	@Autowired
	private VendorRepositoryIF vendorRepo;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private CustomerRepositoryIF custRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// invoke dao's method to get uer details form DB
		UserEntity user = userRepo.findByEmail(email)
				.orElseThrow(() ->
				new UsernameNotFoundException("Invalid Email !!!!!"));
		//=> user email exists
		return new CustomUserDetails(user);
	}
	public UserEntity getUserDetails(String email) {
		UserEntity user = userRepo.findByEmail(email)
				.orElseThrow(() ->
				new UsernameNotFoundException("Invalid Email !!!!!"));
		return user;
	}
	
	
	public void saveVendor(String email) {
		Vendor vendor=vendorRepo.findByEmail(email);
		UserEntity user=mapper.map(vendor, UserEntity.class);
		userRepo.save(user);
		
	}
	
	public void saveCustomer(String email) {
		Customer customer=custRepo.findByEmail(email);
		UserEntity user=mapper.map(customer, UserEntity.class);
		userRepo.save(user);
	}
	public void deleteUser(String email) {
		
		UserEntity user=userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("no such user found"));
		userRepo.delete(user);
		
	}

}
