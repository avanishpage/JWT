package com.app.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	@MapsId
	private Customer customer;
	
	@ManyToMany(mappedBy = "carts")
	private Set<Service> services=new HashSet<>();
	
	@Column(name="time_stamp")
	private LocalDateTime timeStamp;
	
	
	
	//helper methods of cart
	public void addCartToCustomer(Customer customer) {
		this.customer=customer;
		
	}
	public void removeCartFromCustomer() {
		this.customer=null;
	}
	
	//to add a service to cart
	public void associateServiceWithCart(Service service) {
		this.services.add(service);
		service.getCarts().add(this);
	}
	
	//to delete a service from cart
	public void disassociateServiceWithCart(Service service) {
		this.services.remove(service);
		service.getCarts().remove(this);
	}
	
	
	
}
