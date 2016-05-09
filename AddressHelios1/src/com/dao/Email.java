package com.dao;

import java.util.ArrayList;

/**
 * This class contains student's email details
 */

public class Email implements java.io.Serializable {

	private long emailId;
	private String emailType;
	private String emailAddr;
	
	public Email() {
	}

	public Email(String emailType, String emailAddr) {
		this.emailType = emailType;
		this.emailAddr = emailAddr;
	}
	public Email(String emailType, String emailAddr, long emailId) {
		this.emailType = emailType;
		this.emailAddr = emailAddr;
		this.emailId=emailId;
	}	
	
	public long getEmailId() {
		return emailId;
	}
	public void setEmailId(long emailId) {
		this.emailId = emailId;
	}
	public String getEmailType() {
		return emailType;
	}
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}
	public String getEmailAddr() {
		return emailAddr;
	}
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	/**
	 * overridden so that in the JSP we can display this information easily
	 */
	public String toString() {
		String emailInformation=getEmailAddr();
		
		if (emailInformation == null || emailInformation.equals("?")) {
			emailInformation="";
		}
		

		return(emailInformation);
	}	


}
