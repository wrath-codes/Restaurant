package com.wrathcodes.restaurant.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.wrathcodes.restaurant.domain.Restaurant;

public class RestaurantDAOTest {

    @Test
    @Ignore
    public void save() {
        Restaurant restaurant = new Restaurant();

        // create a new restaurant
        restaurant.setName("Arby's");

        // create restaurant DAO
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        restaurantDAO.save(restaurant);

        System.out.println("Restaurant saved successfully");
        System.out.println("╭────────────────────────────╮\n" +
                "│ Code: " + restaurant.getCode() + "\n" +
                "│ Name: " + restaurant.getName() + "\n" +
                "╰────────────────────────────╯");

    }

    @Test
    @Ignore
    public void list() {
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        List<Restaurant> result = restaurantDAO.list();

        System.out.println("Restaurants found: " + result.size());
        for (int position = 0; position < result.size(); position++) {
            System.out.println("╭────────────────────────────╮");
            if (position > 0) {
                System.out.println("├────────────────────────────┤");
                System.out.println("│ Code: " + result.get(position).getCode() + "\n" +
                        "│ Name: " + result.get(position).getName());
            } else {
                System.out.println("│ Code: " + result.get(position).getCode() + "\n" +
                        "│ Name: " + result.get(position).getName());
            }
            System.out.println("╰────────────────────────────╯");
        }

    }

    @Test
    @Ignore
    public void search() {
        Long code = 2L;
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        Restaurant restaurant = restaurantDAO.search(code);

        if (restaurant == null) {
            System.err.println("Record not found");
        } else {
            System.err.println("Record found:");
            System.err.println(restaurant.getName());
        }
    }

    @Test
    @Ignore
    public void delete() {
        Long code = 2L;
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        Restaurant restaurant = restaurantDAO.search(code);
        if (restaurant == null) {
            System.err.println("Record not found");
        } else {
            restaurantDAO.delete(restaurant);
            System.err.println("Record deleted:");
            System.err.println(restaurant.getName());
        }
    }

    @Test
    @Ignore
    public void update() {
        // search the restaurant
        Long code = 4L;
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        Restaurant restaurant = restaurantDAO.search(code);

        // update the restaurant
        if (restaurant == null) {
            System.err.println("Record not found");
        } else {
            System.err.println("Record found:");
            System.err.println(restaurant.getName());
            restaurant.setName("Burger King");
            restaurantDAO.update(restaurant);
            System.err.println("Record updated:");
            System.err.println(restaurant.getName());
        }
    }

}
