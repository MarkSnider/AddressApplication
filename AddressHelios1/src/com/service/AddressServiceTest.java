package com.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dao.Address;


/**
 * @author User
 *
 */
public class AddressServiceTest {
	static org.hibernate.SessionFactory sessionFactory;
	static Address theAddress;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sessionFactory = new Configuration().configure().buildSessionFactory();	
		theAddress = new Address();
		theAddress.setCity("Columbus " + Math.random());
		theAddress.setState("Ohio");
		theAddress.setStreet("3600 Corporate Dr " + Math.random());
		theAddress.setZipcode("43231");		
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
	public void testAddAddress() {
		Session session = sessionFactory.getCurrentSession();
		AddressService.addAddress(session,theAddress);
		session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Address> result = session.createQuery("from Address").list();
        session.getTransaction().commit();
		assert(result.size()>0);		
	}
	
	@Test
	public void testDeleteAddress() {
		// Add Address first
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		theAddress.setStreet("3600 Corporate Dr " + Math.random());
		session.save(theAddress);
		session.getTransaction().commit();
		
		// Select the address get the count before the delete
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Address> list = session.createQuery("from Address").list();
		session.getTransaction().commit();
		assert(list != null);
		assert(list.size() > 0);
		int numBefore=list.size();
		
		// Do the delete
		Address theAddress = list.get(0);
		AddressService.deleteAddress(sessionFactory.getCurrentSession(), theAddress);
		
		//Select the count again and make sure its less than before
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		list = session.createQuery("from Address").list();
		session.getTransaction().commit();
		int numAfter=list.size();
		assert(numBefore > numAfter);
		
	}

//	@Test
//	public void testUpdateAddress() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testListAddress() {
		List<Address> theList = AddressService.listAddress(sessionFactory.getCurrentSession());
		assert(theList != null);
		assert(theList.size()>0);											// return the list
	}

}
