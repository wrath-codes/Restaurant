package com.wrathcodes.restaurant.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.wrathcodes.restaurant.domain.OrderCustomer;
import com.wrathcodes.restaurant.domain.OrderItem;
import com.wrathcodes.restaurant.domain.TableCheck;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class OrderBean implements Serializable  {
	private OrderItem order;
	private List<OrderItem> orders;
	private OrderCustomer customerOrderTrack;
	private List<OrderCustomer> tableOrdersTrack;
	private TableCheck currentTableCheck;
	private List<TableCheck> tableChecks;

	public OrderItem getOrder() {
		return order;
	}
	
	public void setOrder(OrderItem order) {
		this.order = order;
	}
	
	public List<OrderItem> getOrders() {
		return orders;
	}
	
	public void setOrders(List<OrderItem> orders) {
		this.orders = orders;
	}
	
	public OrderCustomer getCustomerOrderTrack() {
		return customerOrderTrack;
	}
	
	public void setCustomerOrderTrack(OrderCustomer customerOrderTrack) {
		this.customerOrderTrack = customerOrderTrack;
	}
	
	public List<OrderCustomer> getTableOrdersTrack() {
		return tableOrdersTrack;
	}
	
	public void setTableOrdersTrack(List<OrderCustomer> tableOrdersTrack) {
		this.tableOrdersTrack = tableOrdersTrack;
	}
	
		
	public TableCheck getCurrentTableCheck() {
		return currentTableCheck;
	}
	
	public void setCurrentTableCheck(TableCheck currentTableCheck) {
		this.currentTableCheck = currentTableCheck;
	}
	
	public List<TableCheck> getTableChecks() {
		return tableChecks;
	}
	
	public void setTableChecks(List<TableCheck> tableChecks) {
		this.tableChecks = tableChecks;
	}
		

}
