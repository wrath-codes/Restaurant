package com.wrathcodes.restaurant.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Order;

import com.wrathcodes.restaurant.domain.Customer;
import com.wrathcodes.restaurant.util.HibernateUtil;

public class CustomerDAO extends GenericDAO<Customer> {
	@SuppressWarnings("unchecked")
	public List<Customer> list(Long restaurantCode) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria customerCriteria = session.createCriteria(Customer.class);
			Criteria tableCriteria = customerCriteria.createCriteria("seatedAt");
			tableCriteria.add(Restrictions.eq("restaurant.code", restaurantCode));

			List<Customer> result = customerCriteria.list();

			System.out.println("Restaurant code: " + restaurantCode);
			System.out.println("Customers: " + result);

			return result;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Customer> list(Long restaurantCode, Long tableCode) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria customerCriteria = session.createCriteria(Customer.class);
			Criteria tableCriteria = customerCriteria.createCriteria("seatedAt");
			tableCriteria.add(Restrictions.eq("restaurant.code", restaurantCode));
			tableCriteria.add(Restrictions.eq("code", tableCode));

			List<Customer> result = customerCriteria.list();
			System.out.println("Restaurant code: " + restaurantCode);
			System.out.println("Table code: " + tableCode);
			System.out.println("Customers: " + result);
			return result;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	public Customer getLatest(Long restaurantCode) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria customerCriteria = session.createCriteria(Customer.class);
			Criteria tableCriteria = customerCriteria.createCriteria("seatedAt");
			tableCriteria.add(Restrictions.eq("code", restaurantCode));
			customerCriteria.setMaxResults(1);
			customerCriteria.addOrder(Order.desc("createdAt"));
			Customer result = (Customer) customerCriteria.uniqueResult();
			System.out.println("Latest Customer: " + result);
			return result;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

}
