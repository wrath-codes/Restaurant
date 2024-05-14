package com.wrathcodes.restaurant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Category extends GenericDomain {

	@Column(length = 50, nullable = false, unique = false)
	private String name;

	@Column(length = 100, nullable = false)
	private String description;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Menu menu;

	public Category() {
	}

	public Category(Menu menu) {
		this.menu = menu;
	}

	public Category(String name, String description, Menu menu) {
		this.name = name;
		this.description = description;
		this.menu = menu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
