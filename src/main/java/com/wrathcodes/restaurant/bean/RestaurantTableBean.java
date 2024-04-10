package com.wrathcodes.restaurant.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.wrathcodes.restaurant.dao.RestaurantTableDAO;
import com.wrathcodes.restaurant.domain.Restaurant;
import com.wrathcodes.restaurant.domain.RestaurantTable;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class RestaurantTableBean implements Serializable {
	private RestaurantTable restaurantTable;
	private List<RestaurantTable> restaurantTableList;

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

	public void list(Long restaurantCode) {
		try {
			RestaurantTableDAO restaurantTableDAO = new RestaurantTableDAO();
			Messages.addGlobalInfo("Restaurant code: " + restaurantCode);
			restaurantTableList = restaurantTableDAO.list(restaurantCode);
		} catch (RuntimeException error) {
			error.printStackTrace();
		}
	}

	public void add(Restaurant restaurant) {
		restaurantTable = new RestaurantTable(restaurant);
		restaurantTable.setRestaurant(restaurant);
	}

	public void save() {
		try {
			RestaurantTableDAO restaurantTableDAO = new RestaurantTableDAO();

			restaurantTableDAO.merge(restaurantTable);

			add(restaurantTable.getRestaurant());

			System.out.println("Restaurant code: " + restaurantTable.getRestaurant().getCode());
			restaurantTableList = restaurantTableDAO.list(restaurantTable.getRestaurant().getCode());

			Messages.addGlobalInfo("Table saved successfully");
		} catch (RuntimeException error) {
			error.printStackTrace();
		}
	}

	public void delete() {
		try {
			RestaurantTableDAO restaurantTableDAO = new RestaurantTableDAO();
			restaurantTableDAO.delete(restaurantTable);

			restaurantTableList = restaurantTableDAO.list();

			Messages.addGlobalInfo("Table deleted successfully");
		} catch (RuntimeException error) {
			error.printStackTrace();
		}
	}

	public void edit(ActionEvent event) {
		restaurantTable = (RestaurantTable) event.getComponent().getAttributes().get("selectedTable");
	}

}
