package com.wrathcodes.restaurant.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.wrathcodes.restaurant.domain.Customer;
import com.wrathcodes.restaurant.util.HibernateUtil;

public class CustomerDAO extends GenericDAO<Customer> {
	@SuppressWarnings("unchecked")
	public List<Customer> list(Long RestaurantCode) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria query = session.createCriteria(Customer.class);
			query.add(Restrictions.eq("seatedAt.restaurant.code", RestaurantCode));

			List<Customer> result = query.list();
			System.out.println("Restaurant code: " + RestaurantCode);
			System.out.println("Result: " + result);
			return result;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<Customer> list(Long RestaurantCode, Long TableCode) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria query = session.createCriteria(Customer.class);
			query.add(Restrictions.eq("seatedAt.restaurant.code", RestaurantCode));
			query.add(Restrictions.eq("seatedAt.code", TableCode));

			List<Customer> result = query.list();
			System.out.println("Restaurant code: " + RestaurantCode);
			System.out.println("Table code: " + TableCode);
			System.out.println("Result: " + result);
			return result;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}
}