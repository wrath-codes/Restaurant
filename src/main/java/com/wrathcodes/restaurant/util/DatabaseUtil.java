package com.wrathcodes.restaurant.util;

import com.wrathcodes.restaurant.seeder.RestaurantSeeder;

public class DatabaseUtil {
	public void seed_db() {
		try {
		// create restaurants
		RestaurantSeeder restaurantSeeder = new RestaurantSeeder();
		restaurantSeeder.seed("/resources/csv/restaurants.csv");
		// create categories
		// create tables
		// create menus
		// create menu items
		// create customers
		// create table check
		// create order Customer
		// create order items
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

	public void clean() {
		// delete table check
		// delete menu-ingredient
		// delete order items
		// delete order customer
		// delete order item
		// delete menu item
		// delete category
		// delete menu
		// delete tables
		// delete restaurants
	}
}
