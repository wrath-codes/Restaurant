package com.wrathcodes.restaurant.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.wrathcodes.restaurant.domain.RestaurantTable;
import com.wrathcodes.restaurant.util.HibernateUtil;

public class RestaurantTableDAO extends GenericDAO<RestaurantTable> {
	@SuppressWarnings("unchecked")
	public List<RestaurantTable> list(Long restaurantCode) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria query = session.createCriteria(RestaurantTable.class);
			query.add(Restrictions.eq("restaurant.code", restaurantCode));

			List<RestaurantTable> result = query.list();
			System.out.println("Restaurant code: " + restaurantCode);
			System.out.println("Result: " + result);
			return result;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	public void merge(RestaurantTable table) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			RestaurantTableDAO dao = new RestaurantTableDAO();
			RestaurantTable existingTable = dao.search(table.getCode());

			// Restaurant code and number are unique together i want to change the number of
			// the table
			if (existingTable != null && existingTable.getNumber() != table.getNumber()) {
				existingTable.setNumber(table.getNumber());
				session.merge(existingTable);
				transaction.commit();
			} else {
				session.merge(table);
				transaction.commit();
			}
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}
}