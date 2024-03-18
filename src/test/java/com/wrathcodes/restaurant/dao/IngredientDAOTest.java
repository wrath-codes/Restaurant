package com.wrathcodes.restaurant.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.wrathcodes.restaurant.domain.Ingredient;

public class IngredientDAOTest {

    @Test
    @Ignore
    public void save() {

        // create a new ingredient
        Ingredient ingredient = new Ingredient();

        // Tomatoes
        // ingredient.setName("Tomato");
        // ingredient.setDescription("Red, round, and juicy");

        // Lettuce
        // ingredient.setName("Lettuce");
        // ingredient.setDescription("Green, leafy, and crunchy");

        // Cheese
        // ingredient.setName("Cheese");
        // ingredient.setDescription("Yellow, creamy, and salty");

        // Pickles
        // ingredient.setName("Pickles");
        // ingredient.setDescription("Green, crunchy, and sour");

        // Bread
        // ingredient.setName("Bread");
        // ingredient.setDescription("White, soft, and fluffy");

        // create ingredient DAO
        IngredientDAO ingredientDAO = new IngredientDAO();
        ingredientDAO.save(ingredient);

        System.out.println("Ingredient saved successfully");
        System.out.println("╭────────────────────────────╮\n" +
                "│ Code: " + ingredient.getCode() + "\n" +
                "│ Name: " + ingredient.getName() + "\n" +
                "│ Description: " + ingredient.getDescription() + "\n" +
                "╰────────────────────────────╯");
    }

    @Test
    @Ignore
    public void list() {
        IngredientDAO ingredientDAO = new IngredientDAO();

        List<Ingredient> result = ingredientDAO.list();

        System.out.println("╭────────────────────────────╮");
        for (int position = 0; position < result.size(); position++) {
            if (position > 0) {
                System.out.println("├────────────────────────────┤");
                System.out.println("│ Code: " + result.get(position).getCode() + "\n" +
                        "│ Name: " + result.get(position).getName() + "\n" +
                        "│ Description: " + result.get(position).getDescription());
            } else {
                System.out.println("│ Code: " + result.get(position).getCode() + "\n" +
                        "│ Name: " + result.get(position).getName() + "\n" +
                        "│ Description: " + result.get(position).getDescription());
            }
        }
        System.out.println("╰────────────────────────────╯");
    }

    @Test
    @Ignore
    public void search() {
        Long code = 1L;
        IngredientDAO ingredientDAO = new IngredientDAO();
        Ingredient ingredient = ingredientDAO.search(code);

        if (ingredient == null) {
            System.out.println("No records found");
        } else {
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + ingredient.getCode() + "\n" +
                    "│ Name: " + ingredient.getName() + "\n" +
                    "│ Description: " + ingredient.getDescription() + "\n" +
                    "╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void delete() {
        Long code = 1L;
        IngredientDAO ingredientDAO = new IngredientDAO();
        Ingredient ingredient = ingredientDAO.search(code);

        if (ingredient == null) {
            System.out.println("No records found");
        } else {
            ingredientDAO.delete(ingredient);
            System.out.println("Record deleted successfully");
            System.err.println("╭────────────────────────────╮\n" +
                    "│ Code: " + ingredient.getCode() + "\n" +
                    "│ Name: " + ingredient.getName() + "\n" +
                    "│ Description: " + ingredient.getDescription() + "\n" +
                    "╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void update() {
        Long code = 2L;
        IngredientDAO ingredientDAO = new IngredientDAO();
        Ingredient ingredient = ingredientDAO.search(code);

        if (ingredient == null) {
            System.out.println("No records found");
        } else {
            System.out.println("Before update:");
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + ingredient.getCode() + "\n" +
                    "│ Name: " + ingredient.getName() + "\n" +
                    "│ Description: " + ingredient.getDescription() + "\n" +
                    "╰────────────────────────────╯");

            ingredient.setName("Pepper");
            ingredient.setDescription("Red, hot, and spicy");

            ingredientDAO.update(ingredient);

            System.out.println("After update:");
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + ingredient.getCode() + "\n" +
                    "│ Name: " + ingredient.getName() + "\n" +
                    "│ Description: " + ingredient.getDescription() + "\n" +
                    "╰────────────────────────────╯");
        }
    }
}
