package com.wrathcodes.restaurant.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.wrathcodes.restaurant.domain.OrderCustomer;
import com.wrathcodes.restaurant.domain.OrderItem;
import com.wrathcodes.restaurant.domain.OrderPriority;
import com.wrathcodes.restaurant.domain.OrderStatus;
import com.wrathcodes.restaurant.domain.MenuItem;

public class OrderItemDAOTest {

    @Test
    @Ignore
    public void save() {
        Long orderCustomerCode = 1L;
        Long menuItemCode = 1L;
        // create for menu item
        MenuItem menuItem = new MenuItemDAO().search(menuItemCode);
        OrderCustomer orderCustomer = new OrderCustomerDAO().search(orderCustomerCode);

        if (menuItem == null || orderCustomer == null) {
            System.out.println("Menu item or order customer not found");
        } else {
            System.out.println("Menu item found: " + menuItem.getName());
            System.out.println("Order for " + orderCustomer.getCustomer().getName() + " found");

            // create a new order item
            OrderItem orderItem = new OrderItem();
            orderItem.setStatus(OrderStatus.PENDING);
            orderItem.setPriority(OrderPriority.LOW);
            orderItem.setQuantity(1);
            orderItem.setOrderedAt(new Date());
            orderItem.setItem(menuItem);
            orderItem.setOrderCustomer(orderCustomer);

            // create order item DAO
            OrderItemDAO orderItemDAO = new OrderItemDAO();
            orderItemDAO.save(orderItem);

            System.out.println("Order item saved successfully");
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + orderItem.getCode() + "\n" +
                    "│ Status: " + orderItem.getStatus() + "\n" +
                    "│ Priority: " + orderItem.getPriority() + "\n" +
                    "│ Quantity: " + orderItem.getQuantity() + "\n" +
                    "│ Ordered At: " + orderItem.getOrderedAt() + "\n" +
                    "│ Item: " + orderItem.getItem().getName() + "\n" +
                    "│ Order Customer: " + orderItem.getOrderCustomer().getCustomer().getName() + "\n" +
                    "| Seated at table number: " + orderItem.getOrderCustomer().getCustomer().getSeatedAt().getNumber()
                    + "\n" +
                    "╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void list() {
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        List<OrderItem> result = orderItemDAO.list();

        System.out.println("Order Item List:");
        System.out.println("╭────────────────────────────╮");
        for (int position = 0; position < result.size(); position++) {
            if (position > 0) {
                System.out.println("├────────────────────────────┤");
                System.out.println("│ Code: " + result.get(position).getCode() + "\n" +
                        "│ Status: " + result.get(position).getStatus() + "\n" +
                        "│ Priority: " + result.get(position).getPriority() + "\n" +
                        "│ Quantity: " + result.get(position).getQuantity() + "\n" +
                        "│ Ordered At: " + result.get(position).getOrderedAt() + "\n" +
                        "│ Item: " + result.get(position).getItem().getName() + "\n" +
                        "│ Order Customer: " + result.get(position).getOrderCustomer().getCustomer().getName() + "\n" +
                        "│ Seated at table number: "
                        + result.get(position).getOrderCustomer().getCustomer().getSeatedAt().getNumber());
            } else {
                System.out.println("│ Code: " + result.get(position).getCode() + "\n" +
                        "│ Status: " + result.get(position).getStatus() + "\n" +
                        "│ Priority: " + result.get(position).getPriority() + "\n" +
                        "│ Quantity: " + result.get(position).getQuantity() + "\n" +
                        "│ Ordered At: " + result.get(position).getOrderedAt() + "\n" +
                        "│ Item: " + result.get(position).getItem().getName() + "\n" +
                        "│ Order Customer: " + result.get(position).getOrderCustomer().getCustomer().getName() + "\n" +
                        "│ Seated at table number: "
                        + result.get(position).getOrderCustomer().getCustomer().getSeatedAt().getNumber());
            }
        }
        System.out.println("╰────────────────────────────╯");
    }

    @Test
    @Ignore
    public void search() {
        Long code = 1L;
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        OrderItem orderItem = orderItemDAO.search(code);

        if (orderItem == null) {
            System.err.println("Record not found");
        } else {
            System.out.println("Record found:");
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + orderItem.getCode() + "\n" +
                    "│ Status: " + orderItem.getStatus() + "\n" +
                    "│ Priority: " + orderItem.getPriority() + "\n" +
                    "│ Quantity: " + orderItem.getQuantity() + "\n" +
                    "│ Ordered At: " + orderItem.getOrderedAt() + "\n" +
                    "│ Item: " + orderItem.getItem().getName() + "\n" +
                    "│ Order Customer: " + orderItem.getOrderCustomer().getCustomer().getName() + "\n" +
                    "│ Seated at table number: "
                    + orderItem.getOrderCustomer().getCustomer().getSeatedAt().getNumber() + "\n" +
                    "╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void delete() {
        Long code = 1L;
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        OrderItem orderItem = orderItemDAO.search(code);
        if (orderItem == null) {
            System.err.println("Record not found");
        } else {
            orderItemDAO.delete(orderItem);
            System.out.println("Record deleted:");
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + orderItem.getCode() + "\n" +
                    "│ Status: " + orderItem.getStatus() + "\n" +
                    "│ Priority: " + orderItem.getPriority() + "\n" +
                    "│ Quantity: " + orderItem.getQuantity() + "\n" +
                    "│ Ordered At: " + orderItem.getOrderedAt() + "\n" +
                    "│ Item: " + orderItem.getItem().getName() + "\n" +
                    "│ Order Customer: " + orderItem.getOrderCustomer().getCustomer().getName() + "\n" +
                    "│ Seated at table number: "
                    + orderItem.getOrderCustomer().getCustomer().getSeatedAt().getNumber() + "\n" +
                    "╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void update() {
        Long code = 1L;
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        OrderItem orderItem = orderItemDAO.search(code);
        if (orderItem == null) {
            System.err.println("Record not found");
        } else {
            orderItem.setStatus(OrderStatus.READY);
            orderItemDAO.update(orderItem);
            System.out.println("Record updated:");
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + orderItem.getCode() + "\n" +
                    "│ Status: " + orderItem.getStatus() + "\n" +
                    "│ Priority: " + orderItem.getPriority() + "\n" +
                    "│ Quantity: " + orderItem.getQuantity() + "\n" +
                    "│ Ordered At: " + orderItem.getOrderedAt() + "\n" +
                    "│ Item: " + orderItem.getItem().getName() + "\n" +
                    "│ Order Customer: " + orderItem.getOrderCustomer().getCustomer().getName() + "\n" +
                    "│ Seated at table number: "
                    + orderItem.getOrderCustomer().getCustomer().getSeatedAt().getNumber() + "\n" +
                    "╰────────────────────────────╯");
        }
    }

}
