package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Customer;

public interface CustomerRepositoryIF extends JpaRepository<Customer, Long> {

	public Customer findByEmail(String email);

}
