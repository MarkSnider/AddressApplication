package com.dao;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.forms.EditForm;

/**
 * This class contains student details.
 */
public class Student implements java.io.Serializable {

	private long studentId;
	private String studentName;
	private Address studentAddress;
	private Set<Phone> studentPhoneNumbers = new HashSet<Phone>(0);				// multiple phone numbers
	private Set<Email> studentEmailAddresses = new HashSet<Email>(0);			// Multiple emails
	private Set<Web> studentWebAddresses = new HashSet<Web>(0);					// For the multiple URLs for each student
	private Set<RadioShow> studentRadioShows = new HashSet<RadioShow>(0);		// For the multiple RadioShows for students with shows
	
	private String addressInfo;
	private String phoneInfo;
	private String emailInfo;
	
	// This actually reflects a column in the database.  It can be very large amount of information
	private String studentInfo;		
	
	// This is only the first 100 characters of the student Information which can be rather long
	private String studentInfoSmall;
	
	private String studentType;
	
	private String State;
	private String City;
	private long AddressId;
	private String addressStreet;
	private String Street;
	private String country;
	private String zip;


	/**
	 * Check if this is rather large and if so just return the first 100 characters of studentInfo
	 * @return
	 */
	public String getStudentInfoSmall() {
		
		// Get the first 100 characters of student information
		if (studentInfo != null && studentInfo.isEmpty() != true && studentInfo.length() > 100) {
			
			// Get the first 100 characters and add the ellipses to indicate that there is more information
			studentInfoSmall = studentInfo.substring(0, 100) + "...";
		}
		else {
			studentInfoSmall = studentInfo;
		}
		return studentInfoSmall;
	}

