package com.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dao.State;
import com.dao.StudentType;
import com.utilities.LoadStates;
import com.utilities.LoadStudentTypes;

/**
 * Handles all of the database interaction with the student types: Family, friend, radio show, stamper
 * @author User
 *
 */
public class StudentTypeService {
	
	/** 
	 * Read the studentTypes from the Student_Type table
	 * @return a list of StudentType objects
	 */
	public static List<StudentType> selectStudentTypes() {
		List<StudentType> theList = new ArrayList<StudentType>();
		Transaction transaction = null;
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();	
		transaction = session.beginTransaction();
		theList = session.createQuery("from StudentType").list();
		
		return(theList);
		
	}
	/**
	 * Read the student types from the text file and load  them into the database
	 */
	public static void addAllStudentTypes() {
		Transaction transaction = null;
		StudentType theStudentType = new StudentType();
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	

		LoadStudentTypes theStudentTypes = new LoadStudentTypes(LoadStudentTypes.STUDENTTYPES);		// Construct the student type loading object
		theStudentTypes.processFile();																// Read the student types out of the file
		List<StudentType> theStudentTypeList = new ArrayList<StudentType>();						// Construct a list for the student type object
		List<String> StudentTypes = theStudentTypes.getStudentTypeList();
		

		for (String data: StudentTypes) {											// Loop through the list of student types
			Session session = sessionFactory.getCurrentSession();	
			transaction = session.beginTransaction();
			theStudentType.setStudentType(data);
			session.save(theStudentType);
			transaction.commit();			
		}			
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<StudentType> theList = new ArrayList<StudentType>();
		Transaction transaction = null;
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();	
		transaction = session.beginTransaction();
		theList = session.createQuery("from StudentType").list();
		System.out.println("theList.size() " + theList.size());

	}

}
