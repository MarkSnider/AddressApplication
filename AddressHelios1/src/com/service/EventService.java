package com.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.dao.Event;

public class EventService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		org.hibernate.SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();	
		Session session = sessionFactory.getCurrentSession();
/*		Event theEvent = new Event();
		theEvent.setDate(new Date());
		theEvent.setTitle("The event title");
		addEvents(session,theEvent);*/
		
        session.beginTransaction();
        List<Event> result = session.createQuery("from Event").list();
        System.out.println("result "+ result.size() );
        session.getTransaction().commit();		
	}
	
	/**
	 * Add the event object to the event table
	 * @param session the hibernate session
	 * @param theEvent the event object
	 */
	public static void addEvents(Session session, Event theEvent) {
		session.beginTransaction();										// begin the transaction before we do anything else
		session.save(theEvent);											// save the event
		session.getTransaction().commit();								// Commit the transaction
	}
	
	/**
	 * Selects a list of events from the database
	 * @param session the hibernate session
	 * @return a list of event objects
	 */
	public static List<Event> listEvents(Session session)  {
		List<Event> theList = new ArrayList<Event>();					// Create the array list for the events
		session.beginTransaction();										// begin the transaction before we create criteria
		Criteria theCriteria = session.createCriteria(Event.class);		// create the criteria class
		theList = theCriteria.list();									// call the list method
		session.getTransaction().commit();								// commit the transaction
		return(theList);												// return the list
	}
	/**
	 * Delete the event object to the event table
	 * @param session the hibernate session
	 * @param theEvent the event object
	 */
	public static void deleteEvent(Session session,Event theEvent) {
		session.beginTransaction();										// begin the transaction before we do anything else
		session.delete(theEvent);										// save the event
		session.getTransaction().commit();								// Commit the transaction	
	}	
	/**
	 * Update the event in the event table
	 * @param session the hibernate session
	 * @param theEvent the event object
	 */	
	public static void updateEvent(Session session,Event theEvent) {
		session.beginTransaction();										// begin the transaction before we do anything else
		session.saveOrUpdate(theEvent);									// update the event
		session.getTransaction().commit();								// Commit the transaction		
	}		
	
}
