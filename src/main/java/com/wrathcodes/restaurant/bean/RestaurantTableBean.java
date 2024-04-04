package com.wrathcodes.restaurant.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.wrathcodes.restaurant.domain.Restaurant;
import com.wrathcodes.restaurant.domain.RestaurantTable;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class RestaurantTableBean implements Serializable {
	private RestaurantTable restaurantTable;
	private List<RestaurantTable> restaurantTableList;
	private List<Restaurant> restaurantList;

	public RestaurantTable getRestaurantTable() {
		return restaurantTable;
	}

	public void setRestaurantTable(RestaurantTable restaurantTable) {
		this.restaurantTable = restaurantTable;
	}

	public List<RestaurantTable> getRestaurantTableList() {
		return restaurantTableList;
	}

	public void setRestaurantTableList(List<RestaurantTable> restaurantTableList) {
		this.restaurantTableList = restaurantTableList;
	}

	public List<Restaurant> getRestaurantList() {
		return restaurantList;
	}

	public void setRestaurantList(List<Restaurant> restaurantList) {
		this.restaurantList = restaurantList;
	}

}
