package com.service;

import com.dao.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.transform.Transformers;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dao.Address;
import com.dao.CurrentSort;
import com.dao.Email;
import com.dao.Event;
import com.dao.Partner;
import com.dao.Phone;
import com.dao.RadioShow;
import com.dao.ShowDetail;
import com.dao.Student;
import com.dao.StudentWeb;
import com.dao.Web;
import com.forms.EditForm;
import com.constants.Constants;
import com.constants.SqlQuery;

public class StudentServiceTest {
	
	
	public static void main(String[] args) {
		// ****** END ADD *****
		
		
	}
	
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
	public void testBuildPhoneNumbers() {
		
        String[] phones = { "777-1111", "777-2222", "777-3333" };
        String[] phonesIds = { "1", "2", "3" };		
	}
	
	@Test
	public void testBuildEmailAddresses() {
//		String[] emailIds = { "1", "2", "3" };
//		String[] emails = { "msnider1.com", "msnider2.com", "msnider3.com" };
//		String[] emailTypes = { "Work", "Home", "Home2" };
//		
//		// Get the web addresses from the 2 arrays
//		Set<Email> emailAddresses = StudentService.buildEmailAddresses(emailIds, emails,emailTypes);
//		Assert.assertNotNull(emailAddresses);
//		Assert.assertTrue(emailAddresses.size() > 0);
//		
//		List<Email> list = new ArrayList<Email>(emailAddresses);
//		Assert.assertNotNull(list);
//		
//		Email data = list.get(0);
//		Assert.assertNotNull(data);
//		String theAddress = data.getEmailAddr();
//		String type = data.getEmailType();
//		String id = new Long(data.getEmailId()).toString();
//		Assert.assertTrue(theAddress != null);
//		Assert.assertTrue(theAddress.equals("msnider1.com") || theAddress.equals("msnider2.com") || theAddress.equals("msnider3.com"));
//		Assert.assertTrue(type.equals("Work") || type.equals("Home2") || type.equals("Home2"));
//		Assert.assertTrue(id.equals("1") || id.equals("2") || id.equals("3"));
	}	
	
	@Test
	public void testBuildWebAddresses() {
//		String[] webIds = { "1", "2", "3" };
//		String[] webUrls = { "abc.com", "def.com", "hij.com" };
//		
//		// Get the web addresses from the 2 arrays
//		Set<Web> webAddresses = StudentService.buildWebAddresses(webIds, webUrls);
//		Assert.assertNotNull(webAddresses);
//		Assert.assertTrue(webAddresses.size() > 0);
//		
//		List<Web> list = new ArrayList<Web>(webAddresses);
//		Assert.assertNotNull(list);
//		
//		Web data = list.get(0);
//		Assert.assertNotNull(data);
//		String theUrl = data.getUrl();
//		Assert.assertTrue(theUrl != null);
//		Assert.assertTrue(theUrl.equals("abc.com") || theUrl.equals("def.com") || theUrl.equals("hij.com"));
		
	}
	
