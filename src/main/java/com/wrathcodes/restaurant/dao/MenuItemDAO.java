package com.wrathcodes.restaurant.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.wrathcodes.restaurant.domain.MenuItem;
import com.wrathcodes.restaurant.util.HibernateUtil;

public class MenuItemDAO extends GenericDAO<MenuItem> {

	@SuppressWarnings("unchecked")
	public List<MenuItem> list(Long menuCode) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria query = session.createCriteria(MenuItem.class);
			query.add(Restrictions.eq("menu.code", menuCode));

			List<MenuItem> result = query.list();
			System.out.println("Menu code: " + menuCode);
			System.out.println("Result: " + result);
			return result;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<MenuItem> list(Long menuCode, Long cotegoryCode) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria query = session.createCriteria(MenuItem.class);
			query.add(Restrictions.eq("menu.code", menuCode));
			query.add(Restrictions.eq("category.code", cotegoryCode));

			List<MenuItem> result = query.list();
			System.out.println("Menu Items: " + result);
			return result;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}
}
