package com.wrathcodes.restaurant.dao;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.wrathcodes.restaurant.domain.Customer;
import com.wrathcodes.restaurant.domain.OrderCustomer;

public class OrderCustomerDAOTest {

    @Test
    @Ignore
    public void save() {
        Long customerCode = 1L;

        // get customer
        CustomerDAO customerDAO = new CustomerDAO();
        Customer customer = customerDAO.search(customerCode);

        if (customerDAO.search(customerCode) == null) {
            System.out.println("Customer not found");
        } else {
            System.out.println("Customer found");

            // create a new order customer
            OrderCustomer orderCustomer = new OrderCustomer();
            orderCustomer.setOrderTotal(new BigDecimal(0.0));
            orderCustomer.setCustomer(customer);

            // create order customer DAO
            OrderCustomerDAO orderCustomerDAO = new OrderCustomerDAO();
            orderCustomerDAO.save(orderCustomer);

            System.out.println("Order customer saved successfully");
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + orderCustomer.getCode() + "\n" +
                    "│ Order Total: " + orderCustomer.getOrderTotal() + "\n" +
                    "│ Customer: " + orderCustomer.getCustomer().getName() + "\n" +
                    "╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void list() {
        OrderCustomerDAO orderCustomerDAO = new OrderCustomerDAO();

        List<OrderCustomer> result = orderCustomerDAO.list();

        System.out.println("Order Customer List");
        System.out.println("╭────────────────────────────╮");
        for (int position = 0; position < result.size(); position++) {
            if (position > 0) {
                System.out.println("├────────────────────────────┤");
                System.out.println("│ Code: " + result.get(position).getCode() + "\n" +
                        "│ Order Total: " + result.get(position).getOrderTotal() + "\n" +
                        "│ Customer: " + result.get(position).getCustomer().getName());
            } else {
                System.out.println("│ Code: " + result.get(position).getCode() + "\n" +
                        "│ Order Total: " + result.get(position).getOrderTotal() + "\n" +
                        "│ Customer: " + result.get(position).getCustomer().getName());
            }
        }
        System.out.println("╰────────────────────────────╯");
    }

    @Test
    @Ignore
    public void search() {
        Long orderCustomerCode = 1L;

        OrderCustomerDAO orderCustomerDAO = new OrderCustomerDAO();
        OrderCustomer orderCustomer = orderCustomerDAO.search(orderCustomerCode);

        if (orderCustomer == null) {
            System.out.println("Order Customer not found");
        } else {
            System.out.println("Order Customer found");
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + orderCustomer.getCode() + "\n" +
                    "│ Order Total: " + orderCustomer.getOrderTotal() + "\n" +
                    "│ Customer: " + orderCustomer.getCustomer().getName() + "\n" +
                    "╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void delete() {
        Long orderCustomerCode = 1L;

        OrderCustomerDAO orderCustomerDAO = new OrderCustomerDAO();
        OrderCustomer orderCustomer = orderCustomerDAO.search(orderCustomerCode);

        if (orderCustomer == null) {
            System.out.println("Order Customer not found");
        } else {
            orderCustomerDAO.delete(orderCustomer);
            System.out.println("Order Customer deleted");
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + orderCustomer.getCode() + "\n" +
                    "│ Order Total: " + orderCustomer.getOrderTotal() + "\n" +
                    "│ Customer: " + orderCustomer.getCustomer().getName() + "\n" +
                    "╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void update() {
        Long orderCustomerCode = 1L;

        OrderCustomerDAO orderCustomerDAO = new OrderCustomerDAO();
        OrderCustomer orderCustomer = orderCustomerDAO.search(orderCustomerCode);

        if (orderCustomer == null) {
            System.out.println("Order Customer not found");
        } else {
            System.out.println("Order Customer found");
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + orderCustomer.getCode() + "\n" +
                    "│ Order Total: " + orderCustomer.getOrderTotal() + "\n" +
                    "│ Customer: " + orderCustomer.getCustomer().getName() + "\n" +
                    "╰────────────────────────────╯");
            CustomerDAO customerDAO = new CustomerDAO();
            Customer customer = customerDAO.search(1L);

            orderCustomer.setOrderTotal(new BigDecimal(0.0));
            orderCustomer.setCustomer(customer);

            orderCustomerDAO.update(orderCustomer);
            System.out.println("Order Customer updated");
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + orderCustomer.getCode() + "\n" +
                    "│ Order Total: " + orderCustomer.getOrderTotal() + "\n" +
                    "│ Customer: " + orderCustomer.getCustomer().getName() + "\n" +
                    "╰────────────────────────────╯");
        }
    }
}