	@Test
	public void testUpdateTheStudent() {

    	Set<Phone> phoneNumbers = new HashSet<Phone>();					// Create the set of phone numbers    	
    	Phone thePhone = new Phone();									// Create the phone object
    	thePhone.setPhoneNumber("111111111");							// get the home phone from the form
    	thePhone.setPhoneType("Home");									// set the phone type
    	thePhone.setPhoneId(new Long(1851));
    	phoneNumbers.add(thePhone);	
    	
    	thePhone = new Phone();											// Create the phone object
    	thePhone.setPhoneNumber("020304050");							// get the cell phone from the form
    	thePhone.setPhoneType("Cell");									// set the phone type   
    	thePhone.setPhoneId(new Long(1852));
    	phoneNumbers.add(thePhone);										// Add the cell phone number
    	
    	
    	Set<Email> emailAddresses = new HashSet<Email>();				// Create the set of Email address
    	Email Email = new Email();										// Create the Email object
    	Email.setEmailAddr("EMAIL_TEST@SOMEWHERE.COM");					// get the home Email from the form
    	Email.setEmailType("Home");									    // set the Email type
    	Email.setEmailId(new Long(1864));
    	
    	emailAddresses.add(Email);										// Add the first Email address
    	Email = new Email();											// Create the phone object
    	Email.setEmailAddr("EMAIL998@SOMEWHERE.COM");					// get the cell phone from the form
    	Email.setEmailType("Work");	
    	Email.setEmailId(new Long(1865));
    	emailAddresses.add(Email);										// Add the second email address  	
    	
    	Set<Web> webAddresses = new HashSet<Web>();						// Create the set of web address
    	Web theWeb = new Web();											// We support three web sites per student
    	theWeb.setWebid(new Long(2316));
    	theWeb.setUrl("WWW.SOMEPLACEX.COM");					
    	webAddresses.add(theWeb);
    	
    	theWeb = new Web();
    	theWeb.setWebid(new Long(2317));
    	theWeb.setUrl("WWW.SOMEPLACEY.COM");					
    	webAddresses.add(theWeb); 
    	
    	theWeb = new Web();
    	theWeb.setWebid(new Long(2318));
    	theWeb.setUrl("WWW.SOMEPLACEZ.COM");					
    	webAddresses.add(theWeb);  	
    	
    	
    	// Create the address object
		Address address = new Address("1234 SOMESTREET", "CITY1", "OH", "43830", "USA");
		long addressId=964L;
		address.setAddressId(addressId);
		
		//Create the student object and add the addresses, phone numbers, email addresses and radio show information
		Student student1 = new Student("JOHN TEST",  address, phoneNumbers,emailAddresses,webAddresses, new HashSet<RadioShow>(0));	
		
		student1.setStudentInfo("INFO");				// Set that new basic information field
		student1.setStudentType("RADIO_SHOW");			// Get the student type information out of the form.
		student1.setStudentId(new Long(306));

    	StudentService.saveStudent(student1);	
	}	
	@Test
	public void testInsertStudent() {

    	Set<Phone> phoneNumbers = new HashSet<Phone>();					// Create the set of phone numbers    	
    	Phone thePhone = new Phone();									// Create the phone object
    	thePhone.setPhoneNumber("7777777777");			// get the home phone from the form
    	thePhone.setPhoneType("Home");									// set the phone type
    	phoneNumbers.add(thePhone);										// Add the first phone number
    	thePhone = new Phone();											// Create the phone object
    	thePhone.setPhoneNumber("8888888888");			// get the cell phone from the form
    	thePhone.setPhoneType("Cell");									// set the phone type    
    	phoneNumbers.add(thePhone);										// Add the cell phone number
    	
    	Set<Email> emailAddresses = new HashSet<Email>();				// Create the set of Email address
    	Email Email = new Email();										// Create the Email object
    	Email.setEmailAddr("EMAIL1@SOMEWHERE.COM");					// get the home Email from the form
    	Email.setEmailType("Home");									    // set the Email type
    	emailAddresses.add(Email);										// Add the first Email address
    	Email = new Email();											// Create the phone object
    	Email.setEmailAddr("EMAIL2@SOMEWHERE.COM");					// get the cell phone from the form
    	Email.setEmailType("Work");										// set the email type
    	emailAddresses.add(Email);										// Add the second email address  	
    	
    	Set<Web> webAddresses = new HashSet<Web>();						// Create the set of web address
    	Web theWeb = new Web();											// We support three web sites per student
    	theWeb.setUrl("WWW.SOMEPLACE1.COM");					
    	webAddresses.add(theWeb);
    	theWeb = new Web();
    	theWeb.setUrl("WWW.SOMEPLACE2.COM");					
    	webAddresses.add(theWeb); 
    	theWeb = new Web();
    	theWeb.setUrl("WWW.SOMEPLACE2.COM");					
    	webAddresses.add(theWeb);  	
    	
    	
    	// Create the address object
		Address address = new Address("1234 SOMESTREET", "CITY1", "OH", "43830", "USA");
	
		//Create the student object and add the addresses, phone numbers, email addresses and radio show information
		Student student1 = new Student("JOHN TEST",  address, phoneNumbers,emailAddresses,webAddresses, new HashSet<RadioShow>(0));	
		
		student1.setStudentInfo("INFO");				// Set that new basic information field
		student1.setStudentType("RADIO_SHOW");			// Get the student type information out of the form.
		
//		if ( theEditForm.getStudentId() != 0) {
//			student1.setStudentId(theEditForm.getStudentId());
//		}
		
		
    	StudentService.saveStudent(student1);	
	}
	
