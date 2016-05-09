package com.service;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.Transformers;

import com.dao.RadioShow;
import com.dao.ShowDetail;
import com.dao.RadioShow;
import com.dao.StudentRadioShow;
import com.dao.Web;

public class RadioShowService {
	
    public static void main( String[] args )
    {
    	//selectRadioShowByShowId(new Long(156));

    	// Delete the radio show
    	deleteRadioShow(new Long(156));
    }
	public static RadioShow selectRadioShowByShowId(long showId) {
		RadioShow theRadioShow = new RadioShow();					// Create the radio show object
		org.hibernate.SessionFactory sessionFactory;				// Define the session factory
		sessionFactory = HibernateUtil.getSessionFactory();			// Get the session factory
		Session session = sessionFactory.getCurrentSession();		// Create the session
		session.beginTransaction();									// Start the transaction
		
		// Define the SQL select we cannot seem to map text to string for description
		String sql = "select rs.SHOW_ID as showId, rs.TITLE as title, " +
					" rs.ARCHIVE_LISTENS as archiveListens, rs.LIVE_LISTENS as liveListens, " +
					" rs.LISTED_DATE as listedDate " +
					" from radioshow rs" +
					" where SHOW_ID = :showId ";	
		
		// Add the scalars and the result set transformer
		List theList = session.createSQLQuery(sql)
		.addScalar("showId")	
		.addScalar("title")
		.addScalar("archiveListens")
		.addScalar("liveListens")
		.addScalar("listedDate")
		.setParameter("showId", showId)
		.setResultTransformer( Transformers.aliasToBean(RadioShow.class)).list();	
		
		System.out.println(" theList.size() " + theList.size());
		// Get the radio show object
		if (theList != null && theList.size() > 0) {
			theRadioShow = (RadioShow)theList.get(0);
		}
		
		
		session.getTransaction().commit();
		return(theRadioShow);
	} 
    /**
     * Delete a record in the radio show table using HQL delete with showId
     * @param theRadioShow - the radio show object
     */
    public static void deleteRadioShow(long theRadioShow) {
		org.hibernate.SessionFactory sessionFactory;
		sessionFactory = HibernateUtil.getSessionFactory();		// Create the session factory
		Session session = sessionFactory.getCurrentSession();	// Create the session
		session.beginTransaction();								// Begin a transaction

		// Define the query object and the HQL
		Query  query = session.createQuery("delete RadioShow where showId = :showId");
		query.setParameter("showId", theRadioShow);				// Set our parameter

		int result = query.executeUpdate();
		session.getTransaction().commit();
		System.out.println(" result " + result);
    }
	/**
	 * Gets all of the Radioshow objects using HQL descending by showdate.
	 * @return a list of RadioShow objects sorted by radio show 
	 */
	public static List<RadioShow> selectRadioShowDes() {
		org.hibernate.SessionFactory sessionFactory;
		Session session;
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();	

		// Select the records associated to the student id
		String sql = "select showId, title, description, archiveListens, liveListens, listedDate from RadioShow as R order by R.archiveListens desc";
		
		// Create the query object using the string
		Query theQuery = session.createQuery("from RadioShow order by archiveListens desc");
		
		// list the objects
		List<RadioShow> theList = theQuery.list();
		
		session.getTransaction().commit();
	
		return(theList);
	}
	

	/**
	 * Gets all of the Radioshow objects for a given studentId using HQL.
	 * @return a list of RadioShow objects for a given student Id
	 */
	public static List<RadioShow> selectRadioShowByShowIdHQL(long showId) {
		org.hibernate.SessionFactory sessionFactory;
		Session session;
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();	

		// Select the records associated to the student id
		String sql = "select title, description, archiveListens, liveListens, listedDate from RadioShow where showId = :id ";
		
		// Create the query object using the string
		Query theQuery = session.createQuery(sql);
		
		// set the student id
		theQuery.setLong("id", showId);
		
		// list the objects
		List<RadioShow> theList = theQuery.list();
		session.getTransaction().commit();
	
		return(theList);
	}
    /**
     * Get all of the radio shows in the database regardless of the student id
     * @return a list of radio show objects
     * @throws HibernateException
     */
    public static List<RadioShow> selectRadioShows() throws HibernateException {
		org.hibernate.SessionFactory sessionFactory;
		Session session;
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();			
		
		// Create the query object using the string
		Query theQuery = session.createQuery("from RadioShow");  
		// Get the list of radio show and show detail information
		List<RadioShow> radioShowList = theQuery.list();
		session.getTransaction().commit();

		return(radioShowList);
    }
	/**
	 * Add the RadioShow object to the RadioShow table also saves the RadioShow
	 * @param session the hibernate session
	 * @param theRadioShow the RadioShow object
	 */
	public static void saveRadioShow(RadioShow theRadioShow) throws HibernateException { 
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();		
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();	// Start the transaction
			session.saveOrUpdate(theRadioShow);			// Saves the RadioShow, Associated show detail								
			transaction.commit();						// Commits the transaction
		} catch (HibernateException e) {
			transaction.rollback();						// roll back the transaction
			e.printStackTrace();
			throw new HibernateException(e);
		} 
	 
	}
    public void keep() {
		// Define the sql to get the rest of the radio show information that we need 
		String sql = "select title, description, archive_listens, live_listens, listed_date " +
		" from radioshow, show_detail " +
		" where radioshow.show_id = show_detail.show_id " + 
		" and radioshow.show_id = ";
		
		sql = " select title from radioshow";
		
		//sql += "57";
		org.hibernate.SessionFactory sessionFactory;
		Session session;
		sessionFactory = HibernateUtil.getSessionFactory();
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();			
		
		// Create the query object using the string
		Query theQuery = session.createSQLQuery(sql);      
        
		// Get the list of radio show and show detail information
		List radioShowList = theQuery.list();	
		System.out.println(" radioShowList.size() " +  radioShowList.size());
//		for (int i=0; i < radioShowList.size(); i++) {
//			
//			Object[] o = (Object[])radioShowList.get(i);
//			
//			String title = (String)o[0];
//			
//			//desiredList.add(theWeb);
//		}	  	
    }	
}
