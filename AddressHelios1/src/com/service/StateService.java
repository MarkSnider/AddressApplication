package com.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.State;
import com.utilities.LoadStates;

public class StateService {

	public static void main(String[] args) {
		Transaction transaction = null;
		State theState = new State();
		List<State> theStateList = new ArrayList<State>();
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();	
		transaction = session.beginTransaction();
		theStateList = session.createQuery("from State").list();
		System.err.println(" theStateList.size() " + theStateList.size());
		transaction.commit();
	}
	
	/**
	 * Loads all of the states into the database
	 */
	public static void addAllStates() {
		Transaction transaction = null;
		State theState = new State();
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	

		LoadStates theLoadStates = new LoadStates(LoadStates.STATEFILE);		// Construct the state loading object
		theLoadStates.processFile();											// Read the states out of the file
		List<State> theStateList = new ArrayList<State>();						// Construct a list for the state object
		List<String> theStates = new ArrayList<String>();						// Construct a list of basic state strings
		List<String> theAbbList = theLoadStates.getStateAbbrevList();			// Get the state abbreviation list
		

		for (String data: theAbbList) {											// Loop through the list of states
			Session session = sessionFactory.getCurrentSession();	
			transaction = session.beginTransaction();
			theState.setAbbrev(data);
			session.save(theState);
			transaction.commit();			
		}		
	}
	
	/**
	 * Get a list of all of the state objects in the database
	 * @return a list of state objects
	 */
	public static List<State> selectAllStates() {
		
		Transaction transaction = null;
		State theState = new State();
		List<State> theStateList = new ArrayList<State>();
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();	
		transaction = session.beginTransaction();
		theStateList = session.createQuery("from State").list();
		transaction.commit();	
		return(theStateList);
	}
	
}
