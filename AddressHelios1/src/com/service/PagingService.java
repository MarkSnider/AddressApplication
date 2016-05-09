package com.service;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.dao.Student;

public class PagingService {
	
	public static void main(String[] args) {
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();	
		int startPosition=0;									// Start position will move as we page
		int pageSize=10;										// page through ten records at a time
		boolean moreRecords = true;								// set flag that there are more records
		session.beginTransaction();								// start the transaction
		
		// Select all the information from the student table
		Query q = session.createQuery("select studentName, studentType from Student order by studentName");	
		
		q.setFirstResult(startPosition);						// Start that page size at zero
		q.setMaxResults(pageSize);								// We only get ten records each time that list is called
		int pageCnt=0;											// init the page count to zero
		int totalCnt=0;											// count all of the records
		while(moreRecords) {									// loop while there are more records
			List l = q.list();									// list the records
			//System.out.println("pageCnt "+ pageCnt);	
			if (l.size() > 0) {									// if there are records in the list				
				totalCnt=processPage(totalCnt,l);				// process that page of information
				pageCnt++;										// count the page
				startPosition= pageCnt * pageSize;				// calculate the next start position
				q.setFirstResult(startPosition);				// set that start position
			}
			else {
				moreRecords=false;								// if the list is empty then set the flag
			}
			
		}
		System.out.println("totalCnt " + totalCnt);
		session.getTransaction().commit();						// Commit this transaction
	}

	private static int processPage(int totalCnt,List l) {
		Iterator it = l.iterator();						// get the iterator object to move through the list				
		while(it.hasNext() ) {							// second loop while there are records in the iterator
			//String name = (String)it.next();			// get the first record
			Object[] data = (Object[]) it.next();		// Will return an array of objects if more that one field in query
			String name = (String)data[0];
			String type = (String)data[1];
			System.out.println("Student name " + name + " type " + type);							
			totalCnt++;									// count all of the records
		}
		return(totalCnt);
	}
}
