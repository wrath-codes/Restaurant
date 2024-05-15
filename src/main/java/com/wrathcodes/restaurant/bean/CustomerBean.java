package com.wrathcodes.restaurant.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import com.wrathcodes.restaurant.dao.CustomerDAO;
import com.wrathcodes.restaurant.domain.Customer;
import com.wrathcodes.restaurant.domain.Restaurant;
import com.wrathcodes.restaurant.domain.RestaurantTable;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class CustomerBean implements Serializable {
	private Customer customer;
	private List<Customer> customers;
	private RestaurantTable currentTable;
	private Restaurant currentRestaurant;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public RestaurantTable getCurrentTable() {
		return currentTable;
	}

	public void setCurrentTable(RestaurantTable currentTable) {
		this.currentTable = currentTable;
	}

	public Restaurant getCurrentRestaurant() {
		return currentRestaurant;
	}

	public void setCurrentRestaurant(Restaurant currentRestaurant) {
		this.currentRestaurant = currentRestaurant;
	}

	@PostConstruct
	public void list() {
		try {
			CustomerDAO customerDAO = new CustomerDAO();
			customers = customerDAO.list(currentRestaurant.getCode());
		} catch (RuntimeException error) {
			Messages.addGlobalError("An error occurred while trying to list customers");
			error.printStackTrace();
		}
	}

	public void add() {
		customer = new Customer();
		customer.setSeatedAt(currentTable);
	}

	public void save() {
		try {
			CustomerDAO customerDAO = new CustomerDAO();
			customerDAO.merge(customer);

			add();
			customers = customerDAO.list(currentRestaurant.getCode());

			Messages.addGlobalInfo("Customer saved successfully");
		} catch (RuntimeException error) {
			Messages.addGlobalError("An error occurred while trying to save the customer");
			error.printStackTrace();
		}
	}

	public void delete(ActionEvent event) {
		try {
			customer = (Customer) event.getComponent().getAttributes().get("selectedCustomer");

			CustomerDAO customerDAO = new CustomerDAO();
			customerDAO.delete(customer);

			customers = customerDAO.list();

			Messages.addGlobalInfo("Customer removed successfully");
		} catch (RuntimeException error) {
			Messages.addGlobalError("An error occurred while trying to remove the customer");
			error.printStackTrace();
		}
	}

	public void edit(ActionEvent event) {
		customer = (Customer) event.getComponent().getAttributes().get("selectedCustomer");
	}

}