	@Test
	public void testInsertEmail() {
		Email theEmail = new Email();
		theEmail.setEmailAddr("SomeEmail@xxx.com");
		theEmail.setEmailType("BogusType");
		org.hibernate.SessionFactory sessionFactory  = sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();										// begin the transaction before we delete
		
	    session.save(theEmail);   
	    // Get the email id just saved
	    long id=theEmail.getEmailId();
    
		session.getTransaction().commit();		
		
		StudentEmail theData = new StudentEmail();
		theData.setStudentId(new Long(59));
		theData.setEmailId(id);
		
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();		
		session.save(theData);
		session.getTransaction().commit();
		
	}


	@Test
	public void testDeleteStudentEmail() {
		StudentEmail theData = new StudentEmail();
		theData.setEmailId(new Long(1685));
		theData.setStudentId(new Long(59));
		org.hibernate.SessionFactory sessionFactory  = sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();										// begin the transaction before we delete
		session.delete(theData);										// delete the student record
		session.getTransaction().commit();								// commit the transaction		
	
	}
	
	@Test
	public void testDeleteEmail() {
		Email theEmail = new Email();
		theEmail.setEmailId(new Long(1685));
		org.hibernate.SessionFactory sessionFactory  = sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();										// begin the transaction before we delete
		session.delete(theEmail);										// delete the student record
		session.getTransaction().commit();								// commit the transaction		
	}
	@Test
	public void testWebByStudentId() {
		Long StudentId = new Long(59);
		List<Web> theWebList = new ArrayList<Web>();
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();	
		session.beginTransaction();
		
		// Grab the SQL query string
		String sql = SqlQuery.SELECT_WEB;
		
		// Add the scalars to tell us what data type that we are using
		SQLQuery q = session.createSQLQuery(sql).addScalar("url", Hibernate.STRING)
												.addScalar("webid",Hibernate.LONG);
																				    
		q.setParameter("studentNum", StudentId);

		// Convert the result using the Result set transformer
		q.setResultTransformer(Transformers.aliasToBean(Web.class));
		
		theWebList = q.list();
		Assert.assertNotNull(theWebList);
		Assert.assertTrue(theWebList.size() > 0);
		Web theWeb = theWebList.get(0);
		long theId = theWeb.getWebid();
		Long theWebId = new Long(theId);
		Assert.assertNotNull(theWebId);
		Assert.assertTrue(theWebId.equals(new Long(0)) != true);
		session.getTransaction().commit();
			
	}		
	
