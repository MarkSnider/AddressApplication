package com.dao;

import java.util.ArrayList;


/**
 * This class contains student's phone number
 * 			details.
 */
public class Phone implements java.io.Serializable {

	private long phoneId;
	private String phoneType;
	private String phoneNumber;

	public Phone() {
	}

	public Phone(String phoneType, String phoneNumber) {
		this.phoneType = phoneType;
		this.phoneNumber = phoneNumber;
	}
	
	public long getPhoneId() {
		return this.phoneId;
	}

	public void setPhoneId(long phoneId) {
		this.phoneId = phoneId;
	}

	public String getPhoneType() {
		return this.phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * overridden so that in the JSP we can display this information easily
	 */
	public String toString() {
		String phoneInformation;
		phoneInformation= getPhoneNumber();
		if (phoneInformation == null || phoneInformation.equals("?")) {
			phoneInformation="";
		}
		return(phoneInformation);
	}
	
/**
 * Note that STUDENT_PHONE table's PHONE_ID had to also be int(32) and
 * PHONE_ID had to have an index on it as well.  Or we could not make the foreign key that pointed
 * to the STUDENT_PHONE Table
 * 
 */
	
	/*         CREATE TABLE  `employees`.`phone` (
  `PHONE_ID` int(32) NOT NULL auto_increment,
  `PHONE_TYPE` varchar(10) default NULL,
  `PHONE_NUMBER` varchar(15) default NULL,
  PRIMARY KEY  (`PHONE_ID`),
CONSTRAINT fk_PerStudentPhone FOREIGN KEY (PHONE_ID)
REFERENCES student_phone(PHONE_ID)) ENGINE=InnoDB DEFAULT CHARSET=latin1; */		

}

