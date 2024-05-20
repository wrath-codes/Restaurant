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
	
	@SuppressWarnings("unchecked")
	public List<MenuItem> list(Long restaurantCode, Boolean available){
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria menuItemCriteria = session.createCriteria(MenuItem.class);
			// get restaurant menus available
			Criteria menuCriteria = menuItemCriteria.createCriteria("menu");
			menuCriteria.add(Restrictions.eq("restaurant.code", restaurantCode));
			// only show items from available menus
			menuCriteria.add(Restrictions.eq("available", true));
			// only show available items
			menuItemCriteria.add(Restrictions.eq("available", available));
			
			List<MenuItem> result = menuItemCriteria.list();
			return result;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
		
	}

	public void delete(Long categoryCode) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.createQuery("delete from MenuItem where category_code = :category_code")
					.setParameter("category_code", categoryCode).executeUpdate();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			session.getTransaction().rollback();
			throw e;
		} finally {
			session.close();
		}
	}
}