	@Test
	public void testPhoneByStudentId() {
		Long StudentId = new Long(59);
		List<Phone> thePhoneNumbers = new ArrayList<Phone>();
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();	
		session.beginTransaction();
		
		// Grab the SQL query string
		String sql = SqlQuery.SELECT_PHONE;
		
		// Add the scalars to tell us what data type that we are using
		SQLQuery q = session.createSQLQuery(sql).addScalar("phoneId", Hibernate.LONG)
												.addScalar("phoneType", Hibernate.STRING)
												.addScalar("phoneNumber", Hibernate.STRING);									    
		q.setParameter("studentNum", StudentId);

		// Convert the result using the Result set transformer
		q.setResultTransformer(Transformers.aliasToBean(Phone.class));
		
		thePhoneNumbers = q.list();
		Assert.assertNotNull(thePhoneNumbers);
	    Phone thePhone = thePhoneNumbers.get(0);
	    Assert.assertNotNull(thePhone);
	    Assert.assertNotNull(thePhone.getPhoneId());
		session.getTransaction().commit();
			
	}	
	
	
	@Test
	public void testEmailsByStudentId() {
		Long StudentId = new Long(59);
		List<Email> theEmails = new ArrayList<Email>();
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();	
		session.beginTransaction();
		
		// Grab the sql query string
		String sql = SqlQuery.SELECT_EMAIL;
		
		// Add the scalars to tell us what data type that we are using
		SQLQuery q = session.createSQLQuery(sql).addScalar("emailId", Hibernate.LONG)
												.addScalar("emailAddr", Hibernate.STRING)
											    .addScalar("emailType", Hibernate.STRING);
		q.setParameter("studentNum", StudentId);

		
		// Convert the result using the Result set transformer
		q.setResultTransformer(Transformers.aliasToBean(Email.class));
		
		theEmails = q.list();
		Assert.assertNotNull(theEmails);
		
		session.getTransaction().commit();
			
	}
	@Test
	public void testStudentsById() {
		
		Long studentId = new Long(59);
		List<Student> theStudents = new ArrayList<Student>();
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();	
		session.beginTransaction();
		
		// Grab the SQL query string
		String sql = SqlQuery.SELECT_STUDENT_BY_ID;
		
		// Add the scalars to tell us what data type that we are using
		SQLQuery q = session.createSQLQuery(sql).addScalar("studentId", Hibernate.LONG)
												.addScalar("studentName", Hibernate.STRING)
												.addScalar("studentType", Hibernate.STRING)
											    .addScalar("studentInfo", Hibernate.STRING)
											    .addScalar("State", Hibernate.STRING)
											    .addScalar("City", Hibernate.STRING)
											    .addScalar("AddressId", Hibernate.LONG)
											    .addScalar("Street", Hibernate.STRING)
												.addScalar("country", Hibernate.STRING)
												.addScalar("zip", Hibernate.STRING);
		q.setParameter("studentId", studentId);

		
		// Convert the result using the Result set transformer
		q.setResultTransformer(Transformers.aliasToBean(Student.class));
		
		theStudents = q.list();
		Assert.assertNotNull(theStudents);
		
		Student theData = theStudents.get(0);
		Assert.assertNotNull(theData);
		
		Assert.assertNotNull(theData.getStreet());
		Assert.assertNotNull(theData.getCity());
		Assert.assertNotNull(theData.getCountry());
		Assert.assertNotNull(theData.getZip());
		String TheState= theData.getState();
		
		session.getTransaction().commit();
		
		
	}	
	
	@Test
	public void testStates() {
		
		List<State> theStudents = new ArrayList<State>();
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();	
		session.beginTransaction();
		
		// Grab the SQL query string
		String sql = SqlQuery.SELECT_STATES;
		
		// Add the scalars to tell us what data type that we are using
		SQLQuery q = session.createSQLQuery(sql).addScalar("stateid", Hibernate.LONG)
												.addScalar("abbrev", Hibernate.STRING);

		// Convert the result using the Result set transformer
		q.setResultTransformer(Transformers.aliasToBean(State.class));
		
		theStudents = q.list();
		Assert.assertNotNull(theStudents);
		
		State theData = theStudents.get(0);
		Assert.assertNotNull(theData);
		
		Assert.assertNotNull(theData.getStateid());
		Assert.assertNotNull(theData.getAbbrev());
		session.getTransaction().commit();
		
		
	}	
	
