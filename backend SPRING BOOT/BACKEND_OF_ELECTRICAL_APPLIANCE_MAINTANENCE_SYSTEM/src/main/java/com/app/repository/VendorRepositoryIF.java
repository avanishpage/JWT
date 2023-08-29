package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Vendor;


	public interface VendorRepositoryIF extends JpaRepository<Vendor, Long> {

		public Vendor findByEmail(String email);

	}
