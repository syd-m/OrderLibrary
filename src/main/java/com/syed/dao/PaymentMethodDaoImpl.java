package com.syed.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.syed.entity.PaymentMethod;
import com.syed.util.HibernateUtil;

public class PaymentMethodDaoImpl implements PaymentMethodDao {

	SessionFactory sessionFactory;

	public PaymentMethodDaoImpl() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<PaymentMethod> findPaymentMehods(Long custId) {
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		List<PaymentMethod> customerPaymentMethods = new ArrayList<>();
		try {
			Query query = session.createQuery("FROM PaymentMethod where customer_id=:customer_id");
			query.setParameter("customer_id", custId);
			customerPaymentMethods = query.list();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return customerPaymentMethods;
	}

	@Override
	public int deletePaymentMethods(Long custId) {
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("delete PaymentMethod where customer_id = :customer_id");
		query.setParameter("customer_id", custId);
		int updatedRows = query.executeUpdate();
		return updatedRows;
	}

	@Override
	public PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod) {
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.saveOrUpdate(paymentMethod);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public void insertPaymentMethods(List<PaymentMethod> paymentMethods) {
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			for (PaymentMethod paymentMethod : paymentMethods) {
				session.save(paymentMethod);
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			session.close();
		}
	}

}
