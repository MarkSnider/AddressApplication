package com.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dao.Event;

public class EventServiceTest {

	static org.hibernate.SessionFactory sessionFactory;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sessionFactory = new Configuration().configure().buildSessionFactory();	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddEvents() {
		Event theEvent = new Event();
		theEvent.setDate(new Date());
		theEvent.setTitle("The event title "+Math.random());
		Session session = sessionFactory.getCurrentSession();
		EventService.addEvents(session,theEvent);
		session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Event> result = session.createQuery("from Event").list();
        session.getTransaction().commit();
		assert(result.size()>0);

	}

	@Test
	public void testListEvents() {
		List<Event> theList = EventService.listEvents(sessionFactory.getCurrentSession());
		assert(theList != null);
		assert(theList.size()>0);
	}
	
	
	@Test
	public void testUpdateEvent() {
		List<Event> theList = EventService.listEvents(sessionFactory.getCurrentSession());
		assert(theList != null);
		assert(theList.size()>0);
		Event theEvent = theList.get(0);
		theEvent.setTitle("This is an updated title"+ Math.random());
		EventService.updateEvent(sessionFactory.getCurrentSession(), theEvent);
	}

	@Test
	public void testDeleteEvent() {
		Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();			
		List<Event> theList = session.createQuery("from Event").list();
		assert(theList != null);
		assert(theList.size()>0);
		int beginNum=theList.size();
		Event theEvent = theList.get(0);
		EventService.deleteEvent(sessionFactory.getCurrentSession(), theEvent);
		session = sessionFactory.getCurrentSession();
        session.beginTransaction();		
		List<Event> theList2 = session.createQuery("from Event").list();
		assert(theList2 != null);
		int endNum=theList2.size();
		assert(beginNum > endNum);
	}

}