	public void setStudentInfoSmall(String studentInfoSmall) {
		this.studentInfoSmall = studentInfoSmall;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	// Formatted html link for the address information
	private String emailHtmlLinks;
	
	// The first URL in the hashset of URLs
	private String url;

	// The first email in the HashSet of email
	private String email;
	
	public Student() {
	}

	public Student(String studentName, Address studentAddress, Set<Phone> studentPhoneNumbers, 
			Set<Email> studentEmailAddresses, Set<Web> studentWeb, Set<RadioShow> sRadioShows) {
		this.studentName = studentName;
		this.studentAddress = studentAddress;
		this.studentPhoneNumbers = studentPhoneNumbers;
		this.studentEmailAddresses = studentEmailAddresses;
		this.studentWebAddresses = studentWeb;
		this.studentRadioShows = sRadioShows;
	}
	
	public Student(String studentName, Address studentAddress, Set<Phone> studentPhoneNumbers, Set<Email> studentEmailAddresses, Set<Web> studentWeb) {
		this.studentName = studentName;
		this.studentAddress = studentAddress;
		this.studentPhoneNumbers = studentPhoneNumbers;
		this.studentEmailAddresses = studentEmailAddresses;
		this.studentWebAddresses = studentWeb;
	}
	
	public Student(String studentName, Address studentAddress, Set<Phone> studentPhoneNumbers, Set<Email> studentEmailAddresses) {
		this.studentName = studentName;
		this.studentAddress = studentAddress;
		this.studentPhoneNumbers = studentPhoneNumbers;
		this.studentEmailAddresses = studentEmailAddresses;
	}
	
	public Student(String studentName, Address studentAddress, Set<Phone> studentPhoneNumbers) {
		this.studentName = studentName;
		this.studentAddress = studentAddress;
		this.studentPhoneNumbers = studentPhoneNumbers;
	}
	
	public Student(String studentName,  Set<Phone> studentPhoneNumbers) {
		this.studentName = studentName;
		this.studentPhoneNumbers = studentPhoneNumbers;
	}
	
	public Student(String studentName, Address studentAddress) {
		this.studentName = studentName;
		this.studentAddress = studentAddress;
	}
	
	public Set<Web> getStudentWebAddresses() {
		return studentWebAddresses;
	}

	public void setStudentWebAddresses(Set<Web> studentWebAddresses) {
		this.studentWebAddresses = studentWebAddresses;
	}
	
	public Set<Email> getStudentEmailAddresses() {
		return studentEmailAddresses;
	}

	public void setStudentEmailAddresses(Set<Email> studentEmailAddresses) {
		this.studentEmailAddresses = studentEmailAddresses;
	}
	
	public Set<Phone> getStudentPhoneNumbers() {
		return this.studentPhoneNumbers;
	}

	public void setStudentPhoneNumbers(Set<Phone> studentPhoneNumbers) {
		this.studentPhoneNumbers = studentPhoneNumbers;
	}
	public long getStudentId() {
		return this.studentId;
	}
	

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return this.studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public Address getStudentAddress() {
		return this.studentAddress;
	}

	public void setStudentAddress(Address studentAddress) {
		this.studentAddress = studentAddress;
	}

	/**
	 * Called by the display that seperates things into pages
	 * @return the address information concatenated together.
	 */
	public String getAddressInfo() {
		addressInfo=studentAddress.getStreet() + " " + studentAddress.getCity() + "," + studentAddress.getState() + " " + studentAddress.getZipcode();
		return addressInfo;
	}

	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}
	
	
    /**
     * The home and work phone are concatenated together and return as a string
     * @param thePhoneSet	the set of phone numbers
     * @return	home and work phone are concatenated
     */
    String createPhoneInfo(Set<Phone> thePhoneSet) {
    	String data="";
    	Iterator<Phone> it = thePhoneSet.iterator();							// Get the iterator
    	while(it.hasNext()) {													// loop through the Phone  set
    		Phone currentPhone = it.next();										// get the Phone object out of the iterator
    		if (currentPhone.getPhoneType().toUpperCase().startsWith("H")) { 	// check if its a work Phone
    			data="Home ";
    			data += currentPhone.getPhoneNumber();
    			data += " ";
    		}
    		else {
    			
    			data="Work ";
    			data += currentPhone.getPhoneNumber();
    			data += " ";
    		}
    	}
    	return(data);
    }
    /**
     * The home and work email are concatenated together and return as a string
     * @param theEmailSet	the set of phone numbers
     * @return	home and work phone are concatenated
     */
    String createEmailInfo(Set<Email> theEmailSet) {
    	String data="";
    	Iterator<Email> it = theEmailSet.iterator();							// Get the iterator
    	while(it.hasNext()) {													// loop through the email address set
    		Email currentEmail = it.next();										// get the email object out of the iterator
    		if (currentEmail.getEmailType().startsWith("W")) { 	// check if its a work email
    			data="Work ";
    			data += currentEmail.getEmailAddr();
    			data += " ";
    		}
    		else {
    			
    			data="Home ";
    			data += currentEmail.getEmailAddr();
    			data += " ";
    		}
    	}
    	return(data);
    }
    /**
     * Called from the display that separates things into pages
     * @return   home and work phone are concatenated together
     */
	public String getPhoneInfo() {
		phoneInfo=createPhoneInfo(this.getStudentPhoneNumbers());
		return phoneInfo;
	}

	public void setPhoneInfo(String phoneInfo) {
		this.phoneInfo = phoneInfo;
	}
    /**
     * Called from the display that separates things into pages
     * @return   home and work email are concatenated together
     */
	public String getEmailInfo() {
		emailInfo=createEmailInfo(this.getStudentEmailAddresses());
		return emailInfo;
	}

	public void setEmailInfo(String emailInfo) {
		this.emailInfo = emailInfo;
	}

	/**
	 * Maps to a column in the database that stores general information about the person
	 * @return the notes on the person.
	 */
	public String getStudentInfo() {
		return studentInfo;
	}
	
