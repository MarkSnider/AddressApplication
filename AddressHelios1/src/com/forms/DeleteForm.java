package com.forms;

public class DeleteForm extends org.apache.struts.action.ActionForm {
	
	private String name;
	private long studentId;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	
	

}
