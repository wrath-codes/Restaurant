package com.wrathcodes.restaurant.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import com.wrathcodes.restaurant.util.HibernateUtil;

import com.wrathcodes.restaurant.domain.OrderItem;

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

}
