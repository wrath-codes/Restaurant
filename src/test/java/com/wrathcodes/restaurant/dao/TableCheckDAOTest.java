package com.wrathcodes.restaurant.dao;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

import com.wrathcodes.restaurant.domain.RestaurantTable;
import com.wrathcodes.restaurant.domain.TableCheck;

public class TableCheckDAOTest {

    @Test
    @Ignore
    public void save() {
        Long tableCode = 1L;

        RestaurantTable table = new RestaurantTableDAO().search(tableCode);

        if (table == null) {
            System.out.println("Table not found");
        } else {
            System.out.println("Table found: " + table.getNumber());

            TableCheck tableCheck = new TableCheck();
            tableCheck.setClosed(false);
            tableCheck.setTotal(new BigDecimal(0.0));
            tableCheck.setRestaurantTable(table);

            TableCheckDAO tableCheckDAO = new TableCheckDAO();
            tableCheckDAO.save(tableCheck);

            System.out.println("Table check saved successfully");
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + tableCheck.getCode() + "\n" +
                    "│ Closed: " + tableCheck.getClosed() + "\n" +
                    "│ Total: " + tableCheck.getTotal() + "\n" +
                    "│ Table: " + tableCheck.getRestaurantTable().getNumber() + "\n" +
                    "╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void search() {
        Long tableCheckCode = 1L;
        TableCheck tableCheck = new TableCheckDAO().search(tableCheckCode);

        if (tableCheck == null) {
            System.out.println("Table check not found");
        } else {
            System.out.println("Table check found: " + tableCheck.getCode());
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + tableCheck.getCode() + "\n" +
                    "│ Closed: " + tableCheck.getClosed() + "\n" +
                    "│ Total: " + tableCheck.getTotal() + "\n" +
                    "│ Table: " + tableCheck.getRestaurantTable().getNumber() + "\n" +
                    "╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void list() {
        TableCheckDAO tableCheckDAO = new TableCheckDAO();
        for (TableCheck tableCheck : tableCheckDAO.list()) {
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + tableCheck.getCode() + "\n" +
                    "│ Closed: " + tableCheck.getClosed() + "\n" +
                    "│ Total: " + tableCheck.getTotal() + "\n" +
                    "│ Table: " + tableCheck.getRestaurantTable().getNumber() + "\n" +
                    "╰────────────────────────────╯");
        }
    }

    @Test
    @Ignore
    public void delete() {
        Long tableCheckCode = 1L;
        TableCheck tableCheck = new TableCheckDAO().search(tableCheckCode);

        if (tableCheck == null) {
            System.out.println("Table check not found");
        } else {
            TableCheckDAO tableCheckDAO = new TableCheckDAO();
            tableCheckDAO.delete(tableCheck);

            System.out.println("Table check deleted successfully");
        }
    }

    @Test
    @Ignore
    public void update() {
        Long tableCheckCode = 1L;
        TableCheck tableCheck = new TableCheckDAO().search(tableCheckCode);

        if (tableCheck == null) {
            System.out.println("Table check not found");
        } else {
            tableCheck.setClosed(true);
            tableCheck.setTotal(new BigDecimal(100.0));

            TableCheckDAO tableCheckDAO = new TableCheckDAO();
            tableCheckDAO.update(tableCheck);

            System.out.println("Table check updated successfully");
            System.out.println("╭────────────────────────────╮\n" +
                    "│ Code: " + tableCheck.getCode() + "\n" +
                    "│ Closed: " + tableCheck.getClosed() + "\n" +
                    "│ Total: " + tableCheck.getTotal() + "\n" +
                    "│ Table: " + tableCheck.getRestaurantTable().getNumber() + "\n" +
                    "╰────────────────────────────╯");
        }
    }
}
