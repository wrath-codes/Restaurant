package com.wrathcodes.restaurant.dao;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import com.wrathcodes.restaurant.domain.Category;
import com.wrathcodes.restaurant.domain.Ingredient;
import com.wrathcodes.restaurant.domain.Menu;
import com.wrathcodes.restaurant.domain.MenuItem;

public class MenuItemDAOTest {

    @Test
    @Ignore
    public void save() {
        Long menuCode = 2L;
        Long categoryCode = 1L;

        Long ingredientCode_01 = 1L;
        Long ingredientCode_02 = 2L;
        Long ingredientCode_03 = 3L;
        Long ingredientCode_04 = 4L;
        Long ingredientCode_05 = 5L;

        IngredientDAO ingredientDAO_01 = new IngredientDAO();
        IngredientDAO ingredientDAO_02 = new IngredientDAO();
        IngredientDAO ingredientDAO_03 = new IngredientDAO();
        IngredientDAO ingredientDAO_04 = new IngredientDAO();
        IngredientDAO ingredientDAO_05 = new IngredientDAO();

        Ingredient ingredient_01 = ingredientDAO_01.search(ingredientCode_01);
        Ingredient ingredient_02 = ingredientDAO_02.search(ingredientCode_02);
        Ingredient ingredient_03 = ingredientDAO_03.search(ingredientCode_03);
        Ingredient ingredient_04 = ingredientDAO_04.search(ingredientCode_04);
        Ingredient ingredient_05 = ingredientDAO_05.search(ingredientCode_05);

        MenuDAO menuDAO = new MenuDAO();
        CategoryDAO categoryDAO = new CategoryDAO();

        // get menu
        Menu menu = menuDAO.search(menuCode);

        // get category
        Category category = categoryDAO.search(categoryCode);

        if (menu == null || category == null || ingredient_01 == null || ingredient_02 == null || ingredient_03 == null
                || ingredient_04 == null || ingredient_05 == null) {
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

            Set<Ingredient> ingredients = new HashSet<Ingredient>();
            ingredients.add(ingredient_01);
            ingredients.add(ingredient_02);
            ingredients.add(ingredient_03);
            ingredients.add(ingredient_04);
            ingredients.add(ingredient_05);

            menuItem.setIngredients(ingredients);

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
                    "│ Ingredients: " + menuItem.getIngredients() + "\n" +
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
