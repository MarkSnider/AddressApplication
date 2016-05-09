package com.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.transform.Transformers;

import com.dao.Contact251;
import com.dao.Student;

public class Contact251Service {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String sql="select linenumber, info from Contact251 where linenumber < 175 and linenumber > 165";
		List<Contact251> theList = Contact251Service.selectByLineNumber(sql);
	}
	
	/**
	 * Add or save the Contact251 object into the associated table of the same name.
	 * @param session the hibernate session
	 * @param theStudent the Student object
	 */
	public static void saveContact251(Contact251 theContact251) throws HibernateException { 
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();		
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();	// Start the transaction
			session.saveOrUpdate(theContact251);		// Saves the line in the file into database								
			transaction.commit();						// Commits the transaction
		} catch (HibernateException e) {
			transaction.rollback();						// roll back the transaction
			e.printStackTrace();
			throw new HibernateException(e);
		} 
	 
	}
	
	/**
	 * Do query only for information that is like the text that is passed.
	 * We will exclude the line numbers and keywords from the query so that our 
	 * query will return results
	 * @param text look for lines that contain text like "Sirius" or whatever data is passed in
	 * @return a List of Contact251 objects
	 */
	public static List<Contact251> selectInfoLike(String text) {
		// Create the list to return
		List<Contact251> theList = new ArrayList<Contact251>();
		
		// Create a contact 251 object
		Contact251 theContact = new Contact251();
		
		// set the line information
		theContact.setInfo(text);
		
		
		//Create the example object
		Example theExample = Example.create(theContact);
		
		// Make sure to exclude the properties we DO NOT want in the query where clause
		// otherwise our query will not work...
		theExample.excludeProperty("keyNumber1");
		theExample.excludeProperty("keyNumber2");
		theExample.excludeProperty("lineNumber");
		
		// set the match mode anywhere
		theExample.enableLike(MatchMode.ANYWHERE);
		
		// Create session information
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();		
		Transaction transaction = null;
		
		transaction = session.beginTransaction();				// Start the transaction
		
		// Create a criteria object using the Contact251 class
		Criteria theCriteria = session.createCriteria(Contact251.class);

		// Add the example to the criteria
		theCriteria.add(theExample);
		
		// Get the list of information
		theList = theCriteria.list();
		
		session.getTransaction().commit();								// commit the transaction						// Commits the transaction
		
		//return the list of Contact Information
		return(theList);
		
	}

	/**
	 * Do a query which gets all of the information from the entire contact
	 * @return a List of Contact251 objects that is all of the contact information
	 */
	public static List<Contact251> selectAll() {
		// Create the list to return
		List<Contact251> theList = new ArrayList<Contact251>();

		// Create session information
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();		
		Transaction transaction = null;
		
		// Start the transaction
		transaction = session.beginTransaction();				
		
		// Create the query for getting all of information
		Query q = session.createQuery("from Contact251");
		
		// Get the list of information
		theList = q.list();
		
		// Commits the transaction
		session.getTransaction().commit();						
		
		//return the list of Contact Information
		return(theList);
		
	}	

	/**
	 * Does a small query based on the line numbers passed an one example might be
	 * select * from contact251 where linenumber < 175 and linenumber > 165
	 * @param sql select based on line numbers
	 * @return a list of contact 251 objects
	 */
	public static List<Contact251> selectByLineNumber(String sql) {
		// Create the list to return
		List<Contact251> theList = new ArrayList<Contact251>();

		// Create session information
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();		
		Transaction transaction = null;
		
		// Start the transaction
		transaction = session.beginTransaction();				
		
		// Create the query for getting all of information
		Query q = session.createSQLQuery(sql)
		.addScalar("lineNumber", Hibernate.LONG)		// tell Hibernate what values 
		.addScalar("info", Hibernate.STRING)		// that are returned during the query
		.setResultTransformer(Transformers.aliasToBean(Contact251.class));
		
		// Get the list of information
		theList = q.list();
		
		// Commits the transaction
		session.getTransaction().commit();						
		
		//return the list of Contact Information
		return(theList);
		
	}		
}
