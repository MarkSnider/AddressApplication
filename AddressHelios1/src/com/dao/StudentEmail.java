package com.dao;

public class StudentEmail implements java.io.Serializable {

	private long emailId;
	private long studentId;
//	private long EMAIL_ID;

//	public long getEMAIL_ID() {
//		return EMAIL_ID;
//	}
//	public void setEMAIL_ID(long eMAIL_ID) {
//		EMAIL_ID = eMAIL_ID;
//	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	public long getEmailId() {
		return emailId;
	}
	public void setEmailId(long emailId) {
		this.emailId = emailId;
	}

	
	
}
