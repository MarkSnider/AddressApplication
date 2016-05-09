package com.dao;

/**
 * student types: Family, friend, radio show, stamper
 * @author User
 *
 */
public class StudentType implements java.io.Serializable {
	
	private long studenttypeid;
	
	private String studentType;
	
	private String selected;

	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}

	public long getStudenttypeid() {
		return studenttypeid;
	}

	public void setStudenttypeid(long studenttypeid) {
		this.studenttypeid = studenttypeid;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	
}
