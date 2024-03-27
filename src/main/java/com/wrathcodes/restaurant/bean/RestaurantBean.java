package com.wrathcodes.restaurant.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

import org.omnifaces.util.Messages;

import com.wrathcodes.restaurant.dao.RestaurantDAO;
import com.wrathcodes.restaurant.domain.Restaurant;

@ManagedBean
@ViewScoped
@SuppressWarnings("serial")
public class RestaurantBean implements Serializable {
    RestaurantBean restaurantBean;
    private List<Restaurant> restaurants;

    private Restaurant restaurant;

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

    @PostConstruct
    public void list() {
        try {
            RestaurantDAO restaurantDAO = new RestaurantDAO();
            restaurants = restaurantDAO.list();
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
            restaurantDAO.save(restaurant);

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
}
