package com.app.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Customer;
import com.app.entity.Order;
import com.app.enums.JobStatus;

public interface OrderRepositoryIF extends JpaRepository<Order, Long>{
	
	public ArrayList<Order> findByJobStatus(JobStatus jobstatus);

	public List<Order> findByCustomer(Customer customer);

}
