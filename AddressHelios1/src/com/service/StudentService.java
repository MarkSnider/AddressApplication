package com.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.transform.Transformers;
import org.hibernate.SQLQuery;
import org.junit.Assert;
import org.junit.Test;

import com.dao.Address;
import com.dao.CurrentSort;
import com.dao.Email;
import com.dao.Event;
import com.dao.Phone;
import com.dao.RadioShow;
import com.dao.State;
import com.dao.Student;
import com.dao.StudentType;
import com.dao.StudentRadioShow;
import com.dao.Web;
import com.constants.Constants;
import com.dao.StudentEmail;
import com.dao.StudentPhone;
import com.constants.SqlQuery;
import com.example.SomeExampleClass;

public class StudentService {
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Get the actual one item
		List<Student> theList = SelectStudentsById(new Long(55));
		Assert.assertNotNull(theList);
	}	
	
	/**
	 * Get the student id using the Hibernate query
	 * @param theId - the student id not the object
	 * @return return the student object
	 */
	public static Student SelectStudentById(long theId) {
		// Get the session factory
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		
		Session session = sessionFactory.getCurrentSession();		// Get the session	
        session.beginTransaction();									// Start the transaction
        String StudentId = ""+ theId;								// Set the student id
        String sql ="from Student S where S.studentId = ";			// Build the query without the id
        sql += StudentId;    										// add the id
        List<Student> theList = session.createQuery(sql).list();	// Do the hibernate query
		session.getTransaction().commit();							// Commit the transaction
		Student theStudent = theList.get(0);						// Get the student record
		return(theStudent);											// Return the student object
	}
	/**
	 * Deletes the entry in the student phone table
	 * @param phoneId - id for the phone
	 * @param studentId - id associated to the student
	 */
	public void DeleteStudentPhone(long phoneId, long studentId) {
		StudentPhone theData = new StudentPhone();
		theData.setPhoneId(phoneId);
		theData.setStudentId(studentId);
		org.hibernate.SessionFactory sessionFactory  = sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();										// begin the transaction before we delete
		session.delete(theData);										// delete the student record
		session.getTransaction().commit();								// commit the transaction		
	
	}
	

	public void DeletePhone(long phoneId) {
		Phone thePhone = new Phone();
		thePhone.setPhoneId(phoneId);
		org.hibernate.SessionFactory sessionFactory  = sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();										// begin the transaction before we delete
		session.delete(thePhone);										// delete the student record
		session.getTransaction().commit();								// commit the transaction		
	}	
	/**
	 * Build the Hashset of phone information that consists of the phone ids, numbers and types.  Type being work or home.
	 * @param request - The object that contains all of the parameters passed
	 * @return - Hashset of Phone numbers
	 */	
	public static Set<Phone> processPhoneNumbers(HttpServletRequest request) {
		
		// Array of strings that comes from the page.  Multiple text fields or hidden fields
        String[] phones = request.getParameterValues("phones");
        String[] phonesIds = request.getParameterValues("phonesIds");
        String[] phoneTypes = request.getParameterValues("phoneTypes");	
        
        // Get the Phone types new and the new PhoneIds 
        String[] PhoneTypeNew = request.getParameterValues("PhoneTypeNew");
        String[] PhoneNumberNew = request.getParameterValues("PhoneNumberNew");    
        
        Set<Phone> PhoneAddresses = buildPhoneAddresses(phonesIds,phones, phoneTypes, PhoneTypeNew, PhoneNumberNew);
        
        return(PhoneAddresses);
	}
	/**
	 * Build the Hashset of phone information that consists of the phone ids, numbers and types.  Type being work or home.
	 * @param phoneIds - an array of strings of ids into the database
	 * @param PhoneNumbers = an array of strings of phone numbers
	 * @param phoneTypes = An array of string of phone type meaning home or work
	 * @return - Hashset of Phone numbers
	 */
	public static Set<Phone> buildPhoneAddresses(String[] phoneIds, String[] PhoneNumbers, String[] phoneTypes, String[] PhoneTypeNew, String[] PhoneNumberNew) {
		
		Set<Phone> PhoneAddresses = new HashSet<Phone>();
		int i=0;
		 
	    // Make sure we dont blow up if  called from add
		if (phoneIds != null && phoneIds[0].isEmpty() != true) {
			for (String data: phoneIds) {						// Loop through the array of Phone 
				
				 // Do not add an phone that is flagged for delete
				 if (data != null && data.contains("DELETE") != true) {
					 Phone thePhone = new Phone();					// Construct the Phone object											
					 thePhone.setPhoneId(new Long(data));			// Place the id in the object
					 String PhoneNumber = PhoneNumbers[i];			// Get the phone number
					 String PhoneType = phoneTypes[i];				// Get the phone Type
					 thePhone.setPhoneNumber(PhoneNumber);			// Set the phone number
					 thePhone.setPhoneType(PhoneType);				// Set the phone type
					 PhoneAddresses.add(thePhone);					// Add the object to the HashSet				 
				 }

			    i++;
			 }				
		}
	
		 int j=0;
		 
		 // Do not create an empty row in the database
		 if (PhoneTypeNew != null && PhoneTypeNew[0].isEmpty() != true) {
			 for (String data2: PhoneTypeNew) {	
				 Phone thePhone = new Phone();					// Construct the Phone object		
				 thePhone.setPhoneId(new Long(0));				// New Phone Id to zero
				 String thePhoneNum = PhoneNumberNew[j];		// Get the new address
				 thePhone.setPhoneNumber(thePhoneNum);	
				 String phoneType = PhoneTypeNew[j];
				 thePhone.setPhoneType(phoneType);			// Set the type		
				 PhoneAddresses.add(thePhone);				// Add the object to the HashSet
				 j++;
			 }			 
		 }		
		return(PhoneAddresses);
	}
	 /**
	  * Loop through the two string arrays build a HASHSET out of the information
	  * @param webIds - the IDS for the web objects
	  * @param webUrls - URLS for the web objects
	  * @return - HashSet of Web objects populated with IDS and URLS
	  */
	 public static Set<Web> buildWebAddresses(String[] webIds, String[] webUrls, String[] WebUrlNew) {
		 
		 Set<Web> webAddresses = new HashSet<Web>();
		 int i=0;
		 
		 // Make sure we don't blow up when called from add
		 if (webIds != null && webIds[0].isEmpty() != true) {
			 for (String data: webIds) {				// Loop through the array of web IDS
				 
				 if (data != null && data.contains("DELETE") != true) {
				    Web theWeb = new Web();					// Construct the web object											
				    theWeb.setWebid(new Long(data));		// Place the id in the object
				    String Url = webUrls[i];				// Get the URL out of the string Array
				    theWeb.setUrl(Url);						// Place the URL in the object				
				    webAddresses.add(theWeb);				// Add the object to the HashSet
				 }
			    i++;
			 }			 
		 }

		 
		 int j=0;
		 // Do not create an empty row in the database
		 if (WebUrlNew != null && WebUrlNew[0].isEmpty() != true) {
			 
			 for (String data2: WebUrlNew) {	
				 Web theWeb = new Web();					// Construct the Phone object		
				 theWeb.setWebid(new Long(0));				// New Web Id to zero
				 String theWebUrl = WebUrlNew[j];			// Get the new address
				 theWeb.setUrl(theWebUrl);					// The web URL
				 webAddresses.add(theWeb);					// Add the object to the HashSet
				 j++;
			 }			 
		 }			 
		 return(webAddresses);
	 }
	 
	    /**
	     * Turn all of the Web addresses into a HashTable
	     * @param request
	     * @return A HASHSET of email web addresses
	     */
		public static Set<Web> processWebAddresses(HttpServletRequest request) {
			
	        // Get the webs information
	        String[] webIds = request.getParameterValues("webIds");
	        String[] webUrls = request.getParameterValues("webs");
	        String[] WebUrlNew = request.getParameterValues("WebUrlNew");
			
			// Get the web addresses from the 3 arrays
			Set<Web> WebAddresses = StudentService.buildWebAddresses(webIds, webUrls, WebUrlNew);

			return(WebAddresses);
		}	 
	    /**
	     * Turn all of the existing email address information into a HashTable
	     * @param request
	     * @return A HASHSET of email addresses
	     */
		public static Set<Email> processEmailAddresses(HttpServletRequest request) {
	        // Get the email addresses, phones and webs list
	        String[] emails = request.getParameterValues("emails");
	        String[] emailIds = request.getParameterValues("emailIds");
	        String[] emailTypes = request.getParameterValues("emailTypes");
	        
	        // Get the email types news and the emails new
	        String[] emailTypesNew = request.getParameterValues("emailTypesNew");
	        String[] emailsNew = request.getParameterValues("emailsNew");
			
			// Get the web addresses from the 3 arrays
			Set<Email> emailAddresses = StudentService.buildEmailAddresses(emailIds, emails,emailTypes, emailTypesNew, emailsNew);

			return(emailAddresses);
		}	
		
	 /**
	  * Populate the email hash tables
	  * @param EmailIds - array of email IDS
	  * @param EmailAddresses - array of email addresses
	  * @param emailType - array of email types (work, home)
	  * @return - A HASHSET of Email o
	  */
	 public static Set<Email> buildEmailAddresses(String[] EmailIds, String[] EmailAddresses, String[] emailTypes, String[] emailTypesNew, String[] emailsNew) {
		 Set<Email> EmailHash = new HashSet<Email>();
		 int i=0;
		 
		 // Make sure we don't blow up if called from add
		 if (EmailIds != null && EmailIds[0].isEmpty() != true) {
			 for (String data: EmailIds) {				// Loop through the array of web IDS
				 
				 // Do not add an email that is flagged for delete
				 if (data != null && data.contains("DELETE") != true) {
						Email theEmail = new Email();			// Construct the web object		
						theEmail.setEmailId(new Long(data));
					    String address = EmailAddresses[i];		// Get the URL out of the string Array
					    theEmail.setEmailAddr(address);			// Place the URL in the object		
					    String emailType = emailTypes[i];
					    theEmail.setEmailType(emailType);		// Set the type
					    EmailHash.add(theEmail);				// Add the object to the HashSet				 
				 }

			    i++;
			 }				 
		 }
	
		 
		 int j=0;
		 
		 // Do not create an empty row in the database
		 if (emailTypesNew != null && emailTypesNew[0].isEmpty() != true) {
			 for (String data2: emailTypesNew) {	
				 Email theEmail = new Email();	
				 theEmail.setEmailId(new Long(0));		// New Email Id to zero
				 String address = emailsNew[j];			// Get the new address
				 theEmail.setEmailAddr(address);	
				 String emailType = emailTypesNew[j];
				 theEmail.setEmailType(emailType);		// Set the type		
				 
				 EmailHash.add(theEmail);				// Add the object to the HashSet
				 j++;
			 }			 
		 }

		 return(EmailHash);
	 }
     private int getNumberValue(StringBuffer theString) {
    	 int value=0;
 		for (int i=0; i < theString.length(); i++) {
			value += Character.getNumericValue(theString.charAt(i));
		}
		
		System.out.println("value " + value);
		return(value);
     }
     
    
     /**
      * Get the web URLS for this person
      * A list of Web objects
      * @return
      */
     public static List<Web> selectWeb(Long StudentId) {
 		
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
		session.getTransaction().commit();   
		return(theWebList);
     }
    /**
     * Select the phone numbers associated to a Student Id
     * @return
     */
 	 public  static List<Phone> selectPhones(long studentId) {
		Long StudentId = new Long(studentId);
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
		session.getTransaction().commit();
		return(thePhoneNumbers);	
	}	    
     
     /**
      * Builds the list of states that is used in the state drop down.
      * @return A List of state objects
      */
     public static List<State> selectStates() {
 		List<State> theStates = new ArrayList<State>();
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
		
		theStates = q.list();
		State theData = theStates.get(0);
		session.getTransaction().commit();
		return(theStates);
     }
	/**
	 * This query will allow us to find all of the information in the database
	 * that is related to people that are in guests of the radio show
	 * @param studentType can be RADIO_SHOW, FRIEND, FAMILY or STAMPER
	 * @return a list of student objects
	 */
	public static List<Student> selectStudentsByStudentType(String studentType) {
		
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();				
		session.beginTransaction();
		Criteria theCriteria = session.createCriteria(Student.class);
		Student theStudent = new Student();
		theStudent.setStudentType(studentType);
		Example theExample = Example.create(theStudent);
		theCriteria.add(theExample);
		Order order = Order.asc("studentName");							// We will order the list by name
		theCriteria.addOrder(order);									// Add the order to the criteria object
		List<Student> theList = theCriteria.list();
		session.getTransaction().commit();
		theList = populateSubOrdinateObjects(theList);					// Get all of the subordinate objects
		
	
		return(theList);
	}

	/**
	 * Populates all of the objects that are subordinate or embedded in student and or address
	 * @param theList completely populated list of items with things like email addresses, web addresses, and urls.
	 * @return  the list with all of the sub objects
	 */
	public static List<Student> populateSubOrdinateObjects(List<Student> theList) {
		Address populatedAddress;
		Set<Phone> thePhones;
		Set<Email> theEmails;
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();	
		// Because of lazy loading all of the sub objects are not populated
        for (Student data: theList) {
        	// We only have the address id if you try to get the rest of the address information you will get a lazy initialization exception			
        	Address theAddress = data.getStudentAddress();

        	session = sessionFactory.getCurrentSession();											
        	session.beginTransaction();
        	populatedAddress = (Address)session.get(Address.class, theAddress.getAddressId());		// retrieves all of the address information using get
        	session.getTransaction().commit();														// Close that transaction
        	data.setStudentAddress(populatedAddress);												// set the populated address object

        	thePhones = listStudentPhone(data.getStudentId());										// populate the student phone information using HashSet
        	data.setStudentPhoneNumbers(thePhones);													// update the HashSet
        	theEmails = listEmailPhone(data.getStudentId());										// populate the email information
        	data.setStudentEmailAddresses(theEmails);												// Set the email information in the hashset
        	data.setStudentWebAddresses(listWebUsingStudentId(data.getStudentId()));				// Get the URLs that the user has
        	// Get the radio show information if there is some there...
        	data.setStudentRadioShows(listRadioShowUsingStudentId(data.getStudentId()));       	
        }	
        return(theList);				
	}
	

	public static List<Student> SelectStudent() {
		
		List<Student> theStudents = new ArrayList<Student>();
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();	
		session.beginTransaction();
		
		// Grab the sql query string
		String sql = SqlQuery.SELECT_STUDENTS;
		
		// Add the scalars to tell us what data type that we are using
		SQLQuery q = session.createSQLQuery(sql).addScalar("studentId", Hibernate.LONG)
												.addScalar("studentName", Hibernate.STRING)
												.addScalar("studentType", Hibernate.STRING)
											    .addScalar("studentInfo", Hibernate.STRING)
											    .addScalar("State", Hibernate.STRING)
											    .addScalar("City", Hibernate.STRING)
											    .addScalar("AddressId", Hibernate.LONG);
		
		// Convert the result using the Result set transformer
		q.setResultTransformer(Transformers.aliasToBean(Student.class));
		
		theStudents = q.list();

		session.getTransaction().commit();
		return(theStudents);
	}
	
	/**
	 * Select the Student Type information.
	 * @return list of student objects
	 */
	public static List<StudentType> SelectStudentType() {
		
		List<StudentType> theStudents = new ArrayList<StudentType>();
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();	
		session.beginTransaction();
		
		// Grab the SQL query string for the students by type
		String sql = SqlQuery.SELECT_STUDENT_TYPE;
		
		// Add the scalars to tell us what data type that we are using
		SQLQuery q = session.createSQLQuery(sql).addScalar("studenttypeid", Hibernate.LONG)
												.addScalar("studentType", Hibernate.STRING);

		// Convert the result using the Result set transformer
		q.setResultTransformer(Transformers.aliasToBean(StudentType.class));
		
		theStudents = q.list();

		session.getTransaction().commit();
		return(theStudents);
	}	
	
	/**
	 * Select the information by Id.
	 * @param studentType - Might be for example radio show if the person is a guest on the show
	 * @return list of student objects
	 */
	public static List<Student> SelectStudentsById(long studentId) {
		
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
		Student theData = theStudents.get(0);
		session.getTransaction().commit();
		return(theStudents);
	}	
	/**
	 * Select the information by type.
	 * @param studentType - Might be for example radio show if the person is a guest on the show
	 * @return list of student objects
	 */
	public static List<Student> SelectStudentsByType(String studentType) {
		
		List<Student> theStudents = new ArrayList<Student>();
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();	
		session.beginTransaction();
		
		// Grab the sql query string
		String sql = SqlQuery.SELECT_STUDENTS_BY_TYPE;
		
		// Add the scalars to tell us what data type that we are using
		SQLQuery q = session.createSQLQuery(sql).addScalar("studentId", Hibernate.LONG)
												.addScalar("studentName", Hibernate.STRING)
												.addScalar("studentType", Hibernate.STRING)
											    .addScalar("studentInfo", Hibernate.STRING)
											    .addScalar("State", Hibernate.STRING)
											    .addScalar("City", Hibernate.STRING)
											    .addScalar("AddressId", Hibernate.LONG);
		q.setParameter("studentType", studentType);

		
		// Convert the result using the Result set transformer
		q.setResultTransformer(Transformers.aliasToBean(Student.class));
		
		theStudents = q.list();

		session.getTransaction().commit();
		return(theStudents);
	}	
	
	/**
	 * Select the emails for a given student id...
	 * @param StudentId - The student id
	 * @return a list of Email objects
	 */
	public static List<Email> SelectEmailForStudent(long StudentId) {
		
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

		session.getTransaction().commit();
		return(theEmails);
	}	
	/**
	 * Get list of web objects using a student id.  Here we do a join of web and student_web
	 * using native sql
	 * @param studentId the student id
	 * @return a list of web objects
	 */
	public static Set<Web> listWebUsingStudentId(long studentId) {
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();		
		
		String sql = "select web_url, web.web_id from web inner join student_web on " +
				"student_web.web_id = web.web_id and student_web.student_id = " + studentId;
		
		session.beginTransaction();
		Query query = session.createSQLQuery(sql);
		
		List webList = query.list();
		HashSet<Web> desiredList = new HashSet<Web>();
		for (int i=0; i < webList.size(); i++) {
			Object[] o = (Object[])webList.get(i);
			Web theWeb = new Web((String)o[0],(Integer)o[1]);
			desiredList.add(theWeb);
		}
		session.getTransaction().commit();
		return(desiredList);
	}
	
	/**
	 * Get Set of RadioShow objects using a student id.  
	 * @param studentId the student id
	 * @return a Set of Radio Show objects
	 */
	public static Set<RadioShow> listRadioShowUsingStudentId(long studentId) {
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();			
		session.beginTransaction();
		// Get the student radio show records
		List theList = StudentRadioShowService.selectStudentRadioShowByStudentIdHQL(studentId);

		// Create the set of radio show objects
		HashSet<RadioShow> desiredList = new HashSet<RadioShow>();
		
		// Loop through the student radio show objects getting the associated radio show
		for (int i=0; i < theList.size(); i++) {
			
		    // Get the object array out of the list
			Object[] items = (Object[])theList.get(i);
			
			// Get the radio show id
			Long radioShowId = (Long)items[0];
			
			// Get the list of radio show objects fpr that show id
			List theRadioShowList = RadioShowService.selectRadioShowByShowIdHQL(radioShowId);
			
			// Loop through the radio shows
			for (int j=0; j < theRadioShowList.size(); j++)  {
				
			    // Get the object array out of the list
				Object[] items2 = (Object[])theRadioShowList.get(j);
				
				// Get the title, description, archiveListens, liveListens
				String title = (String)items2[0];
				String description = (String)items2[1];
				Integer archiveListens = (Integer)items2[2];
				Integer liveListens = (Integer)items2[3];
				Date listedDate = (Date)items2[4];
				
				// Construct a radio show object
				RadioShow current = new RadioShow(title, description, archiveListens, liveListens,listedDate, radioShowId);
				
				// Add the radio show object to the HashSet
				desiredList.add(current);
			}
		}
		
		// This session is typically closed already
		if (session.isOpen()) {
			session.getTransaction().commit();
		}
		
		return(desiredList);
	}
	
	/**
	 * Create an HQL select to get all of the webids for specific studentid
	 * @param studentId the id associated to the student
	 * @return a list of webIds
	 */
	public static List<Long> listWebIdsFromStudentWeb(long studentId) {
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();		
		String hql = "Select webid from StudentWeb where studentId = :id";
		session.beginTransaction();
		Query query = session.createQuery(hql);
		query.setLong("id", studentId);
		List idList = query.list();
		session.getTransaction().commit();	
		return(idList);
	}
	/**
	 * Allows a paged display where user gets as specific subset of all of the data
	 * @param firstResult the first record of the returned result set
	 * @param maxResults the total number of records returned
	 * @return a list of student objects but not the complete list just what has been requested
	 */
	public static List<Student> listStudentsWithPages(int firstResult, int maxResults) {
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();		
		List<Student> theList = new ArrayList<Student>();				// Create the array list for the Students
		session.beginTransaction();										// begin the transaction before we create criteria
		Criteria theCriteria = session.createCriteria(Student.class);	// create the criteria class
		Order order = Order.asc("studentName");							// We will order the list by name
		theCriteria.addOrder(order);									// Add the order to the criteria object
		theCriteria.setFirstResult(firstResult);
		theCriteria.setMaxResults(maxResults);
		theList = theCriteria.list();									// call the list method
		session.getTransaction().commit();	
		Address populatedAddress;
		Set<Phone> thePhones;
		Set<Email> theEmails;
		// Because of lazy loading all of the sub objects are not populated
        for (Student data: theList) {
        	// We only have the address id if you try to get the rest of the address information you will get a lazy initialization exception			
        	Address theAddress = data.getStudentAddress();

        	session = sessionFactory.getCurrentSession();											
        	session.beginTransaction();
        	populatedAddress = (Address)session.get(Address.class, theAddress.getAddressId());		// retrieves all of the address information using get
        	session.getTransaction().commit();														// Close that transaction
        	data.setStudentAddress(populatedAddress);												// set the populated address object

        	thePhones = listStudentPhone(data.getStudentId());										// populate the student phone information using HashSet
        	data.setStudentPhoneNumbers(thePhones);													// update the HashSet
        	theEmails = listEmailPhone(data.getStudentId());										// populate the email information
        	data.setStudentEmailAddresses(theEmails);												// Set the email information in the hashset
        	
        }	
		System.err.println(" theList.size() " + theList.size());
		return(theList);
	}
	/**
	 * Allows user to search for a string in the name.  That can be found
	 * at the START, ANYWHERE, END or EXACT.
	 * @param matchMode START, ANYWHERE, END, EXACT
	 * @param nameStarts all or part of the name
	 * @return a list of student objects
	 */
	public static List<Student> searchWithMatchMode(String matchMode, String nameStarts) {
		List<Student> theList = new ArrayList<Student>();
		Student theStudent = new Student();
		theStudent.setStudentName(nameStarts);							// Prepare to search for all students that begin with D 
		Example theExample = Example.create(theStudent);				// Create the example object
		
		if (matchMode.equals(Constants.ANYWHERE)) {
			theExample.enableLike(MatchMode.ANYWHERE);					// find the string anywhere..	
		}
		else if (matchMode.equals(Constants.START)) {
			theExample.enableLike(MatchMode.START);						// find the string at the start
		}
		else if (matchMode.equals(Constants.EXACT)) {
			theExample.enableLike(MatchMode.EXACT);						// find the exact match
		}		
		else if (matchMode.equals(Constants.END)) {
			theExample.enableLike(MatchMode.END);						// find the string at the END
		}			
		
		Address populatedAddress; Set<Phone> thePhones;	Set<Email> theEmails;
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();										// begin the transaction before we create criteria
		Criteria theCriteria = session.createCriteria(Student.class);	// create the criteria class
		theCriteria.add(theExample);									// Add the starts with search
		theList = theCriteria.list();									// call the list method
		session.getTransaction().commit();								// commit the transaction	
		// Because of lazy loading all of the sub objects are not populated
        for (Student data: theList) {
        	// We only have the address id if you try to get the rest of the address information you will get a lazy initialization exception			
        	Address theAddress = data.getStudentAddress();

        	session = sessionFactory.getCurrentSession();											
        	session.beginTransaction();
        	populatedAddress = (Address)session.get(Address.class, theAddress.getAddressId());		// retrieves all of the address information using get
        	session.getTransaction().commit();														// Close that transaction
        	data.setStudentAddress(populatedAddress);												// set the populated address object

        	thePhones = listStudentPhone(data.getStudentId());										// populate the student phone information using HashSet
        	data.setStudentPhoneNumbers(thePhones);													// update the HashSet
        	theEmails = listEmailPhone(data.getStudentId());										// populate the email information
        	data.setStudentEmailAddresses(theEmails);												// Set the email information in the hashset
           	data.setStudentWebAddresses(listWebUsingStudentId(data.getStudentId()));				// Get the URLs that the user has
        	// Get the radio show information if there is some there...
        	data.setStudentRadioShows(listRadioShowUsingStudentId(data.getStudentId()));
        }			
		
		return(theList);
	}
	/**
	 * Gets the student name that begin with the search criteria
	 * @param nameStarts the string for the name
	 * @return a list of student records
	 */
	public static List<Student> searchNameStartsWith(String nameStarts) {
		Student theStudent = new Student();
		theStudent.setStudentName(nameStarts);							// Prepare to search for all students that begin with D 
		Example theExample = Example.create(theStudent);				// Create the example object
		theExample.enableLike(MatchMode.START);							// Have the search start at the beginning looking for the D etc...
		Address populatedAddress;
		Set<Phone> thePhones;
		Set<Email> theEmails;
		
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		List<Student> theList = new ArrayList<Student>();				// Create the array list for the Students
		session.beginTransaction();										// begin the transaction before we create criteria
		Criteria theCriteria = session.createCriteria(Student.class);	// create the criteria class
		theCriteria.add(theExample);									// Add the starts with search
		theList = theCriteria.list();									// call the list method
		session.getTransaction().commit();								// commit the transaction	
		// Because of lazy loading all of the sub objects are not populated
        for (Student data: theList) {
        	// We only have the address id if you try to get the rest of the address information you will get a lazy initialization exception			
        	Address theAddress = data.getStudentAddress();

        	session = sessionFactory.getCurrentSession();											
        	session.beginTransaction();
        	populatedAddress = (Address)session.get(Address.class, theAddress.getAddressId());		// retrieves all of the address information using get
        	session.getTransaction().commit();														// Close that transaction
        	data.setStudentAddress(populatedAddress);												// set the populated address object

        	thePhones = listStudentPhone(data.getStudentId());										// populate the student phone information using HashSet
        	data.setStudentPhoneNumbers(thePhones);													// update the HashSet
        	theEmails = listEmailPhone(data.getStudentId());										// populate the email information
        	data.setStudentEmailAddresses(theEmails);												// Set the email information in the hashset
        	
        }	
		return(theList);
	}
	
	/**
	 * We can be sure of getting a unique result if we use the ID to do the query
	 * @param studentId the id of the student
	 * @return the completely populated student object
	 */
	public static Student getCompleteStudentById(long studentId) {
		Student data = new Student();
		Address populatedAddress; Set<Phone> thePhones; Set<Email> theEmails;
		
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();			
		session = sessionFactory.getCurrentSession();
		session.beginTransaction();	
		data = (Student)session.get(Student.class,studentId);		// Get the student associated to the id

    	// We only have the address id if you try to get the rest of the address information you will get a lazy initialization exception			
    	Address theAddress = data.getStudentAddress();

    	session = sessionFactory.getCurrentSession();											
    	session.beginTransaction();
    	populatedAddress = (Address)session.get(Address.class, theAddress.getAddressId());		// retrieves all of the address information using get
    	session.getTransaction().commit();														// Close that transaction
    	data.setStudentAddress(populatedAddress);												// set the populated address object

    	thePhones = listStudentPhone(data.getStudentId());										// populate the student phone information using HashSet
    	data.setStudentPhoneNumbers(thePhones);													// update the HashSet
    	theEmails = listEmailPhone(data.getStudentId());										// populate the email information
    	data.setStudentEmailAddresses(theEmails);
    	data.setStudentWebAddresses(listWebUsingStudentId(data.getStudentId()));				// Set the web addresses as needed
    	// Get the radio show information if there is some there...
    	data.setStudentRadioShows(listRadioShowUsingStudentId(data.getStudentId()));
    	return(data);
	}
	/**
	 * Gets the student information using the student name.
	 * @param name the complete name of the person in the database
	 * @return a populated student object
	 */
	public static Student getCompleteStudentByName(String name) {
		Student theStudent = new Student();
		Student data;
		Address populatedAddress;
		Set<Phone> thePhones;
		Set<Email> theEmails;		
		theStudent.setStudentName(name);
		Example theExample = Example.create(theStudent);
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();	
		session.beginTransaction();																// begin the transaction before we create criteria
		
		Criteria theCriteria = session.createCriteria(Student.class);
		theCriteria.add(theExample);
		data = (Student)theCriteria.uniqueResult();
    	// We only have the address id if you try to get the rest of the address information you will get a lazy initialization exception			
    	Address theAddress = data.getStudentAddress();

    	session = sessionFactory.getCurrentSession();											
    	session.beginTransaction();
    	populatedAddress = (Address)session.get(Address.class, theAddress.getAddressId());		// retrieves all of the address information using get
    	session.getTransaction().commit();														// Close that transaction
    	data.setStudentAddress(populatedAddress);												// set the populated address object

    	thePhones = listStudentPhone(data.getStudentId());										// populate the student phone information using HashSet
    	data.setStudentPhoneNumbers(thePhones);													// update the HashSet
    	theEmails = listEmailPhone(data.getStudentId());										// populate the email information
    	data.setStudentEmailAddresses(theEmails);												// Set the email information in the hashset
		return(data);
	}
	/**
	 * Retrieves all of the student information including the nested objects like address, phone and email.  Because
	 * of lazy initialization we have to do a get for each address and we have to get the phone and email information seperately.
	 * @return List of completely populated student objects
	 */
	public static List<Student> listCompleteStudentInfo() {
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();		
		List<Student> theList = new ArrayList<Student>();				// Create the array list for the Students
		session.beginTransaction();										// begin the transaction before we create criteria
		Criteria theCriteria = session.createCriteria(Student.class);	// create the criteria class
		Order order = Order.asc("studentName");							// We will order the list by name
		theCriteria.addOrder(order);									// Add the order to the criteria object
		theList = theCriteria.list();									// call the list method
		session.getTransaction().commit();	
		Address populatedAddress;
		Set<Phone> thePhones;
		Set<Email> theEmails;
		// Because of lazy loading all of the sub objects are not populated
        for (Student data: theList) {
        	// We only have the address id if you try to get the rest of the address information you will get a lazy initialization exception			
        	Address theAddress = data.getStudentAddress();

        	session = sessionFactory.getCurrentSession();											
        	session.beginTransaction();
        	populatedAddress = (Address)session.get(Address.class, theAddress.getAddressId());		// retrieves all of the address information using get
        	session.getTransaction().commit();														// Close that transaction
        	data.setStudentAddress(populatedAddress);												// set the populated address object

        	thePhones = listStudentPhone(data.getStudentId());										// populate the student phone information using HashSet
        	data.setStudentPhoneNumbers(thePhones);													// update the HashSet
        	theEmails = listEmailPhone(data.getStudentId());										// populate the email information
        	data.setStudentEmailAddresses(theEmails);												// Set the email information in the hashset
        	data.setStudentWebAddresses(listWebUsingStudentId(data.getStudentId()));				// Get the URLs that the user has
        	// Get the radio show information if there is some there...
        	data.setStudentRadioShows(listRadioShowUsingStudentId(data.getStudentId()));        	
        }	
        return(theList);					
	}
	/**
	 * Retrieves all of the student information including the nested objects like address, phone and email.  Because
	 * of lazy initialization we have to do a get for each address and we have to get the phone and email information seperately.
	 * @param CurrentSort is an object that contains sort related information
	 * @return List of completely populated student objects
	 */
	public static List<Student> listCompleteStudentInfoWithSort(CurrentSort theSort) {
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();		
		List<Student> theList = new ArrayList<Student>();				// Create the array list for the Students
		session.beginTransaction();										// begin the transaction before we create criteria
		Criteria theCriteria = session.createCriteria(Student.class);	// create the criteria class
		Order order = Order.asc("studentName");							// set the default sort to ascending
		if (theSort.getCurrentSort().equals(Constants.SORT_ASC)) {		// check for an ascending sort passed
			order = Order.asc("studentName");							// set the order object to ascending
		}
		else {
			order = Order.desc("studentName");							// set the order object to descending
		}
			
		theCriteria.addOrder(order);									// Add the order to the criteria object
		theList = theCriteria.list();									// call the list method
		session.getTransaction().commit();	
		Address populatedAddress;
		Set<Phone> thePhones;
		Set<Email> theEmails;
		// Because of lazy loading all of the sub objects are not populated
        for (Student data: theList) {
        	// We only have the address id if you try to get the rest of the address information you will get a lazy initialization exception			
        	Address theAddress = data.getStudentAddress();

        	session = sessionFactory.getCurrentSession();											
        	session.beginTransaction();
        	populatedAddress = (Address)session.get(Address.class, theAddress.getAddressId());		// retrieves all of the address information using get
        	session.getTransaction().commit();														// Close that transaction
        	data.setStudentAddress(populatedAddress);												// set the populated address object

        	thePhones = listStudentPhone(data.getStudentId());										// populate the student phone information using HashSet
        	data.setStudentPhoneNumbers(thePhones);													// update the HashSet
        	theEmails = listEmailPhone(data.getStudentId());										// populate the email information
        	data.setStudentEmailAddresses(theEmails);												// Set the email information in the hashset
        	data.setStudentWebAddresses(listWebUsingStudentId(data.getStudentId()));
        	// Get the radio show information if there is some there...
        	data.setStudentRadioShows(listRadioShowUsingStudentId(data.getStudentId()));        	
        	
        }	
        return(theList);					
	}	
	/**
	 * Displays all of the objects and sub objects in a list of student data
	 * @param theList the completely populated list of student data including the subject objects 
	 */
	public static void displayStudentData(List<Student> theList) {
		Set<Phone> thePhones;  Set<Email> theEmails;
        for (Student data: theList) { 
        	System.err.println(" data.getStudentName() " + data.getStudentName());
        	Address theAddress = data.getStudentAddress();
        	System.err.println("theAddress.getStreet() " + theAddress.getStreet());  
        	System.err.println("theAddress.getCity() " + theAddress.getCity()); 
        	System.err.println("theAddress.getState() " + theAddress.getState()); 
        	System.err.println("theAddress.getZipcode() " + theAddress.getZipcode()); 
        	thePhones = data.getStudentPhoneNumbers();
        	for (Phone p: thePhones) {
        		System.err.println("p.getPhoneNumber() " + p.getPhoneNumber()); 
        		System.err.println("p.getPhoneType() " + p.getPhoneType()); 
        	}
        	theEmails = data.getStudentEmailAddresses();
        	for (Email e: theEmails) {
        		System.err.println("p.getEmailAddr() " + e.getEmailAddr()); 
        		System.err.println("p.getEmailType() " + e.getEmailType()); 
        	}        	
        }		
	}

	/**
	 * Displays the information for a single student
	 * @param data the student object
	 */
	public static void displaySingleStudent(Student data) {
		Set<Phone> thePhones;  Set<Email> theEmails;
		System.err.println(" data.getStudentId() " + data.getStudentId());
    	System.err.println(" data.getStudentName() " + data.getStudentName());
    	Address theAddress = data.getStudentAddress();
    	System.err.println("theAddress.getStreet() " + theAddress.getStreet());  
    	System.err.println("theAddress.getCity() " + theAddress.getCity()); 
    	System.err.println("theAddress.getState() " + theAddress.getState()); 
    	System.err.println("theAddress.getZipcode() " + theAddress.getZipcode()); 
    	thePhones = data.getStudentPhoneNumbers();
    	for (Phone p: thePhones) {
    		System.err.println("p.getPhoneNumber() " + p.getPhoneNumber()); 
    		System.err.println("p.getPhoneType() " + p.getPhoneType()); 
    	}
    	theEmails = data.getStudentEmailAddresses();
    	for (Email e: theEmails) {
    		System.err.println("p.getEmailAddr() " + e.getEmailAddr()); 
    		System.err.println("p.getEmailType() " + e.getEmailType()); 
    	}   	
	}
	/**
	 * Gets the phone objects associated to a specific student
	 * @param studentId the student id of the student
	 * @return a HashSet of phone objects
	 */
	public static Set<Phone> listStudentPhone(long studentId) {
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();		
		List<StudentPhone> theList = new ArrayList<StudentPhone>();				// Create the array list for the StudentPhone object
		session.beginTransaction();												// begin the transaction before we create criteria
		Criteria theCriteria = session.createCriteria(StudentPhone.class);		// create the criteria class
		theList = theCriteria.list();											// call the list method
		session.getTransaction().commit();		
		Set<Phone> desiredStudentPhoneSet = new HashSet<Phone>();				// Create a smaller list for just the phones that match the studentId / phone Id
		Phone thePhone;
		
		for (StudentPhone sp: theList) {										// loop through all of the student phones
			if (sp.getStudentId() == studentId) {								// check for match on student id
				session = sessionFactory.getCurrentSession();
				session.beginTransaction();										// begin a transaction
				thePhone = (Phone)session.get(Phone.class, sp.getPhoneId());	// Get the desired phone record using the corresponding phone id
				session.getTransaction().commit();								// Close that transaction
				desiredStudentPhoneSet.add(thePhone);
			}
		}
		return(desiredStudentPhoneSet);
	}
	

	/**
	 * Gets the email objects associated to a specific student
	 * @param studentId the student id of the student
	 * @return a email of phone objects
	 */
	public static Set<Email> listEmailPhone(long studentId) {
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		Session session = sessionFactory.getCurrentSession();		
		List<StudentEmail> theList = new ArrayList<StudentEmail>();				// Create the array list for the StudentPhone object
		session.beginTransaction();												// begin the transaction before we create criteria
		Criteria theCriteria = session.createCriteria(StudentEmail.class);		// create the criteria class
		theList = theCriteria.list();											// call the list method
		session.getTransaction().commit();		
		Set<Email> desiredStudentEmailSet = new HashSet<Email>();				// Create a smaller list for just the phones that match the studentId / phone Id
		Email theEmail;
		
		for (StudentEmail sp: theList) {										// loop through all of the student phones
			if (sp.getStudentId() == studentId) {								// check for match on student id
				session = sessionFactory.getCurrentSession();
				session.beginTransaction();										// begin a transaction
				theEmail = (Email)session.get(Email.class, sp.getEmailId());	// Get the desired Email record using the corresponding Email id
				session.getTransaction().commit();								// Close that transaction
				desiredStudentEmailSet.add(theEmail);
			}
		}
		return(desiredStudentEmailSet);
	}
	

	/**
	 * Add the Student object to the Student table also saves the student, the associated phone numbers, address etc.
	 * There is an embedded address object in the student and a set of phone numbers
	 * @param session the hibernate session
	 * @param theStudent the Student object
	 */
	public static void addStudent(Session session, Student theStudent) {
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();	// Start the transaction
			session.save(theStudent);					// Saves the student, Associated phone numbers, address etc.								
			transaction.commit();						// Commits the transaction
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} 
	 
	}
	
	/**
	 * Add the Student object to the Student table also saves the student, the associated phone numbers, address etc.
	 * There is an embedded address object in the student and a set of phone numbers
	 * @param session the hibernate session
	 * @param theStudent the Student object
	 */
	public static void saveStudent(Student theStudent) throws HibernateException { 
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();		
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();	// Start the transaction
			session.saveOrUpdate(theStudent);			// Saves the student, Associated phone numbers, address etc.								
			transaction.commit();						// Commits the transaction
		} catch (HibernateException e) {
			transaction.rollback();						// roll back the transaction
			e.printStackTrace();
			throw new HibernateException(e);
		} 
	 
	}
	
	/**
	 * Selects all of the people / students from the database
	 * @return a list of Student objects
	 */
	public static List<Student> listStudents() {
		org.hibernate.SessionFactory sessionFactory = HibernateUtil.getSessionFactory();	
		List<Student> theList = new ArrayList<Student>();
		theList = listStudents(sessionFactory.getCurrentSession());
		return(theList);
	}
	/**
	 * Selects a list of Students from the database
	 * @param session the hibernate session
	 * @return a list of Student objects
	 */
	public static List<Student> listStudents(Session session)  {
		List<Student> theList = new ArrayList<Student>();				// Create the array list for the Students
		session.beginTransaction();										// begin the transaction before we create criteria
		Criteria theCriteria = session.createCriteria(Student.class);	// create the criteria class
		theList = theCriteria.list();									// call the list method
		session.getTransaction().commit();								// commit the transaction
		return(theList);												// return the list
	}
	/**
	 * Deletes a student record from the database
	 * @param session the hibernate session
	 */
	public static void deleteStudent(Student theStudent) {
		org.hibernate.SessionFactory sessionFactory  = sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();										// begin the transaction before we delete
		session.delete(theStudent);										// delete the student record
		session.getTransaction().commit();								// commit the transaction
	}	
	
	public static void updateStudent(Session session, Student theStudent) {
		session.beginTransaction();											// begin the transaction before we do anything else
		session.saveOrUpdate(theStudent);									// update the event
		session.getTransaction().commit();									// Commit the transaction			
	}	
}