	@Test
	public void testListRadioShowUsingStudentId() {
		
		// Hard coded test change this later
		Set<RadioShow> theRadioShowSet =  StudentService.listRadioShowUsingStudentId(210L);
		
		assert(theRadioShowSet != null);
		assert(theRadioShowSet.size() > 0);		
	}
	@Test
	public void testSelectStudentsByStudentType() {
		
		List<Student> theList = StudentService.selectStudentsByStudentType("RADIO_SHOW");
		assert(theList != null);
		assert(theList.size() > 0);

	}


	@Test
	public void listStudentsWithPages() {
		List<Student> theList = StudentService.listStudentsWithPages(0, 10);
		assert(theList != null);
		assert(theList.size() > 0);
		assert(theList.size() < 12);
	}
	
	@Test
	public void testGetCompleteStudentById() {
		Session session = sessionFactory.getCurrentSession();	
		session.beginTransaction();
		List<Student> theList = session.createQuery("from Student").list();
		session.getTransaction().commit();
		assert(theList != null);
		assert(theList.size()>0);		
		Student theStudent;  Student firstStudent;
		firstStudent = theList.get(0);
		theStudent = StudentService.getCompleteStudentById(firstStudent.getStudentId());
		assert(theStudent != null);
		assert(theStudent.getStudentAddress() != null);
		assert(theStudent.getStudentEmailAddresses() != null);
		assert(theStudent.getStudentEmailAddresses().size() != 0);
		// Might not have any radio show but should not be null
		assert(theStudent.getStudentRadioShows() != null);
	}
	
	
	@Test
	public void testSearchNameStarsearchWithMatchModetsWith() {
		Session session = sessionFactory.getCurrentSession();	
		session.beginTransaction();
		List<Student> theList = session.createQuery("from Student").list();
		session.getTransaction().commit();
		assert(theList != null);
		assert(theList.size()>0);		
		Student theStudent;  Student firstStudent;
		firstStudent = theList.get(0);
		String subString = firstStudent.getStudentName().substring(0, 1);
		theList = StudentService.searchWithMatchMode(Constants.ANYWHERE, subString);
		assert(theList != null);
		assert(theList.size() != 0);
		
	}
	
	@Test
	public void testSearchNameStartsWith() {
		Session session = sessionFactory.getCurrentSession();	
		session.beginTransaction();
		List<Student> theList = session.createQuery("from Student").list();
		session.getTransaction().commit();
		assert(theList != null);
		assert(theList.size()>0);		
		Student theStudent;  Student firstStudent;
		firstStudent = theList.get(0);
		String subString = firstStudent.getStudentName().substring(0, 1);
		theList = StudentService.searchNameStartsWith(subString);
		assert(theList != null);
		assert(theList.size() != 0);
		
	}
	
	@Test
	public void testGetCompleteStudentByName() {
		Session session = sessionFactory.getCurrentSession();	
		session.beginTransaction();
		List<Student> theList = session.createQuery("from Student").list();
		session.getTransaction().commit();
		assert(theList != null);
		assert(theList.size()>0);		
		Student theStudent;  Student firstStudent;
		firstStudent = theList.get(0);
		theStudent = StudentService.getCompleteStudentByName(firstStudent.getStudentName());
		assert(theStudent != null);
		assert(theStudent.getStudentAddress() != null);
		assert(theStudent.getStudentEmailAddresses() != null);
		assert(theStudent.getStudentEmailAddresses().size() != 0);
	}
	@Test
	public void testListWebUsingStudentId() {
		Session session = sessionFactory.getCurrentSession();	
		session.beginTransaction();
		List<StudentWeb> theList = session.createQuery("from StudentWeb").list();	
		session.getTransaction().commit();
		assert(theList != null);
		assert(theList.size()>0);	
		StudentWeb theStudentWeb = theList.get(0);
		Set<Web> theWebList = StudentService.listWebUsingStudentId(theStudentWeb.getStudentId());
		assert(theWebList != null);
		assert(theWebList.size()>0);	
	}
	
