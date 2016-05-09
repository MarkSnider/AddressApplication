package com.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.Country;
import com.dao.State;
import com.utilities.LoadCountries;
import com.utilities.LoadStates;

public class CountryService {
	
	public static void main(String[] args) {
		Transaction transaction = null;
		Country theState = new Country();
		List<Country> theCountryList = new ArrayList<Country>();
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();	
		transaction = session.beginTransaction();
		theCountryList = session.createQuery("from Country").list();
		System.err.println(" theCountryList() " + theCountryList.size());
		transaction.commit();		
	}

	public static void addCountries() {
		Transaction transaction = null;
		Country theCountry = new Country();
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	

		LoadCountries theLoadCountries = new LoadCountries(LoadCountries.COUNTRIESFILE);		// Construct the state loading object
		theLoadCountries.processFile();											// Read the states out of the file
		List<Country> theStateList = new ArrayList<Country>();						// Construct a list for the state object
		List<String> theStates = new ArrayList<String>();						// Construct a list of basic state strings
		List<String> theCList = theLoadCountries.getCountriesList();			// Get the state abbreviation list
		

		for (String data: theCList) {											// Loop through the list of states
			Session session = sessionFactory.getCurrentSession();	
			transaction = session.beginTransaction();
			theCountry.setCountryName(data);
			session.save(theCountry);
			transaction.commit();			
		}			
	}
	/**
	 * Get a list of all of the Country objects in the database
	 * @return a list of Country objects
	 */
	public static List<Country> selectAllCountries() {
		
		Transaction transaction = null;
		Country theCountry = new Country();
		List<Country> theCountryList = new ArrayList<Country>();
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();	
		transaction = session.beginTransaction();
		theCountryList = session.createQuery("from Country").list();
		transaction.commit();	
		return(theCountryList);
	}	
}
