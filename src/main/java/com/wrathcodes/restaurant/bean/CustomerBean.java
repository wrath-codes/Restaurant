package com.wrathcodes.restaurant.bean;

import java.io.IOException;

import java.io.Serializable;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.faces.context.FacesContext;

import org.omnifaces.util.Messages;

import com.wrathcodes.restaurant.dao.CustomerDAO;
import com.wrathcodes.restaurant.dao.OrderCustomerDAO;
import com.wrathcodes.restaurant.domain.Customer;
import com.wrathcodes.restaurant.domain.OrderCustomer;
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
	private OrderCustomer customerOrderTrack;

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

	public OrderCustomer getCustomerOrderTrack() {
		return customerOrderTrack;
	}

	public void setCustomerOrderTrack(OrderCustomer customerOrderTrack) {
		this.customerOrderTrack = customerOrderTrack;
	}

	public void findCustomerOrderTrack(Long customerCode) {
		OrderCustomerDAO orderCustomerDAO = new OrderCustomerDAO();
		customerOrderTrack = orderCustomerDAO.search(customerCode);
	}

	public void addCustomerOrderTrack() throws Exception {
		customerOrderTrack = new OrderCustomer();
		OrderCustomerDAO orderCustomerDAO = new OrderCustomerDAO();
		orderCustomerDAO.attach(currentRestaurant.getCode());
	}

	public void view(ActionEvent event) throws IOException {
		try {
			customer = (Customer) event.getComponent().getAttributes().get("selectedCustomer");
			FacesContext.getCurrentInstance().getExternalContext()
					.redirect("/Restaurant/pages/restaurant/customer.xhtml");
		} catch (RuntimeException error) {
			Messages.addGlobalError("An error occurred while trying to list customers");
			error.printStackTrace();
		}
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

	public void save() throws Exception {
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

	public void delete(ActionEvent event)
			throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		try {
			customer = (Customer) event.getComponent().getAttributes().get("selectedCustomer");
			Long customerCode = customer.getCode();

			OrderCustomerDAO orderCustomerDAO = new OrderCustomerDAO();
			orderCustomerDAO.delete(customerCode);

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
