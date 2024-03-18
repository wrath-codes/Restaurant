package com.wrathcodes.restaurant.dao;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.wrathcodes.restaurant.domain.Customer;
import com.wrathcodes.restaurant.domain.RestaurantTable;

public class CustomerDAOTest {

    @Test
    @Ignore
    public void save() {
        // get restaurant table
        Long restaurantTableCode = 1L;

        RestaurantTable restaurantTable = new RestaurantTableDAO().search(restaurantTableCode);

        if (restaurantTable == null) {
            System.out.println("Restaurant table not found");
        } else {
            System.out.println("Restaurant table found: " + restaurantTable.getCode());

            // create a new customer
            Customer customer = new Customer();

            // customer.setName("Ronald McDonald");
            customer.setName("Homer Simpsom");
            customer.setSeatedAt(restaurantTable);

            // create customer DAO
            CustomerDAO customerDAO = new CustomerDAO();
            customerDAO.save(customer);

            System.out.println("Customer saved successfully");

            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + customer.getCode() + "\n" +
                    "│ Name: " + customer.getName() + "\n" +
                    "│ Restaurant: " + customer.getSeatedAt().getRestaurant().getName() + "\n" +
                    "│ Seated at: table " + customer.getSeatedAt().getNumber() + "\n" +
                    "╰────────────────────────────╯");

        }
    }

    @Test
    @Ignore
    public void list() {
        CustomerDAO customerDAO = new CustomerDAO();

        List<Customer> result = customerDAO.list();

        System.out.println("╭────────────────────────────╮");
        for (int position = 0; position < result.size(); position++) {
            if (position > 0) {
                System.out.println("├────────────────────────────┤");
                System.out.println(
                        "│ Code: " + result.get(position).getCode() + "\n" +
                                "│ Name: " + result.get(position).getName() + "\n" +
                                "│ Restaurant: " + result.get(position).getSeatedAt().getRestaurant().getName() + "\n" +
                                "│ Seated at: table " + result.get(position).getSeatedAt().getNumber());
            } else {
                System.out.println(
                        "│ Code: " + result.get(position).getCode() + "\n" +
                                "│ Name: " + result.get(position).getName() + "\n" +
                                "│ Restaurant: " + result.get(position).getSeatedAt().getRestaurant().getName() + "\n" +
                                "│ Seated at: table " + result.get(position).getSeatedAt().getNumber());
            }
        }
        System.out.println("╰────────────────────────────╯");
    }

    @Test
    @Ignore
    public void search() {
        Long code = 1L;
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.search(code);

        if (customer == null) {
            System.err.println("Record not found");
        } else {
            System.out.println("Record found:");
            System.out.println(
                    "╭────────────────────────────╮\n" +
                            "│ Code: " + customer.getCode() + "\n" +
                            "│ Name: " + customer.getName() + "\n" +
                            "│ Restaurant: " + customer.getSeatedAt().getRestaurant().getName() + "\n" +
                            "│ Seated at: table " + customer.getSeatedAt().getNumber() + "\n" +
                            "╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void delete() {
        Long code = 3L;
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.search(code);
        if (customer == null) {
            System.err.println("Record not found");
        } else {
            customerDAO.delete(customer);
            System.out.println("Record deleted:");
            System.out.println(
                    "╭────────────────────────────╮\n" +
                            "│ Code: " + customer.getCode() + "\n" +
                            "│ Name: " + customer.getName() + "\n" +
                            "│ Restaurant: " + customer.getSeatedAt().getRestaurant().getName() + "\n" +
                            "│ Seated at: table " + customer.getSeatedAt().getNumber() + "\n" +
                            "╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void update() {
        Long restaurantTableCode = 1L;
        Long customerCode = 2L;

        RestaurantTable restaurantTable = new RestaurantTableDAO().search(restaurantTableCode);
        if (restaurantTable == null) {
            System.out.println("Restaurant table not found");
        } else {
            System.out.println("Restaurant table found: " + restaurantTable.getCode());

            Customer customer = new CustomerDAO().search(customerCode);
            if (customer == null) {
                System.out.println("Customer not found");
            } else {
                System.out.println("Customer found: " + customer.getName());
                System.out.println("╭────────────────────────────╮\n" +
                        "│ Code: " + customer.getCode() + "\n" +
                        "│ Name: " + customer.getName() + "\n" +
                        "│ Restaurant: " + customer.getSeatedAt().getRestaurant().getName() + "\n" +
                        "│ Seated at: table " + customer.getSeatedAt().getNumber() + "\n" +
                        "╰────────────────────────────╯");

                RestaurantTable newRestaurantTable = new RestaurantTableDAO().search(2L);
                if (newRestaurantTable == null) {
                    System.out.println("New restaurant table not found");
                } else {
                    System.out.println("New restaurant table found: " + newRestaurantTable.getCode());

                    customer.setSeatedAt(newRestaurantTable);
                    new CustomerDAO().update(customer);

                    System.out.println("Customer updated successfully");
                    System.out.println("╭────────────────────────────╮\n" +
                            "│ Code: " + customer.getCode() + "\n" +
                            "│ Name: " + customer.getName() + "\n" +
                            "│ Restaurant: " + customer.getSeatedAt().getRestaurant().getName() + "\n" +
                            "│ Seated at: table " + customer.getSeatedAt().getNumber() + "\n" +
                            "╰────────────────────────────╯");
                }
            }
        }
    }
}
