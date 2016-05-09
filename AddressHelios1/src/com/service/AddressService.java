package com.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.dao.Address;


public class AddressService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		org.hibernate.SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();	
		Session session = sessionFactory.getCurrentSession();
		Address theAddress = new Address();
		theAddress.setCity("Nashport");
		theAddress.setState("Ohio");
		theAddress.setStreet("4995 Watershed Way");
		theAddress.setZipcode("43830");
		addAddress(session,theAddress);
		session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Address> result = session.createQuery("from Address").list();
        System.out.println("result "+ result.size() );
        session.getTransaction().commit();		
	}

	/**
	 * Add the Address object to the Address table
	 * @param session the hibernate session
	 * @param theAddress the Address object
	 */
	public static void addAddress(Session session, Address theAddress) {
		session.beginTransaction();										// begin the transaction before we do anything else
		session.save(theAddress);										// save the Address
		session.getTransaction().commit();								// Commit the transaction
	}


	
	/**
	 * Delete the Address object in the Address table
	 * @param session the hibernate session
	 * @param theAddress the Address object
	 */	
	public static void deleteAddress(Session session,Address theAddress) {
		session.beginTransaction();										// begin the transaction before we delete
		session.delete(theAddress);										// delete the address record
		session.getTransaction().commit();								// commit the transaction			
	}	
	/**
	 * Update the Address object in the Address table
	 * @param session the hibernate session
	 * @param theAddress the Address object
	 */		
	public static void updateAddress(Session session,Address theAddress) {
		session.beginTransaction();										// begin the transaction before we delete
		session.saveOrUpdate(theAddress);								// update the address record
		session.getTransaction().commit();								// commit the transaction			
	}		
	
	/**
	 * Selects a list of Address from the database
	 * @param session the hibernate session
	 * @return a list of Address objects
	 */
	public static List<Address> listAddress(Session session)  {
		List<Address> theList = new ArrayList<Address>();				// Create the array list for the Address
		session.beginTransaction();										// begin the transaction before we create criteria
		Criteria theCriteria = session.createCriteria(Address.class);	// create the criteria class
		theList = theCriteria.list();									// call the list method
		session.getTransaction().commit();								// commit the transaction
		return(theList);												// return the list
	}	
}
