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
import com.dao.PleiadeanMission;

public class PleiadeanMissionService {

	public static void main(String[] args) {

//		PleiadeanMission theData = new PleiadeanMission();
//		theData.setLineNumber(1L);
//		theData.setInfo("This is some test data.");
//		// save the record
//		PleiadeanMissionService.savePleiadeanMission(theData);
//		List<PleiadeanMission> theList = selectInfoLike("Pelegon");
//		System.out.println("theList.size() " + theList.size());
		
		String sql="select linenumber, info from pleiadean_mission where linenumber < 175 and linenumber > 165";
		List<PleiadeanMission> theList = selectByLineNumber(sql);	
		System.out.println("theList.size() " + theList.size());		
	}	
	/**
	 * Add or save the PleiadeanMission object into the associated table of the same name.
	 * @param session the hibernate session
	 * @param theStudent the Student object
	 */
	public static void savePleiadeanMission(PleiadeanMission thePleiadeanMission) throws HibernateException { 
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();		
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();		// Start the transaction
			session.saveOrUpdate(thePleiadeanMission);		// Saves the line in the file into database								
			transaction.commit();							// Commits the transaction
		} catch (HibernateException e) {
			transaction.rollback();							// roll back the transaction
			e.printStackTrace();
			throw new HibernateException(e);
		} 
	 
	}	
	
	/**
	 * Do a query which gets all of the information from the entire Pleiadean Mission table.
	 * @return a List of Pleiadean Mission object
	 */
	public static List<PleiadeanMission> selectAll() {
		// Create the list to return
		List<PleiadeanMission> theList = new ArrayList<PleiadeanMission>();

		// Create session information
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();		
		Transaction transaction = null;
		
		// Start the transaction
		transaction = session.beginTransaction();				
		
		// Create the query for getting all of information
		Query q = session.createQuery("from PleiadeanMission");
		
		// Get the list of information
		theList = q.list();
		
		// Commits the transaction
		session.getTransaction().commit();						
		
		//return the list of Contact Information
		return(theList);
		
	}	
	
	/**
	 * Do query only for information that is like the text that is passed.
	 * We will exclude the line numbers and keywords from the query so that our 
	 * query will return results
	 * @param text look for lines that contain text like "Sirius" or whatever data is passed in
	 * @return a List of PleiadeanMission objects
	 */
	public static List<PleiadeanMission> selectInfoLike(String text) {
		// Create the list to return
		List<PleiadeanMission> theList = new ArrayList<PleiadeanMission>();
		
		// Create a contact PleiadeanMission object
		PleiadeanMission theData = new PleiadeanMission();
		
		// set the line information
		theData.setInfo(text);
		
		
		//Create the example object
		Example theExample = Example.create(theData);
		
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
		
		// Create a criteria object using the PleiadeanMission class
		Criteria theCriteria = session.createCriteria(PleiadeanMission.class);

		// Add the example to the criteria
		theCriteria.add(theExample);
		
		// Get the list of information
		theList = theCriteria.list();
		
		session.getTransaction().commit();								// commit the transaction						// Commits the transaction
		
		//return the list of Contact Information
		return(theList);
		
	}
	
	/**
	 * Does a small query based on the line numbers passed an one example might be
	 * select * from pleiadean_mission where linenumber < 175 and linenumber > 165
	 * @param sql select based on line numbers
	 * @return a list of contact 251 objects
	 */
	public static List<PleiadeanMission> selectByLineNumber(String sql) {
		// Create the list to return
		List<PleiadeanMission> theList = new ArrayList<PleiadeanMission>();

		// Create session information
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();		
		Transaction transaction = null;
		
		// Start the transaction
		transaction = session.beginTransaction();				
		
		// Create the query for getting all of information
		Query q = session.createSQLQuery(sql)
		.addScalar("lineNumber", Hibernate.LONG)		// tell Hibernate what values 
		.addScalar("info", Hibernate.STRING)			// that are returned during the query
		.setResultTransformer(Transformers.aliasToBean(PleiadeanMission.class));
		
		// Get the list of information
		theList = q.list();
		
		// Commits the transaction
		session.getTransaction().commit();						
		
		//return the list of Contact Information
		return(theList);
		
	}		
}
