package com.wrathcodes.restaurant.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.wrathcodes.restaurant.domain.Category;
import com.wrathcodes.restaurant.util.HibernateUtil;

public class CategoryDAO extends GenericDAO<Category> {

	@SuppressWarnings("unchecked")
	public List<Category> list(Long menuCode) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria query = session.createCriteria(Category.class);
			query.add(Restrictions.eq("menu.code", menuCode));

			List<Category> result = query.list();
			return result;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}
	
	public Category search(String name, Long menuCode) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria query = session.createCriteria(Category.class);
			query.add(Restrictions.eq("name", name));
			query.add(Restrictions.eq("menu.code", menuCode));
			Category result = (Category) query.uniqueResult();
			
			System.out.println("Category at Search in DAO: " + result);

			return result;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

}