	@Test
	public void testSaveStudent() {

		sessionFactory = new Configuration().configure().buildSessionFactory();	
		Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();	
        // ADD THE RECORD
		List<Student> theList = new ArrayList<Student>();				// Create the array list for the Students	
		Set<Phone> phoneNumbers = new HashSet<Phone>();					// Create the set of phone numbers
		phoneNumbers.add(new Phone("house","7404522222"));				// Add the phone numbers
		phoneNumbers.add(new Phone("mobile","7406241111"));
		Set<Email> emailAddresses = new HashSet<Email>();				// create the set of email addresses
		emailAddresses.add(new Email("house","joe@columbus.rr.com"));	// Add email addresses
		emailAddresses.add(new Email("work","joe@zanesville.com"));		
		Set<Web> webAddresses = new HashSet<Web>();				// create the set of web addresses
		webAddresses.add(new Web("http://www.mark-snider.com/"));
		webAddresses.add(new Web("http://www.blogtalkradio.com/ohioexopolitics"));
		webAddresses.add(new Web("http://www.exopoliticsohio.us/"));
		
    	RadioShow theRadioShow = new RadioShow();
    	theRadioShow.setArchiveListens(100);
    	theRadioShow.setDescription("test description");
    	theRadioShow.setTitle("test title");
    	theRadioShow.setLiveListens(100);
    	theRadioShow.setListedDate(new Date());
    	
        Set<RadioShow> radioShows = new HashSet<RadioShow>();	
        
    	RadioShow theRadioShow2 = new RadioShow();
    	theRadioShow2.setArchiveListens(100);
    	theRadioShow2.setDescription("Second test description");
    	theRadioShow2.setTitle("Second test title");
    	theRadioShow2.setLiveListens(100);
    	theRadioShow2.setListedDate(new Date());       
        radioShows.add(theRadioShow);
        radioShows.add(theRadioShow2);
        
		Address address = new Address("1234 Red Street", "Nashport", "OH", "43830", "USA");	
		Student student1 = new Student("AAA STUDENT",  address, phoneNumbers,emailAddresses,webAddresses,radioShows);		
		student1.setStudentInfo("SOME INFO");
		student1.setStudentType("RADIOSHOW");
		session.save(student1);
		session.getTransaction().commit();

	
		session = sessionFactory.getCurrentSession();
        session.beginTransaction();
		Criteria theCriteria = session.createCriteria(Student.class);	// create the criteria class
		theList = theCriteria.list();									// call the list method
		session.getTransaction().commit();								// commit the transaction
		assert(theList != null);
		assert(theList.size()>0);			
	}
	
