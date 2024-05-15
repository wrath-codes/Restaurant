package com.wrathcodes.restaurant.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.wrathcodes.restaurant.domain.Category;
import com.wrathcodes.restaurant.domain.Menu;
import com.wrathcodes.restaurant.domain.MenuItem;

public class MenuItemDAOTest {

    @Test
    @Ignore
    public void save() {
        Long menuCode = 2L;
        Long categoryCode = 1L;

        MenuDAO menuDAO = new MenuDAO();

        CategoryDAO categoryDAO = new CategoryDAO();

        // get menu
        Menu menu = menuDAO.search(menuCode);

        // get category
        Category category = categoryDAO.search(categoryCode);

        if (menu == null || category == null) {
            System.out.println("Menu or category not found, or ingredient not found");
        } else {
            System.out.println("Menu found: " + menu.getName());

            // create a new menu item
            MenuItem menuItem = new MenuItem();
            menuItem.setName("Cheeseburger");
            menuItem.setDescription("A delicious cheeseburger");
            menuItem.setPrice(new BigDecimal(15.73));
            menuItem.setAvailable(true);
            menuItem.setKitchen(true);
            menuItem.setMenu(menu);
            menuItem.setCategory(category);



            // create menu item DAO
            MenuItemDAO menuItemDAO = new MenuItemDAO();
            menuItemDAO.save(menuItem);

            System.out.println("Menu item saved successfully");
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + menuItem.getCode() + "\n" +
                    "│ Name: " + menuItem.getName() + "\n" +
                    "│ Description: " + menuItem.getDescription() + "\n" +
                    "│ Price: " + menuItem.getPrice() + "\n" +
                    "│ Available: " + menuItem.getAvailable() + "\n" +
                    "│ Kitchen: " + menuItem.getKitchen() + "\n" +
                    "│ Menu: " + menuItem.getMenu().getName() + "\n" +
                    "│ Category: " + menuItem.getCategory().getName() + "\n" +
                    "╰────────────────────────────╯");

        }

    }

    @Test
    @Ignore
    public void list() {
        MenuItemDAO menuItemDAO = new MenuItemDAO();

        List<MenuItem> result = menuItemDAO.list();

        System.out.println("╭────────────────────────────╮");
        for (int position = 0; position < result.size(); position++) {
            if (position > 0) {
                System.out.println("├────────────────────────────┤");
                System.out.println("│ Code: " + result.get(position).getCode() + "\n" +
                        "│ Name: " + result.get(position).getName() + "\n" +
                        "│ Description: " + result.get(position).getDescription() + "\n" +
                        "│ Price: " + result.get(position).getPrice() + "\n" +
                        "│ Available: " + result.get(position).getAvailable() + "\n" +
                        "│ Kitchen: " + result.get(position).getKitchen() + "\n" +
                        "│ Menu: " + result.get(position).getMenu().getName() + "\n" +
                        "│ Category: " + result.get(position).getCategory().getName());
            } else {
                System.out.println("│ Code: " + result.get(position).getCode() + "\n" +
                        "│ Name: " + result.get(position).getName() + "\n" +
                        "│ Description: " + result.get(position).getDescription() + "\n" +
                        "│ Price: " + result.get(position).getPrice() + "\n" +
                        "│ Available: " + result.get(position).getAvailable() + "\n" +
                        "│ Kitchen: " + result.get(position).getKitchen() + "\n" +
                        "│ Menu: " + result.get(position).getMenu().getName() + "\n" +
                        "│ Category: " + result.get(position).getCategory().getName());
            }
        }
        System.out.println("╰────────────────────────────╯");
    }

    @Test
    @Ignore
    public void search() {
        Long code = 1L;
        MenuItemDAO menuItemDAO = new MenuItemDAO();
        MenuItem menuItem = menuItemDAO.search(code);

        if (menuItem == null) {
            System.out.println("No records found");
        } else {

            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + menuItem.getCode() + "\n" +
                    "│ Name: " + menuItem.getName() + "\n" +
                    "│ Description: " + menuItem.getDescription() + "\n" +
                    "│ Price: " + menuItem.getPrice() + "\n" +
                    "│ Available: " + menuItem.getAvailable() + "\n" +
                    "│ Kitchen: " + menuItem.getKitchen() + "\n" +
                    "│ Menu: " + menuItem.getMenu().getName() + "\n" +
                    "│ Category: " + menuItem.getCategory().getName() + "\n");
            System.out.println("╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void delete() {
        Long code = 2L;
        MenuItemDAO menuItemDAO = new MenuItemDAO();
        MenuItem menuItem = menuItemDAO.search(code);

        if (menuItem == null) {
            System.out.println("No records found");
        } else {
            menuItemDAO.delete(menuItem);
            System.out.println("Menu item deleted successfully");

            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + menuItem.getCode() + "\n" +
                    "│ Name: " + menuItem.getName() + "\n" +
                    "│ Description: " + menuItem.getDescription() + "\n" +
                    "│ Price: " + menuItem.getPrice() + "\n" +
                    "│ Available: " + menuItem.getAvailable() + "\n" +
                    "│ Kitchen: " + menuItem.getKitchen() + "\n" +
                    "│ Menu: " + menuItem.getMenu().getName() + "\n" +
                    "│ Category: " + menuItem.getCategory().getName() + "\n" +
                    "╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void update() {
        Long code = 1L;
        MenuItemDAO menuItemDAO = new MenuItemDAO();
        MenuItem menuItem = menuItemDAO.search(code);

        if (menuItem == null) {
            System.out.println("No records found");
        } else {
            System.out.println("Before update:");
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + menuItem.getCode() + "\n" +
                    "│ Name: " + menuItem.getName() + "\n" +
                    "│ Description: " + menuItem.getDescription() + "\n" +
                    "│ Price: " + menuItem.getPrice() + "\n" +
                    "│ Available: " + menuItem.getAvailable() + "\n" +
                    "│ Kitchen: " + menuItem.getKitchen() + "\n" +
                    "│ Menu: " + menuItem.getMenu().getName() + "\n" +
                    "│ Category: " + menuItem.getCategory().getName() + "\n" +
                    "╰────────────────────────────╯");

            menuItem.setName("Quarter Pounder");
            menuItem.setDescription("A delicious quarter pounder");
            menuItem.setPrice(new BigDecimal(18.78));
            menuItem.setAvailable(true);
            menuItem.setKitchen(true);
            menuItemDAO.update(menuItem);

            System.out.println("Menu item updated successfully");
            System.out.println("After update:");
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + menuItem.getCode() + "\n" +
                    "│ Name: " + menuItem.getName() + "\n" +
                    "│ Description: " + menuItem.getDescription() + "\n" +
                    "│ Price: " + menuItem.getPrice() + "\n" +
                    "│ Available: " + menuItem.getAvailable() + "\n" +
                    "│ Kitchen: " + menuItem.getKitchen() + "\n" +
                    "│ Menu: " + menuItem.getMenu().getName() + "\n" +
                    "│ Category: " + menuItem.getCategory().getName() + "\n" +
                    "╰────────────────────────────╯");
        }
    }

}
