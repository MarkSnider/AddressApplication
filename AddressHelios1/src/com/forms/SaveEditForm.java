package com.forms;

import java.util.ArrayList;
import java.util.List;

import com.dao.Student;
import com.dao.StudentType;

public class SaveEditForm extends org.apache.struts.action.ActionForm  {
	
	// Used by the edit page
    private Student singleStudent;	
	private String name;
	private String SaveEvent;
	private String studentType;
	private String street;
	private String city;
	private String state;
	private String country;
	private String zip;
	
	// This can be ADD or EDIT depending on what is happening.
	private String currentSaveAction;

	
	public String getCurrentSaveAction() {
		return currentSaveAction;
	}

	public void setCurrentSaveAction(String currentSaveAction) {
		this.currentSaveAction = currentSaveAction;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	List<StudentType> typesList = new ArrayList<StudentType>();
	
	public List<StudentType> getTypesList() {
		return typesList;
	}

	public void setTypesList(List<StudentType> typesList) {
		this.typesList = typesList;
	}

	public String getSaveEvent() {
		return SaveEvent;
	}

	public void setSaveEvent(String saveEvent) {
		SaveEvent = saveEvent;
	}	
	
	// Get a single student on the edit page
	public Student getSingleStudent() {
		return singleStudent;
	}

	public void setSingleStudent(Student singleStudent) {
		this.singleStudent = singleStudent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}	
	
	
}
