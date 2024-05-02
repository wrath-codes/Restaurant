package com.wrathcodes.restaurant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@SuppressWarnings("serial")
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "number", "restaurant_code" }) })
public class RestaurantTable extends GenericDomain {

	@Column(nullable = false)
	private Integer number;

	@Column(nullable = false)
	private Boolean available = true;

	@Column(nullable = false)
	private Integer capacity;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurant restaurant;

	public RestaurantTable(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	// Additional constructor that initializes all fields
	public RestaurantTable(Integer number, Boolean available, Integer capacity, Restaurant restaurant) {
		this.number = number;
		this.available = available;
		this.capacity = capacity;
		this.restaurant = restaurant;
	}

	// No-argument constructor required by JPA
	public RestaurantTable() {
		// No initialization needed
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
}
