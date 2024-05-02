package com.wrathcodes.restaurant.bean;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.wrathcodes.restaurant.dao.RestaurantTableDAO;
import com.wrathcodes.restaurant.domain.Restaurant;
import com.wrathcodes.restaurant.domain.RestaurantTable;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class RestaurantTableBean implements Serializable {
	private RestaurantTable table;
	private List<RestaurantTable> tables;
	private Restaurant currentRestaurant;

	public Restaurant getCurrentRestaurant() {
		return currentRestaurant;
	}

	public void setCurrentRestaurant(Restaurant currentRestaurant) {
		this.currentRestaurant = currentRestaurant;
	}

	public RestaurantTable getTable() {
		return table;
	}

	public void setTable(RestaurantTable table) {
		this.table = table;
	}

	public List<RestaurantTable> getTables() {
		return tables;
	}

	public void setTables(List<RestaurantTable> tables) {
		this.tables = tables;
	}

	public void viewTables() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("/Restaurant/pages/restaurant/tables.xhtml"
				+ "?code=" + currentRestaurant.getCode() + "&faces-redirect=true" + "&includeViewParams=true");
		list();
	}

	@PostConstruct
	public void list() {
		try {
			RestaurantTableDAO restaurantTableDAO = new RestaurantTableDAO();
			tables = restaurantTableDAO.list(currentRestaurant.getCode());
			System.out.println("Current restaurant code: " + currentRestaurant.getCode());
			System.out.println("Current restaurant name: " + currentRestaurant.getName());
			System.out.println("Current tables: " + tables);
		} catch (RuntimeException error) {
			error.printStackTrace();
		}
	}

	public void add() {
		table = new RestaurantTable(currentRestaurant);
		table.setRestaurant(currentRestaurant);
	}

	public void save() throws SQLIntegrityConstraintViolationException {
		try {
			RestaurantTableDAO restaurantTableDAO = new RestaurantTableDAO();

			restaurantTableDAO.merge(table);

			add();

			System.out.println("Restaurant code: " + table.getRestaurant().getCode());

			list();

			Messages.addGlobalInfo("Table saved successfully");
		} catch (RuntimeException error) {
			if (error.getCause().toString().contains("SQLIntegrityConstraintViolationException")) {
				error.printStackTrace();
				Messages.addGlobalError("Table number already exists");
			} else {
				error.printStackTrace();
				Messages.addGlobalError("Error saving table");
			}
		}
	}

	public void delete(ActionEvent event) {
		try {
			RestaurantTableDAO restaurantTableDAO = new RestaurantTableDAO();
			table = (RestaurantTable) event.getComponent().getAttributes().get("selectedTable");
			restaurantTableDAO.delete(table);

			tables = restaurantTableDAO.list();

			Messages.addGlobalInfo("Table deleted successfully");
		} catch (RuntimeException error) {
			error.printStackTrace();
		}
	}

	public void edit(ActionEvent event) {
		table = (RestaurantTable) event.getComponent().getAttributes().get("selectedTable");
	}

}
