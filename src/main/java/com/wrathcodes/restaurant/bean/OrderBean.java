package com.wrathcodes.restaurant.bean;

import com.wrathcodes.restaurant.dao.OrderCustomerDAO;
import com.wrathcodes.restaurant.dao.OrderItemDAO;
import com.wrathcodes.restaurant.domain.Customer;
import com.wrathcodes.restaurant.domain.OrderCustomer;
import com.wrathcodes.restaurant.domain.OrderItem;
import com.wrathcodes.restaurant.domain.TableCheck;
import com.wrathcodes.restaurant.domain.OrderStatus;
import com.wrathcodes.restaurant.domain.OrderPriority;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;
import org.omnifaces.util.Messages;

@ManagedBean
@SessionScoped
@SuppressWarnings("serial")
public class OrderBean implements Serializable {
	private OrderItem order;
	private List<OrderItem> orders;
	private OrderCustomer customerOrderTrack;
	private List<OrderCustomer> tableOrdersTrack;
	private TableCheck currentTableCheck;
	private List<TableCheck> tableChecks;
	private Customer customer;

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@PostConstruct
	public void list() {
		try {
			OrderItemDAO orderDAO = new OrderItemDAO();
			orders = orderDAO.list(customer.getCode());

		} catch (RuntimeException e) {
			Messages.addGlobalError("An error occurred while trying to list the orders");
			e.printStackTrace();
		}
	}

	public void add() {
//		OrderCustomerDAO orderCustomerDAO = new OrderCustomerDAO();
//		customerOrderTrack = orderCustomerDAO.search(customer.getCode());
//		if (customerOrderTrack == null) {
//			customerOrderTrack = new OrderCustomer();
//			customerOrderTrack.setCustomer(customer);
//			orderCustomerDAO.merge(customerOrderTrack);
//		}
		order = new OrderItem();
		order.setOrderCustomer(customerOrderTrack);
	}

	public void addToOrderTotal(Long customerCode) {
		OrderItemDAO orderDAO = new OrderItemDAO();
		OrderItem latestOrder = orderDAO.getLatest(customerCode);
		OrderCustomerDAO orderCustomerDAO = new OrderCustomerDAO();
		customerOrderTrack = orderCustomerDAO.search(customerCode);

		if (latestOrder != null && customerOrderTrack != null) {
			customerOrderTrack.setOrderTotal(customerOrderTrack.getOrderTotal()
					.add(latestOrder.getItem().getPrice().multiply(new BigDecimal(latestOrder.getQuantity()))));
			orderCustomerDAO.merge(customerOrderTrack);
		} else {
			OrderCustomer orderCustomer = new OrderCustomer();
			orderCustomer.setCustomer(customer);
			orderCustomer.setOrderTotal(order.getItem().getPrice().multiply(new BigDecimal(order.getQuantity())));
			orderCustomerDAO.merge(orderCustomer);
			customerOrderTrack = orderCustomerDAO.search(customerCode);
		}

		list();
	}

	public void subtractFromOrderTotal(OrderItem order) {
		OrderCustomerDAO orderCustomerDAO = new OrderCustomerDAO();
		customerOrderTrack = orderCustomerDAO.search(customer.getCode());
		customerOrderTrack.setOrderTotal(customerOrderTrack.getOrderTotal()
				.subtract(order.getItem().getPrice().multiply(new BigDecimal(order.getQuantity()))));
		orderCustomerDAO.merge(customerOrderTrack);
	}

	public void cancelOrderItem(ActionEvent event) {
		OrderItem order = (OrderItem) event.getComponent().getAttributes().get("selectedOrder");
		System.out.println("Order: " + order.getItem().getName() + " - " + order.getQuantity() + "x");
		OrderItemDAO orderDAO = new OrderItemDAO();
		subtractFromOrderTotal(order);
		orderDAO.changeOrderStatus(order.getCode(), OrderStatus.CANCELED);
		list();
	}
	
	public void finishOrder(ActionEvent event) {
		OrderItem order = (OrderItem) event.getComponent().getAttributes().get("selectedOrder");
		OrderItemDAO orderDAO = new OrderItemDAO();
		orderDAO.changeOrderStatus(order.getCode(), OrderStatus.READY);
		list();
	}
	
	public void startOrder(ActionEvent event) {
		OrderItem order = (OrderItem) event.getComponent().getAttributes().get("selectedOrder");
		OrderItemDAO orderDAO = new OrderItemDAO();
		orderDAO.changeOrderStatus(order.getCode(), OrderStatus.IN_PROGRESS);
		list();
	}

	public void increasePriority(ActionEvent event) {
		OrderItem order = (OrderItem) event.getComponent().getAttributes().get("selectedOrder");
		OrderItemDAO orderDAO = new OrderItemDAO();

		if (order.getPriority() == OrderPriority.MEDIUM) {
			orderDAO.changeOrderPriority(order, OrderPriority.HIGH);
		}
		if (order.getPriority() == OrderPriority.LOW) {
			orderDAO.changeOrderPriority(order, OrderPriority.MEDIUM);
		}

		list();
	}

	public void lowerPriority(ActionEvent event) {
		OrderItem order = (OrderItem) event.getComponent().getAttributes().get("selectedOrder");
		OrderItemDAO orderDAO = new OrderItemDAO();

		if (order.getPriority() == OrderPriority.MEDIUM) {
			orderDAO.changeOrderPriority(order, OrderPriority.LOW);
		}
		if (order.getPriority() == OrderPriority.HIGH) {
			orderDAO.changeOrderPriority(order, OrderPriority.MEDIUM);
		}
		
		if (order.getPriority() == OrderPriority.FIRST) {
			orderDAO.changeOrderPriority(order, OrderPriority.HIGH);
		}

		list();
	}

	public void bumpToFirst(ActionEvent event) {
		OrderItem order = (OrderItem) event.getComponent().getAttributes().get("selectedOrder");
		OrderItemDAO orderDAO = new OrderItemDAO();

		orderDAO.changeOrderPriority(order, OrderPriority.FIRST);

		list();
	}

	public void updateOrderCustomer() {
		OrderCustomerDAO orderCustomerDAO = new OrderCustomerDAO();
		customerOrderTrack = orderCustomerDAO.search(customer.getCode());
	}

	public void save() {
		try {
			OrderItemDAO orderDAO = new OrderItemDAO();
			orderDAO.merge(order);

			add();
			list();
			addToOrderTotal(customer.getCode());
		} catch (RuntimeException e) {
			Messages.addGlobalError("An error occurred while trying to save the order");
			e.printStackTrace();
		}
	}

	public void delete(ActionEvent event) {
		try {
			order = (OrderItem) event.getComponent().getAttributes().get("selectedOrder");
			OrderItemDAO orderDAO = new OrderItemDAO();
			orderDAO.delete(order);
			list();
		} catch (RuntimeException e) {
			Messages.addGlobalError("An error occurred while trying to delete the order");
			e.printStackTrace();
		}
	}

	public void editItem(ActionEvent event) {
		order = (OrderItem) event.getComponent().getAttributes().get("selectedOrder");
	}

}
