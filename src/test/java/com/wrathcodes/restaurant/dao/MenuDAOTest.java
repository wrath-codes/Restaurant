package com.wrathcodes.restaurant.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.wrathcodes.restaurant.domain.Menu;
import com.wrathcodes.restaurant.domain.Restaurant;

public class MenuDAOTest {

    @Test
    @Ignore
    public void save() {

        // get restaurant
        Long restaurantCode = 1L;
        Restaurant restaurant = new RestaurantDAO().search(restaurantCode);

        if (restaurant == null) {
            System.out.println("Restaurant not found");
        } else {
            System.out.println("Restaurant found: " + restaurant.getName());

            // create a new menu
            Menu menu = new Menu();
            menu.setName("Last Super");
            menu.setDescription("Last meal you'll ever eat");
            menu.setSeason("winter");
            menu.setAvailable(true);
            menu.setRestaurant(restaurant);

            // create menu DAO
            MenuDAO menuDAO = new MenuDAO();
            menuDAO.save(menu);

            System.out.println("Menu saved successfully");
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + menu.getCode() + "\n" +
                    "│ Name: " + menu.getName() + "\n" +
                    "│ Description: " + menu.getDescription() + "\n" +
                    "│ Season: " + menu.getSeason() + "\n" +
                    "│ Available: " + menu.getAvailable() + "\n" +
                    "│ Restaurant: " + menu.getRestaurant().getName() + "\n" +
                    "╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void list() {
        MenuDAO menuDAO = new MenuDAO();

        List<Menu> result = menuDAO.list();

        for (Menu menu : result) {
            System.out.println(
                    "Code: " + menu.getCode() + " - " + menu.getName() + " - " + menu.getRestaurant().getName());
        }
    }

    @Test
    @Ignore
    public void search() {
        Long code = 1L;
        MenuDAO menuDAO = new MenuDAO();
        Menu menu = menuDAO.search(code);

        if (menu == null) {
            System.err.println("Record not found");
        } else {
            System.out.println("Record found:");
            System.out.println(menu.getCode() + ": " + menu.getName());
        }
    }

    @Test
    @Ignore
    public void delete() {
        Long code = 1L;
        MenuDAO menuDAO = new MenuDAO();
        Menu menu = menuDAO.search(code);
        if (menu == null) {
            System.err.println("Record not found");
        } else {
            menuDAO.delete(menu);
            System.out.println("Record deleted:");
            System.out.println(menu.getCode() + ": " + menu.getName());
        }
    }

    @Test
    @Ignore
    public void update() {
        Long restaurantCode = 1L;
        Long menuCode = 2L;

        Restaurant restaurant = new RestaurantDAO().search(restaurantCode);
        Menu menu = new MenuDAO().search(menuCode);

        if (restaurant == null || menu == null) {
            System.out.println("Restaurant or Menu not found");
        } else {
            System.out.println("Restaurant and Menu found: " + restaurant.getName() + " - " + menu.getName());

            menu.setRestaurant(restaurant);
            menu.setName("Mom's Special");
            menu.setDescription("Owner's favorite foods and drinks");
            menu.setSeason("Winter");
            menu.setAvailable(true);

            MenuDAO menuDAO = new MenuDAO();
            menuDAO.update(menu);

            System.out.println("Record updated:");
            System.out.println(menu.getCode() + ": " + menu.getName());
            System.out.println("Description: " + menu.getDescription());
            System.out.println("Season: " + menu.getSeason());
            System.out.println("Available: " + menu.getAvailable());
        }
    }

}
