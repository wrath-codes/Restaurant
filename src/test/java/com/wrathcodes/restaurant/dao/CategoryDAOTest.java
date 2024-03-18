package com.wrathcodes.restaurant.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.wrathcodes.restaurant.domain.Category;

public class CategoryDAOTest {

    @Test
    @Ignore
    public void save() {
        // create a new category
        Category category = new Category();
        // category.setName("Pizza");
        // category.setDescription("All about pizza");

        category.setName("Burgers");
        category.setDescription("All about burgers");

        // create category DAO
        CategoryDAO categoryDAO = new CategoryDAO();
        categoryDAO.save(category);
    }

    @Test
    @Ignore
    public void list() {
        CategoryDAO categoryDAO = new CategoryDAO();
        List<Category> result = categoryDAO.list();

        // print the result
        System.err.println("Total of categories: " + result.size());
        for (Category category : result) {
            System.err.println(category.getCode() + " - " + category.getName() + ": " + category.getDescription());

        }
    }

    @Test
    @Ignore
    public void search() {
        Long code = 1L;
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = categoryDAO.search(code);

        if (category == null) {
            System.err.println("Record not found");
        } else {
            System.err.println("Record found:");
            System.err.println(category.getName() + ": " + category.getDescription());
        }
    }

    @Test
    @Ignore
    public void delete() {
        Long code = 1L;
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = categoryDAO.search(code);

        if (category == null) {
            System.err.println("Record not found");
        } else {
            categoryDAO.delete(category);
            System.err.println("Record deleted");
        }
    }

    @Test
    @Ignore
    public void update() {
        Long code = 2L;
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = categoryDAO.search(code);

        if (category == null) {
            System.err.println("Record not found");
        } else {
            System.err.println("Record found:");
            System.err.println(category.getName() + ": " + category.getDescription());
            category.setName("Kebab");
            category.setDescription("All about the kebab");
            categoryDAO.update(category);
            System.err.println("Record updated:");
            System.err.println(category.getName() + ": " + category.getDescription());
        }
    }
}
