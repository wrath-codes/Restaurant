package com.wrathcodes.restaurant.dao;

import org.junit.Ignore;
import org.junit.Test;

import com.wrathcodes.restaurant.domain.Restaurant;
import com.wrathcodes.restaurant.domain.RestaurantTable;

public class RestaurantTableDAOTest {

    @Test
    public void save() {

        // get restaurant
        Long restaurantCode = 1L;
        Restaurant restaurant = new RestaurantDAO().search(restaurantCode);

        if (restaurant == null) {
            System.out.println("Restaurant not found");
        } else {
            System.out.println("Restaurant found: " + restaurant.getName());

            // create a new table
            RestaurantTable restaurantTable = new RestaurantTable();
            restaurantTable.setNumber(2);
            restaurantTable.setAvailable(true);
            restaurantTable.setCapacity(6);
            restaurantTable.setRestaurant(restaurant);

            // create table DAO
            RestaurantTableDAO restaurantTableDAO = new RestaurantTableDAO();
            restaurantTableDAO.save(restaurantTable);

            System.out.println("Table saved");
            System.out.println(
                    "╭────────────────────────────╮\n" +
                            "│ Code: " + restaurantTable.getCode() + "\n" +
                            "│ Number: " + restaurantTable.getNumber() + "\n" +
                            "│ Available: " + restaurantTable.getAvailable() + "\n" +
                            "│ Capacity: " + restaurantTable.getCapacity() + "\n" +
                            "│ Restaurant: " + restaurantTable.getRestaurant().getName() + "\n" +
                            "╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void list() {
        RestaurantTableDAO restaurantTableDAO = new RestaurantTableDAO();

        System.out.println("╭────────────────────────────╮");
        for (RestaurantTable restaurantTable : restaurantTableDAO.list()) {
            System.out.println("├────────────────────────────┤");
            System.out.println(
                    "│ Code: " + restaurantTable.getCode() + "\n" +
                            "│ Number: " + restaurantTable.getNumber() + "\n" +
                            "│ Available: " + restaurantTable.getAvailable() + "\n" +
                            "│ Capacity: " + restaurantTable.getCapacity() + "\n" +
                            "│ Restaurant: " + restaurantTable.getRestaurant().getName());
        }
        System.out.println("╰────────────────────────────╯");
    }

    @Test
    @Ignore
    public void search() {
        Long code = 1L;
        RestaurantTableDAO restaurantTableDAO = new RestaurantTableDAO();
        RestaurantTable restaurantTable = restaurantTableDAO.search(code);

        if (restaurantTable == null) {
            System.err.println("Record not found");
        } else {
            System.out.println("Record found:");
            System.out.println(
                    "╭────────────────────────────╮\n" +
                            "│ Code: " + restaurantTable.getCode() + "\n" +
                            "│ Number: " + restaurantTable.getNumber() + "\n" +
                            "│ Available: " + restaurantTable.getAvailable() + "\n" +
                            "│ Capacity: " + restaurantTable.getCapacity() + "\n" +
                            "│ Restaurant: " + restaurantTable.getRestaurant().getName() + "\n" +
                            "╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void delete() {
        Long code = 4L;
        RestaurantTableDAO restaurantTableDAO = new RestaurantTableDAO();
        RestaurantTable restaurantTable = restaurantTableDAO.search(code);
        if (restaurantTable == null) {
            System.err.println("Record not found");
        } else {
            restaurantTableDAO.delete(restaurantTable);
            System.out.println("Record deleted:");
            System.out.println(
                    "╭────────────────────────────╮\n" +
                            "│ Code: " + restaurantTable.getCode() + "\n" +
                            "│ Number: " + restaurantTable.getNumber() + "\n" +
                            "│ Available: " + restaurantTable.getAvailable() + "\n" +
                            "│ Capacity: " + restaurantTable.getCapacity() + "\n" +
                            "│ Restaurant: " + restaurantTable.getRestaurant().getName() + "\n" +
                            "╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void update() {
        Long restaurantCode = 1L;
        Long restaurantTableCode = 3L;

        Restaurant restaurant = new RestaurantDAO().search(restaurantCode);

        if (restaurant == null) {
            System.out.println("Restaurant not found");
        } else {
            RestaurantTable restaurantTable = new RestaurantTableDAO().search(restaurantTableCode);
            if (restaurantTable == null) {
                System.out.println("RestaurantTable not found");
            } else {
                System.out.println("Restaurant and RestaurantTable found:");
                System.out.println("╭────────────────────────────╮\n" +
                        "│ Code: " + restaurantTable.getCode() + "\n" +
                        "│ Number: " + restaurantTable.getNumber() + "\n" +
                        "│ Available: " + restaurantTable.getAvailable() + "\n" +
                        "│ Capacity: " + restaurantTable.getCapacity() + "\n" +
                        "│ Restaurant: " + restaurantTable.getRestaurant().getName() + "\n" +
                        "╰────────────────────────────╯");

                restaurantTable.setNumber(3);
                restaurantTable.setAvailable(true);
                restaurantTable.setCapacity(8);
                restaurantTable.setRestaurant(restaurant);

                RestaurantTableDAO restaurantTableDAO = new RestaurantTableDAO();
                restaurantTableDAO.update(restaurantTable);

                System.out.println("Restaurant Table updated:");
                System.out.println(
                        "╭────────────────────────────╮\n" +
                                "│ Code: " + restaurantTable.getCode() + "\n" +
                                "│ Number: " + restaurantTable.getNumber() + "\n" +
                                "│ Available: " + restaurantTable.getAvailable() + "\n" +
                                "│ Capacity: " + restaurantTable.getCapacity() + "\n" +
                                "│ Restaurant: " + restaurantTable.getRestaurant().getName() + "\n" +
                                "╰────────────────────────────╯");

            }
        }
    }

}
