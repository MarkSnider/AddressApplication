package com.service;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;

import com.dao.Address;
import com.dao.Phone;
import com.dao.Student;

public class PhoneService {
	
	public static void main(String[] args) {
		org.hibernate.SessionFactory  sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			Set<Phone> phoneNumbers = new HashSet<Phone>();
			phoneNumbers.add(new Phone("house","32354353"));
			phoneNumbers.add(new Phone("mobile","9889343423"));
			Address address = new Address("OMR Road", "Chennai", "TN", "600097", "USA");
			Student student = new Student("Eswar",  address, phoneNumbers);
			session.save(student);
			
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} 
		
	}
	
/*	public static void main(String[] args) {
		org.hibernate.SessionFactory  sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			Set<Phone> phoneNumbers = new HashSet<Phone>();
			phoneNumbers.add(new Phone("house","32354353"));
			phoneNumbers.add(new Phone("mobile","9889343423"));
			Address address = new Address("OMR Road", "Chennai", "TN", "600097");
			Student student = new Student("Eswar",  address,phoneNumbers);
			session.save(student);
			
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} 
				
	}*/
	

}
