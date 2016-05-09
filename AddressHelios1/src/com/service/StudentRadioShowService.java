package com.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;

import com.dao.StudentRadioShow;

public class StudentRadioShowService {
	
	public static void main(String args[]) {
		
		selectStudentRadioShowByStudentIdHQL(210L);
		//selectStudentRadioShowByStudentId(210L);
	}

	/**
	 * Gets all of the studentRadioshow objects for a given studentId using HQL
	 * @return a list of StudentRadioShow objects for a given student Id
	 */
	public static List<StudentRadioShow> selectStudentRadioShowByStudentIdHQL(long StudentId) {
		org.hibernate.SessionFactory sessionFactory;
		Session session;
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();	

		
		// Select the records associated to the student id
		String sql = "select radioShowId, studentId from StudentRadioShow where studentId = :id ";
		
		// Create the query object using the string
		Query theQuery = session.createQuery(sql);
		
		// set the student id
		theQuery.setLong("id", StudentId);
		
		// list the objects
		List<StudentRadioShow> theList = theQuery.list();
		session.getTransaction().commit();
		
		
		
		return(theList);
	}
	/**
	 * Gets all of the studentRadioshow objects for a given studentId
	 * @return a list of StudentRadioShow objects for a given student Id
	 */
	public static List<StudentRadioShow> selectStudentRadioShowByStudentId(long StudentId) {

		org.hibernate.SessionFactory sessionFactory;
		Session session;
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();	
		
		// Creates the criteria object used for selecting the desired student radio show
		Criteria theCriteria = session.createCriteria(StudentRadioShow.class);
		
		// Create a student radio show object using the passed in id
		StudentRadioShow theStudentRadioShow = new StudentRadioShow();
		theStudentRadioShow.setRadioShowId(StudentId);
		
		// Create an Example object using the student Radio show with the passed id
		Example theExample = Example.create(theStudentRadioShow);
		
		// Add the example object to the criteria object
		theCriteria.add(theExample);
		
		// List the records using the criteria object
		List<StudentRadioShow> theList = theCriteria.list();	
		

		// commit the transaction
		session.getTransaction().commit();		
		
		System.out.println("selectStudentRadioShowByStudentId theList.size() " + theList.size());		
		return(theList);
	}
	
	/**
	 * Gets all of the studentRadioshow objects
	 * @return a list of StudentRadioShow objects
	 */
	public List<StudentRadioShow> selectStudentRadioShow() {
		org.hibernate.SessionFactory sessionFactory;
		Session session;
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();	
		List<StudentRadioShow> theList = session.createQuery("from StudentRadioShow").list();

		session.getTransaction().commit();
		return(theList);
	}
	public static void addStudentRadioShow() {
		
	}

}
