package com.app.entity;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.app.enums.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Service implements Cloneable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	private Long price;

	@Column(name = "image_path")
	private String imagePath;

	@Enumerated
	private Category category;

	// we will do image source afterwards
	// remember to delete this after completing

	@ManyToOne(fetch = FetchType.LAZY)
	private Vendor vendor;

	@ManyToMany
	@JoinTable(name = "service_cart", joinColumns = @JoinColumn(name = "service_id"), inverseJoinColumns = @JoinColumn(name = "cart_id"))
	private Set<Cart> carts = new HashSet<>();

	@ManyToMany
	@JoinTable(name = "service_order", joinColumns = @JoinColumn(name = "service_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
	private Set<Order> orders = new HashSet<>();

	@Override
	protected Service clone() {
		// TODO Auto-generated method stub
		try {
			return (Service) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	public Service(String name, String description, Long price, Category category, Vendor vendor, Set<Cart> carts,
			Set<Order> orders) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.vendor = vendor;
		this.carts = carts;
		this.orders = orders;
	}

}
