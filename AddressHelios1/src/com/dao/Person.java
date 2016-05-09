package com.dao;

import java.util.ArrayList;
import java.util.Iterator;



/**
 * All of the attributes that are shown on the first page of contact information
 * @author User
 *
 */
public class Person {
	
	/** The first name of the contact */
	private String firstName;
	
	/** The middle name of the contact */
	private String middleName;
	
	/** The last name of the contact */
	private String lastName;
	
	/** The complete name of the customer */
	private String fullName;
	

	
	/** The first street which could be the first of two or more */
	private String streetName;
	
	/** The city of the user */
	private String city;
	
	/** The state of the user */
	private String state;
	
	/** the zip code of the individual */
	private String zip;
	

	/** This is just a string used to display the multiple emails */
	private String displayEmails;
	


	/**
	 * The person that appears as a record in the list of address contacts
	 * @param fName first name
	 * @param mName middle name 
	 * @param lName last name
	 */
	public Person(String fName, String mName, String lName) {
		firstName=fName;
		middleName=mName;
		lastName=lName;
	}

	/**
	 * The first name of the contact
	 * @return The first name of the contact
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * The first name of the contact
	 * @return The first name of the contact
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * The middle name of the contact
	 * @return The middle name of the contact
	 */
	public String getMiddleName() {
		return middleName;
	}
	/**
	 * The middle name of the contact
	 * @return The middle name of the contact
	 */
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	/**
	 * The last name of the contact
	 * @return The last name of the contact
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * The last name of the contact
	 * @return The last name of the contact
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * The complete name of the customer
	 * @return The complete name of the customer starting with last name
	 */
	public String getFullName() {
		fullName = lastName + "," + firstName + " " + middleName;
		return fullName;
	}
	/**
	 * The complete name of the customer
	 * @return The complete name of the customer starting with last name
	 */	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	/**
	 * The first street which could be the first of two or more
	 * @return The first street which could be the first of two or more
	 */
	public String getStreetName() {
		return streetName;
	}
	/**
	 * The first street which could be the first of two or more
	 * @return The first street which could be the first of two or more
	 */	
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	/**
	 * Overridden for display purposes
	 */
	public String toString() {
		return(lastName);
	}

	/**
	 * The city of the user
	 * @return The city of the user
	 */
	public String getCity() {
		return city;
	}
	/**
	 * The city of the user
	 * @param The city of the user
	 */	
	public void setCity(String theCity) {
		this.city = theCity;
	}

	/**
	 *  The state of the user 
	 * @return  The state of the user 
	 */
	public String getState() {
		return state;
	}
	
	/**
	 *  The state of the user 
	 * @param  The state of the user 
	 */	
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * the zip code of the individual
	 * @return the zip code of the individual
	 */
	public String getZip() {
		return zip;
	}
	
	/**
	 * the zip code of the individual
	 * @param the zip code of the individual
	 */	
	public void setZip(String zip) {
		this.zip = zip;
	}
	


}
