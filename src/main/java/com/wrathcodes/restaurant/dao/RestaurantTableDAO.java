package com.wrathcodes.restaurant.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
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
			System.out.println("Tables: " + result);
			return result;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}
}