	@Test
	public void testAddStudent() {

		sessionFactory = new Configuration().configure().buildSessionFactory();	
		Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();	
        // ADD THE RECORD
		List<Student> theList = new ArrayList<Student>();				// Create the array list for the Students	
		Set<Phone> phoneNumbers = new HashSet<Phone>();					// Create the set of phone numbers
		phoneNumbers.add(new Phone("house","7404522222"));				// Add the phone numbers
		phoneNumbers.add(new Phone("mobile","7406241111"));
		Set<Email> emailAddresses = new HashSet<Email>();				// create the set of email addresses
		emailAddresses.add(new Email("house","joe@columbus.rr.com"));	// Add email addresses
		emailAddresses.add(new Email("work","joe@zanesville.com"));		
		Set<Web> webAddresses = new HashSet<Web>();				// create the set of web addresses
		webAddresses.add(new Web("http://www.mark-snider.com/"));
		webAddresses.add(new Web("http://www.blogtalkradio.com/ohioexopolitics"));
		webAddresses.add(new Web("http://www.exopoliticsohio.us/"));
		
    	RadioShow theRadioShow = new RadioShow();
    	theRadioShow.setArchiveListens(100);
    	theRadioShow.setDescription("test description");
    	theRadioShow.setTitle("test title");
    	theRadioShow.setLiveListens(100);
    	theRadioShow.setListedDate(new Date());
    	
        Set<RadioShow> radioShows = new HashSet<RadioShow>();	
        radioShows.add(theRadioShow);
        
		Address address = new Address("1234 Red Street", "Nashport", "OH", "43830", "USA");	
		Student student1 = new Student("TEST STUDENT",  address, phoneNumbers,emailAddresses,webAddresses,radioShows);		
		student1.setStudentInfo("SOME INFO");
		student1.setStudentType("RADIOSHOW");
		session.save(student1);
		session.getTransaction().commit();
	
		session = sessionFactory.getCurrentSession();
        session.beginTransaction();
		Criteria theCriteria = session.createCriteria(Student.class);	// create the criteria class
		theList = theCriteria.list();									// call the list method
		session.getTransaction().commit();								// commit the transaction
		assert(theList != null);
		assert(theList.size()>0);			
	}
	@Test
	public void testAddSecondStudent() {

		Session session = sessionFactory.getCurrentSession();	

		List<Student> theList = new ArrayList<Student>();				// Create the array list for the Students	
		Set<Phone> phoneNumbers = new HashSet<Phone>();						// Create the set of phone numbers
		phoneNumbers.add(new Phone("house","7404524422"));					// Add the phone numbers
		phoneNumbers.add(new Phone("mobile","7406242203"));
		Set<Email> emailAddresses = new HashSet<Email>();					// create the set of email addresses
		emailAddresses.add(new Email("house","msnider5@columbus.rr.com"));	// Add email addresses
		emailAddresses.add(new Email("work","FoundOut@Soon.com"));		
		
		Address address = new Address("4995 Watershed Way", "Nashport", "OH", "43830","USA");
		Student student1 = new Student("Mark Snider",  address, phoneNumbers,emailAddresses);		
		
		StudentService.addStudent(session, student1);					// Add a couple of student records
	
		session = sessionFactory.getCurrentSession();
        session.beginTransaction();
		Criteria theCriteria = session.createCriteria(Student.class);	// create the criteria class
		theList = theCriteria.list();									// call the list method
		session.getTransaction().commit();								// commit the transaction
		assert(theList != null);
		assert(theList.size()>0);			
	}
	
	@Test
	public void testlistCompleteStudentInfo() {
		List<Student> theList = StudentService.listCompleteStudentInfo();
		assert(theList != null);
		assert(theList.size()>0);	
		Set<Phone> thePhones; Set<Email> theEmails;
        for (Student data: theList) { 
        	assert(data.getStudentName().length()>0);     	
        	Address theAddress = data.getStudentAddress();
        	assert(theAddress.getStreet().length()>0);
        	assert(theAddress.getCity().length()>0);
        	assert(theAddress.getState().length()>0); 
        	assert(theAddress.getZipcode().length()>0);
        	thePhones = data.getStudentPhoneNumbers();
        	for (Phone p: thePhones) {
        		assert(p.getPhoneNumber().length()>0);
        		assert(p.getPhoneType().length()>0);
        	}
        	theEmails = data.getStudentEmailAddresses();
        	for (Email e: theEmails) {
        		assert(e.getEmailAddr().length()>0);
        		assert(e.getEmailType().length()>0);
        	}  
        }	
	}

	@Test
	public void testlistCompleteStudentInfoWithSort() {
		CurrentSort theSort = new CurrentSort();
		theSort.setCurrentSort(Constants.SORT_DESC);
		List<Student> theList = StudentService.listCompleteStudentInfoWithSort(theSort);
		assert(theList != null);
		assert(theList.size()>0);	
		Set<Phone> thePhones; Set<Email> theEmails;
        for (Student data: theList) { 
        	assert(data.getStudentName().length()>0);     	
        	Address theAddress = data.getStudentAddress();
        	assert(theAddress.getStreet().length()>0);
        	assert(theAddress.getCity().length()>0);
        	assert(theAddress.getState().length()>0); 
        	assert(theAddress.getZipcode().length()>0);
        	thePhones = data.getStudentPhoneNumbers();
        	for (Phone p: thePhones) {
        		assert(p.getPhoneNumber().length()>0);
        		assert(p.getPhoneType().length()>0);
        	}
        	theEmails = data.getStudentEmailAddresses();
        	for (Email e: theEmails) {
        		assert(e.getEmailAddr().length()>0);
        		assert(e.getEmailType().length()>0);
        	}  
        }	
	}
	
