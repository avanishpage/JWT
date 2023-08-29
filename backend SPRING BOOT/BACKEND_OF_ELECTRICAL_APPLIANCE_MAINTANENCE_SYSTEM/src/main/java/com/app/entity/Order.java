	package com.app.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.app.enums.JobStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "vendor_id")
	private Vendor vendor;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
	private Customer customer;
	
	@ManyToMany(mappedBy = "orders")
	private Set<Service> services=new HashSet<>();
	
	@Enumerated
	@Column(name="job_status")
	private JobStatus jobStatus; 

	@OneToOne(mappedBy = "order")
	private Rating rating;

	
	private LocalDateTime timeStamp;
	
	
	//helper methods
	public void giveRating(Rating rating) {
		this.rating = rating;
		rating.setOrder(this);
	}
	
	public void setRelationWithCart(Set<Service> services, Customer customer, Vendor vendor, JobStatus scheduled) {
		
		
		services.forEach(s->this.services.add(s.clone()));
		this.customer=customer;
		this.vendor=vendor;
		this.jobStatus=scheduled;
		this.timeStamp=LocalDateTime.now();
		this.services.forEach(s->s.getOrders().add(this));
		
		
	}
	
	
	
}
