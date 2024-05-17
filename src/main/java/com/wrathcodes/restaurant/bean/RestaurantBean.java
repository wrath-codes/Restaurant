package com.wrathcodes.restaurant.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.wrathcodes.restaurant.domain.Restaurant;
import com.wrathcodes.restaurant.dao.RestaurantDAO;
import com.wrathcodes.restaurant.dao.RestaurantTableDAO;
//import com.wrathcodes.restaurant.util.DatabaseUtil;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class RestaurantBean implements Serializable {
	private List<Restaurant> restaurants;

	private Restaurant restaurant;

	private Long restaurantCode;

	private Restaurant restaurantSelected;

	public Restaurant getRestaurantSelected() {
		return restaurantSelected;
	}

	public void setRestaurantSelected(Restaurant restaurantSelected) {
		this.restaurantSelected = restaurantSelected;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public Long getRestaurantCode() {
		return restaurantCode;
	}

	public void setRestaurantCode(Long code) {
		this.restaurantCode = code;
	}

	public void view(Restaurant restaurant) throws IOException {
		RestaurantDAO restaurantDAO = new RestaurantDAO();
		restaurantSelected = restaurantDAO.search(restaurant.getCode());
		FacesContext.getCurrentInstance().getExternalContext().redirect("/Restaurant/pages/restaurant/overview.xhtml");
	}


	public Integer tableCount(Long code) {
		RestaurantTableDAO restaurantTableDAO = new RestaurantTableDAO();
		return restaurantTableDAO.list(code).size();
	}
	
	@PostConstruct
	public void list() {
		try {
			RestaurantDAO restaurantDAO = new RestaurantDAO();
			restaurants = restaurantDAO.list();
			System.out.println("Restaurants Total: " + restaurants.size());
			for (Restaurant restaurant : restaurants) {
				System.out.println("Code: " + restaurant.getCode() + " - " + restaurant.getName());
			}
		} catch (RuntimeException error) {
			Messages.addGlobalError("Error trying to list restaurants!");
			error.printStackTrace();
		}
	}

	public void add() {
		restaurant = new Restaurant();
	}

	public void save() {
		try {
			RestaurantDAO restaurantDAO = new RestaurantDAO();
			restaurantDAO.merge(restaurant);

			add();
			restaurants = restaurantDAO.list();

			Messages.addGlobalInfo("Restaurant saved successfully!");

		} catch (RuntimeException error) {
			Messages.addGlobalError("Error trying to save restaurant!");
			error.printStackTrace();
		}
	}

	public void delete(ActionEvent event) {
		try {
			restaurant = (Restaurant) event.getComponent().getAttributes().get("selectedRestaurant");

			RestaurantDAO restaurantDAO = new RestaurantDAO();
			restaurantDAO.delete(restaurant);
			restaurants = restaurantDAO.list();

			Messages.addGlobalInfo("Restaurant deleted successfully!");
		} catch (RuntimeException error) {
			Messages.addGlobalError("Error trying to delete restaurant!");
			error.printStackTrace();
		}
	}

	public void edit(ActionEvent event) {
		restaurant = (Restaurant) event.getComponent().getAttributes().get("selectedRestaurant");
	}
}
