package com.wrathcodes.restaurant.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.wrathcodes.restaurant.domain.Category;
import com.wrathcodes.restaurant.util.HibernateUtil;

public class CategoryDAO extends GenericDAO<Category> {

	@SuppressWarnings("unchecked")
	public List<Category> list(Long restaurantCode) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria query = session.createCriteria(Category.class);
			query.add(Restrictions.eq("restaurant.code", restaurantCode));

			List<Category> result = query.list();
			System.out.println("Restaurant code: " + restaurantCode);
			System.out.println("Categories: " + result);
			return result;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

}