	/**
	 * Maps to a column in the database that stores general information about the person
	 * @param the notes on the person.
	 */
	public void setStudentInfo(String studentInfo) {
		this.studentInfo = studentInfo;
	} 	
	
	
	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}

	public Set<RadioShow> getStudentRadioShows() {
		return studentRadioShows;
	}

	public void setStudentRadioShows(Set<RadioShow> studentRadioShows) {
		this.studentRadioShows = studentRadioShows;
	}	

	/**
	 * Creates the email links using the set of email address
	 * @return a string containing the html for a link or two links
	 */
	public String getEmailHtmlLinks() {
		if (studentEmailAddresses != null) {
			// Get the array of email address
			Object[] theEmails = studentEmailAddresses.toArray();
			String link ="";
			boolean found = false;
			// loop through the object array but just use the first email
			for (Object current: theEmails) {
				
				// Get the email object
				Email theEmail = (Email)current;
				
				// Attempt to check that we have a real email 
				if (theEmail != null && theEmail.getEmailAddr() != null && theEmail.getEmailAddr().length() > 1) {
					
					// add the email address;
					link = theEmail.getEmailAddr();
					
					// get out of loop
					break;
					
				}
				
			}
			// set the return value
			emailHtmlLinks=link;
		}
		return emailHtmlLinks;
	}
	/**
	 * Creates the email links using the set of email address
	 * @param a string containing the html for a link or two links
	 */
	public void setEmailHtmlLinks(String emailHtmlLinks) {
		this.emailHtmlLinks = emailHtmlLinks;
	}

	/**
	 * The first URL in the HashSet of URLs
	 * @return just returns the first URL for display purposes
	 */
	public String getUrl() {
		
		String urlValue="";
		url=urlValue;
		// Make sure that we actually have some web addresses
		if (studentWebAddresses != null && studentWebAddresses.size() > 0) {
			
			// Get an iterator
			Iterator<Web> it = studentWebAddresses.iterator();
			
			// Loop through the address
			while(it.hasNext()) {
				
				// Get the URL object
				Web currentUrl = it.next();
				
				// Make sure we have a good URL object
				if (currentUrl != null && currentUrl.getUrl() != null) {
					
					// Get the actual string
					urlValue = currentUrl.getUrl();
					
					// Make sure it has length
					if (urlValue.length() > 0) {
						
						// Get out of the loop
						break;
					}
				}
			}
			
			// Get the actual URL at of the web object
			url= urlValue;
		}

		return url;
	}
	/**
	 * The first URL in the HashSet of URLs
	 * @param just the first URL for display purposes
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * The first email in the HashSet of email
	 * @return The email address
	 */
	public String getEmail() {
		
		String emailValue="";
		email=emailValue;
		// Make sure that we actually have some web addresses
		if (studentEmailAddresses != null && studentEmailAddresses.size() > 0) {
			
			// Get an iterator
			Iterator<Email> it = studentEmailAddresses.iterator();
			
			// Loop through the address
			while(it.hasNext()) {
				
				// Get the URL object
				Email current = it.next();
				
				// Make sure we have a good URL object
				if (current != null && current.getEmailAddr() != null) {
					
					// Get the actual string
					emailValue = current.getEmailAddr();
					
					// Make sure it has length and get the blanks off there
					if (emailValue.trim().length() > 0) {
						
						// Get out of the loop
						break;
					}
				}
			}
			
			// Get the actual URL at of the web object
			email= emailValue;
		}		
		return email;
	}

	/**
	 * The first email in the HashSet of email
	 * @param The email address
	 */	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName="
				+ studentName + ", studentAddress=" + studentAddress
				+ ", studentPhoneNumbers=" + studentPhoneNumbers
				+ ", studentEmailAddresses=" + studentEmailAddresses
				+ ", studentWebAddresses=" + studentWebAddresses
				+ ", studentRadioShows=" + studentRadioShows + ", addressInfo="
				+ addressInfo + ", phoneInfo=" + phoneInfo + ", emailInfo="
				+ emailInfo + ", studentInfo=" + studentInfo + ", studentType="
				+ studentType + ", State=" + State + ", City=" + City
				+ ", AddressId=" + AddressId + ", emailHtmlLinks="
				+ emailHtmlLinks + ", url=" + url + ", email=" + email + "]";
	}

	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}	
	
	public long getAddressId() {
		return AddressId;
	}

	public void setAddressId(long addressId) {
		AddressId = addressId;
	}	
	
	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getStreet() {
		return Street;
	}

	public void setStreet(String street) {
		Street = street;
	}	
	
	
}

