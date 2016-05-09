package com.dao;

/**
 * Class is for the STUDENT_RADIOSHOW table
 * @author Mark Snider
 *
 */
public class StudentRadioShow implements java.io.Serializable{
	
	private long radioShowId;
	private long studentId;
	
	public long getRadioShowId() {
		return radioShowId;
	}
	public void setRadioShowId(long radioShowId) {
		this.radioShowId = radioShowId;
	}
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	
	

}