	@Test
	public void testListStudentsComplete() {
		List<Student> theList = StudentService.listStudents(sessionFactory.getCurrentSession());
		assert(theList != null);
		assert(theList.size()>0);			
	}
	
	@Test
	public void testListStudents() {
		List<Student> theList = StudentService.listStudents(sessionFactory.getCurrentSession());
		assert(theList != null);
		assert(theList.size()>0);			
	}
	@Test
	public void testListSecondStudents() {
		List<Student> theList = StudentService.listStudents();
		assert(theList != null);
		assert(theList.size()>0);			
	}

	@Test
	public void testUpdateStudent() {
		Session session = sessionFactory.getCurrentSession();	
		Address address = new Address("4995 Watershed Way"+Math.random(), "Nashport", "OH", "43830","USA");
		Student student1 = new Student("Mark Snider " + Math.random(), address);
		StudentService.addStudent(session, student1);
		session = sessionFactory.getCurrentSession();
        session.beginTransaction();			
		List<Student> theList = session.createQuery("from Student").list();
		assert(theList != null);
		assert(theList.size()>0);			
		Student theStudent = theList.get(0);
		theStudent.setStudentName("Updated Student Name");
		StudentService.updateStudent(sessionFactory.getCurrentSession(), theStudent);
		session = sessionFactory.getCurrentSession();
        session.beginTransaction();			
		theList = session.createQuery("from Student").list();
		theStudent = theList.get(0);
		assert(theStudent.getStudentName().equals("Updated Student Name"));
	}
	
	@Test
	public void testDeleteStudent() {
		Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();			
		//List<Student> theList = session.createQuery("from Student S where S.studentName = 'AAATEST'").list();
        long TheStudentId = 309L;
        String StudentId = ""+ TheStudentId;
        String sql ="from Student S where S.studentId = ";
        sql += StudentId;
        // from Student S where S.studentId = 309
        
        List<Student> theList = session.createQuery(sql).list();
		session.getTransaction().commit();
		assert(theList != null);
		assert(theList.size()>0);
		
		Student theStudent = theList.get(0);
		StudentService.deleteStudent(theStudent);	
		assert(theStudent != null);

	}
	public void testDeleteStudentUsingId() {
		
		Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();			
		List<Student> theList = new ArrayList<Student>();				// Create the array list for the Students	
		Set<Phone> phoneNumbers = new HashSet<Phone>();					// Create the set of phone numbers
		phoneNumbers.add(new Phone("house","7404522222"));				// Add the phone numbers
		phoneNumbers.add(new Phone("mobile","7406241111"));
		Set<Email> emailAddresses = new HashSet<Email>();				// create the set of email addresses
		emailAddresses.add(new Email("house","joe@columbus.rr.com"));	// Add email addresses
		emailAddresses.add(new Email("work","joe@zanesville.com"));		
		Set<Web> webAddresses = new HashSet<Web>();				// create the set of web addresses
		webAddresses.add(new Web("http://www.mark-snider.com/"));
		webAddresses.add(new Web("http://www.blogtalkradio.com/ohioexopolitics"));
		webAddresses.add(new Web("http://www.exopoliticsohio.us/"));
		
		Address address = new Address("1234 Red Street", "Nashport", "OH", "43830", "USA");	
		Student student1 = new Student("TEST",  address, phoneNumbers,emailAddresses,webAddresses);		
		student1.setStudentInfo("SOME INFO");
		session.save(student1);
		session.getTransaction().commit();


	}



}
