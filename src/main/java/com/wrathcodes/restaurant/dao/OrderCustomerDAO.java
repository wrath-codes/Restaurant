package com.wrathcodes.restaurant.dao;

import java.util.List;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.hibernate.criterion.Restrictions;
import org.hibernate.Criteria;
import org.hibernate.Session;
import com.wrathcodes.restaurant.util.HibernateUtil;
import com.wrathcodes.restaurant.domain.Customer;
import com.wrathcodes.restaurant.domain.OrderCustomer;

public class OrderCustomerDAO extends GenericDAO<OrderCustomer> {

	public OrderCustomer search(Long customerCode) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			OrderCustomer result = (OrderCustomer) session.get(OrderCustomer.class, customerCode);
			return result;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

	public void delete(Long customerCode)
			throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SystemException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = (Transaction) session.beginTransaction();
			OrderCustomer orderCustomer = search(customerCode);
			session.delete(orderCustomer);
			transaction.commit();
		} catch (RuntimeException error) {
			if (transaction != null) {
				transaction.rollback();
				throw error;
			}
		} finally {
			session.close();
		}
	}

	public void attach(Long restaurantCode) throws Exception {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = (Transaction) session.beginTransaction();
			CustomerDAO customerDAO = new CustomerDAO();
			Customer customer = customerDAO.getLatest(restaurantCode);

			OrderCustomer orderCustomer = new OrderCustomer();
			orderCustomer.setCustomer(customer);

			session.save(orderCustomer);
			transaction.commit();

			System.out.println("OrderCustomer saved: " + orderCustomer);
		} catch (RuntimeException | RollbackException | HeuristicMixedException | HeuristicRollbackException
				| SystemException error) {
			if (transaction != null) {
				transaction.rollback();
				throw error;
			}
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<OrderCustomer> search(Long restaurantCode, Long tableCode) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			Criteria orderCustomerCriteria = session.createCriteria(OrderCustomer.class);
			
			Criteria customerCriteria = orderCustomerCriteria.createCriteria("customer");

			Criteria tableCriteria = customerCriteria.createCriteria("seatedAt");

			tableCriteria.add(Restrictions.eq("code", tableCode));
			
			tableCriteria.add(Restrictions.eq("restaurant.code", restaurantCode));
			
			List<OrderCustomer> result = orderCustomerCriteria.list();

			System.out.println("OrderCustomers from search(rcode, tcode): " + result);
			for (OrderCustomer orderCustomer : result) {
				System.out.println("OrderCustomer: " + orderCustomer.getCustomer().getName());
			}

			return result;

		} catch (RuntimeException e) {
			throw e;
		} finally {
			session.close();
		}
	}

}
