package com.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;

import com.dao.Address;
import com.dao.CurrentSort;
import com.dao.Email;
import com.dao.Event;
import com.dao.Phone;
import com.dao.Student;
import com.dao.Web;
import com.constants.Constants;
import com.dao.StudentEmail;
import com.dao.StudentPhone;

public class HQLExamples {
	
	public static void main(String[] args) {
//		listWebIdsFromStudentWeb();
//		selectStreetFromAddressByState();
//		selectStreetFromAddressByStateInPutParameters();
//		selectMultiFromAddressByStateInPutParameters();
		selectMultiFromAddressByStateInPutParametersWithLike();
	}
	
	/**
	 * Create an HQL select to get all of the webids for specific studentid
	 * @param studentId the id associated to the student
	 * @return a list of webIds
	 */
	public static void  listWebIdsFromStudentWeb() {
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();		
		String hql = "Select webid from StudentWeb where studentId = :id";
		session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setLong("id", 120);
		List list = query.list();
		for (int i=0; i < list.size(); i++) {
			Long theId = (Long)list.get(i);
			System.err.println("theId " + theId);
			
		}
		session.getTransaction().commit();	
		
	}	
	/**
	 * Create an HQL select to get all of the webids for specific studentid
	 * @param studentId the id associated to the student
	 * @return a list of webIds
	 */
	public static void  selectStreetFromAddressByState() {
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();		
		//String hql = "Select street from Address where state = 'OH'";   	// Works
		//String hql = "Select street from Address as A where state = 'OH'";	// Works
		 String hql = "Select street from Address as A where A.state = 'OH'";   // Works
		 
		session.beginTransaction();
		Query query = session.createQuery(hql);

		List list = query.list();
		for (int i=0; i < list.size(); i++) {
			String theStreet = (String)list.get(i);
			System.err.println("theStreet " + theStreet);
			
		}
		session.getTransaction().commit();	
		
	}	

	/**
	 * Create an HQL select to get all of the webids for specific studentid
	 * @param studentId the id associated to the student
	 * @return a list of webIds
	 */
	public static void  selectStreetFromAddressByStateInPutParameters() {
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();		
		//String hql = "Select street from Address where state = 'OH'";   	// Works
		//String hql = "Select street from Address as A where state = 'OH'";	// Works
		String inPutState = "OH";
		String hql = "Select street from Address as A where A.state = :inPutState";   // Works
		 
		session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setString("inPutState", inPutState);
		
		List list = query.list();
		for (int i=0; i < list.size(); i++) {
			String theStreet = (String)list.get(i);
			System.err.println("theStreet " + theStreet);
			
		}
		session.getTransaction().commit();	
		
	}
	
	/**
	 * Create an HQL select to get all of the webids for specific studentid
	 * @param studentId the id associated to the student
	 * @return a list of webIds
	 */
	public static void  selectMultiFromAddressByStateInPutParameters() {
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();		
		//String hql = "Select street from Address where state = 'OH'";   	// Works
		//String hql = "Select street from Address as A where state = 'OH'";	// Works
		String inPutState = "OH";
		String hql = "Select street,zipcode from Address as A where A.state = :inPutState";   // Works
		 
		session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setString("inPutState", inPutState);
		
		List list = query.list();
		for (int i=0; i < list.size(); i++) {
			Object[] theData = (Object[])list.get(i);
			System.err.println("theStreet " + theData[0]);
			System.err.println("zipcode " + theData[1]);
		}
		session.getTransaction().commit();	
		
	}
	
	public static void  selectMultiFromAddressByStateInPutParametersWithLike() {
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();		
		//String hql = "Select street from Address where state = 'OH'";   	// Works
		//String hql = "Select street from Address as A where state = 'OH'";	// Works
		String inPutState = "O";
		//(description LIKE '%' || :inPutState || '%')
		String hql = "Select street,zipcode from Address as A where (A.state LIKE '%' || :inPutState || '%')";   // Works
		 
		session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setString("inPutState", inPutState);
		
		List list = query.list();
		for (int i=0; i < list.size(); i++) {
			Object[] theData = (Object[])list.get(i);
			System.err.println("theStreet " + theData[0]);
			System.err.println("zipcode " + theData[1]);
		}
		session.getTransaction().commit();	
		
	}		
}
