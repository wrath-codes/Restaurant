package com.wrathcodes.restaurant.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.wrathcodes.restaurant.domain.Menu;
import com.wrathcodes.restaurant.util.HibernateUtil;

public class MenuDAO extends GenericDAO<Menu> {

	@SuppressWarnings("unchecked")
	public List<Menu> list(Long restaurantCode) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria query = session.createCriteria(Menu.class);
			query.add(Restrictions.eq("restaurant.code", restaurantCode));

			List<Menu> result = query.list();
			System.out.println("Menus: " + result);
			return result;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

}
