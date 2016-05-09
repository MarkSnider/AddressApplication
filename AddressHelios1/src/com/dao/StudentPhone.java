package com.dao;

public class StudentPhone implements java.io.Serializable {
	
	private long phoneId;
	private long studentId;
	
	public long getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(long phoneId) {
		this.phoneId = phoneId;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	

}
