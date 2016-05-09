package com.service;


import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dao.RadioShow;
import com.dao.ShowDetail;
import com.dao.Student;
import com.dao.StudentRadioShow;

public class RadioShowServiceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
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
	public void testSelectRadioShowByShowIdHQL() {
		List<RadioShow> radioShowList = RadioShowService.selectRadioShows();
		assert(radioShowList != null);
		assert(radioShowList.size() > 0);
		RadioShow theRadioShow = radioShowList.get(0);
		radioShowList = RadioShowService.selectRadioShowByShowIdHQL(theRadioShow.getShowId());
		assert(radioShowList != null);
		assert(radioShowList.size() > 0);		
	}
	
	@Test
	public void testSelectRadioShows() {
		List<RadioShow> radioShowList = RadioShowService.selectRadioShows();
		assert(radioShowList != null);
	}	
	
	/**
	 * Add the RadioShow object to the RadioShow table also saves the RadioShow
	 * @param theRadioShow the RadioShow object
	 */
	@Test
	public void testSaveRadioShow()  { 
    	RadioShow theRadioShow = new RadioShow();
    	theRadioShow.setArchiveListens(100);
    	theRadioShow.setDescription("test description");
    	theRadioShow.setTitle("test title");
    	theRadioShow.setLiveListens(100);
    	theRadioShow.setListedDate(new Date());
    	
        RadioShowService.saveRadioShow(theRadioShow);
	 
	}
	

	
	@Test
	public void testSaveRadioShowWithExistingStudent() {
//		org.hibernate.SessionFactory sessionFactory;
//		sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();	
//		session.beginTransaction();		
//		List<StudentRadioShow> theList = session.createQuery("from StudentRadioShow").list();
//		StudentRadioShow theStudentRadioShow = theList.get(0);
//		System.out.println(" theStudentRadioShow.getRadioShowId() " + theStudentRadioShow.getRadioShowId()
//				+ " theStudentRadioShow.getStudentId() " + theStudentRadioShow.getStudentId());
//		
//        RadioShow show = new RadioShow();
//        
//        show.setTitle("Joe Blow 2");
//        ShowDetail showDetail = new ShowDetail();
//        showDetail.setDescription("Joe Blows second show " + new Date().toString());
//        showDetail.setListedDate(new Date());
//        showDetail.setArchiveListens(234);
//        showDetail.setLiveListens(34);
//        show.setShowDetail(showDetail);
//        showDetail.setRadioShow(show);
//        RadioShowService.saveRadioShow(show);		
//		session.getTransaction().commit();
	}
	
	public static void main(String[] args ) {
		org.hibernate.SessionFactory sessionFactory;
		sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();	
		session.beginTransaction();		
		List<StudentRadioShow> theList = session.createQuery("from StudentRadioShow").list();
		StudentRadioShow theStudentRadioShow = theList.get(0);
		System.out.println(" theStudentRadioShow.getRadioShowId() " + theStudentRadioShow.getRadioShowId()
				+ " theStudentRadioShow.getStudentId() " + theStudentRadioShow.getStudentId());
		session.getTransaction().commit();		
	}
}
