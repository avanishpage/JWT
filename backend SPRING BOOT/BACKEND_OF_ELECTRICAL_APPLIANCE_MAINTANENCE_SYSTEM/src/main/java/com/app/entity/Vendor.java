package com.app.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Vendor extends Person {
	@OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Service> services = new ArrayList<>();

	@OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> orders = new ArrayList<>();

	@OneToMany(mappedBy = "vendor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Rating> ratings = new ArrayList<>();

	// helper methods of vendor
	public void addService(Service service) {
		services.add(service);
		service.setVendor(this);
	}

	// helper method for delete service

	public void deleteService(Service service) {
		services.remove(service);
		service.setVendor(null);
	}
	
	public void addRating(Rating rating) {
		ratings.add(rating);
		rating.setVendor(this);
	}

}
