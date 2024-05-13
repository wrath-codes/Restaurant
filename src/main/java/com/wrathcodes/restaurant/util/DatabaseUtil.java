package com.wrathcodes.restaurant.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.wrathcodes.restaurant.domain.Restaurant;

public class DatabaseUtil {
	public void seed() {
		// create mapper
		String restaurantFile = "/csv/restaurant.csv";

		ObjectMapper mapper = new CsvMapper();
		CsvSchema headerSchema = CsvSchema.emptySchema().withHeader();
		try {
			CsvSchema restaurantSchema = mapper.readerFor(Restaurant.class).with(headerSchema)
					.readValue(restaurantFile);
			System.out.println(restaurantSchema);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// create ingredients

		// create restaurants
		// create categories
		// create tables
		// create menus
		// create menu items
		// create customers
		// create table check
		// create order Customer
		// create order items
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
