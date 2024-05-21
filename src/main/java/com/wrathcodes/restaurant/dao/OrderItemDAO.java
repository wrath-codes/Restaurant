package com.wrathcodes.restaurant.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.wrathcodes.restaurant.util.HibernateUtil;

import com.wrathcodes.restaurant.domain.OrderItem;
import com.wrathcodes.restaurant.domain.OrderPriority;
import com.wrathcodes.restaurant.domain.OrderStatus;

public class OrderItemDAO extends GenericDAO<OrderItem> {

	@SuppressWarnings("unchecked")
	public List<OrderItem> list(Long customerCode) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria orderItemCriteria = session.createCriteria(OrderItem.class);
			// order customer
			Criteria orderCustomerCriteria = orderItemCriteria.createCriteria("orderCustomer");
			orderCustomerCriteria.add(Restrictions.eq("customer.code", customerCode));

			List<OrderItem> result = orderItemCriteria.list();

			System.out.println("Customer Orders from list(ccode): " + result);
			return result;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	public OrderItem getLatest(Long customerCode) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			// Get the latest added order item for a customer
			Criteria orderItemCriteria = session.createCriteria(OrderItem.class);
			// order customer
			Criteria orderCustomerCriteria = orderItemCriteria.createCriteria("orderCustomer");
			orderCustomerCriteria.add(Restrictions.eq("customer.code", customerCode));
			orderItemCriteria.setMaxResults(1);
			orderItemCriteria.addOrder(org.hibernate.criterion.Order.desc("orderedAt"));
			OrderItem result = (OrderItem) orderItemCriteria.uniqueResult();
			System.out.println("Latest OrderItem: " + result);
			return result;

		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	public void changeOrderStatus(Long orderItemCode, OrderStatus status) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			OrderItem orderItem = (OrderItem) session.get(OrderItem.class, orderItemCode);
			orderItem.setStatus(status);
			session.update(orderItem);
			transaction.commit();
		} catch (RuntimeException error) {
			if (transaction != null) {
				transaction.rollback();
				throw error;
			}
		} finally {
			session.close();
		}
	}

	public void changeOrderPriority(OrderItem orderItem, OrderPriority priority) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			orderItem.setPriority(priority);
			session.update(orderItem);
			transaction.commit();
		} catch (RuntimeException error) {
			if (transaction != null) {
				transaction.rollback();
				throw error;
			}
		} finally {
			session.close();
		}
	}

	// Lists all orders from active customers
	@SuppressWarnings("unchecked")
	public List<OrderItem> listAll(Long restaurantCode) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {

			// order item
			Criteria orderItemCriteria = session.createCriteria(OrderItem.class);

			// order customer
			Criteria orderCustomerCriteria = orderItemCriteria.createCriteria("orderCustomer");

			// customer
			Criteria customerCriteria = orderCustomerCriteria.createCriteria("customer");

			// table
			Criteria tableCriteria = customerCriteria.createCriteria("seatedAt");

			// restaurant

			tableCriteria.add(Restrictions.eq("restaurant.code", restaurantCode));

			List<OrderItem> result = orderItemCriteria.list();

			System.out.println("All Orders: " + result);

			return result;
		} catch (RuntimeException e) {
			e.printStackTrace();
			throw e;
		} finally {
			session.close();
		}
	}
}
