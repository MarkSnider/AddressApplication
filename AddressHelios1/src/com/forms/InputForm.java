/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.forms;

import java.util.ArrayList;
import java.util.List;

import com.dao.Student;
import com.dao.StudentType;

/**
 * This is the first form used during startup of the address application
 * @author Mark Snider
 */
public class InputForm extends org.apache.struts.action.ActionForm {
   
    private ArrayList<Student> studentList;		// List of all of ths students / people that are being used by the address application

    private String StudentId;
    private String SelectedFilterItem;
    private String SaveEvent;
//    private List<StudentType> studentTypes;
    


	// Used by the edit page
    private Student singleStudent;
    
    // New Attributes used by the edit page
    
	public InputForm() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	// Get a single student on the edit page
	public Student getSingleStudent() {
		return singleStudent;
	}

	public void setSingleStudent(Student singleStudent) {
		this.singleStudent = singleStudent;
	}


	
	public String getSaveEvent() {
		return SaveEvent;
	}

	public void setSaveEvent(String saveEvent) {
		SaveEvent = saveEvent;
	}



	/**
	 * This will me set when the user selected an item in the filter list.
	 * @return
	 */
    public String getSelectedFilterItem() {
		return SelectedFilterItem;
	}
    
	/**
	 * This will me set when the user selected an item in the filter list.
	 * @return
	 */
	public void setSelectedFilterItem(String selectedFilterItem) {
		SelectedFilterItem = selectedFilterItem;
	}	
	
    /**
     * List of students / people in the address database
     * @return Students are actually the people that are in the address database
     */    
	public ArrayList<Student> getStudentList() {
		return studentList;
	}
    /**
     * List of students / people in the address database
     * @param Students are actually the people that are in the address database
     */    
	public void setStudentList(ArrayList<Student> studentList) {
		this.studentList = studentList;
	}

	private String startsWith;  					// used for searching for names that start with some string
	private String selectedMatchMode;				// the one the user selected in the list
	private String contact251InfoLike;				// search the contact notes for contact 251

	
	/**
	 * Used for searching for names that start with some string
	 * @return the letters that are the search criteria
	 */
	public String getStartsWith() {
		return startsWith;
	}
	

	/**
	 * Used for searching for names that start with some string
	 * @return the letters that are the search criteria
	 */	public void setStartsWith(String startsWith) {
		this.startsWith = startsWith;
	}


	public String getSelectedMatchMode() {
		return selectedMatchMode;
	}


	public void setSelectedMatchMode(String selectedMatchMode) {
		this.selectedMatchMode = selectedMatchMode;
	}

	/**
	 * search the contact notes for contact 251.  Searching for text that is like what is passed
	 * @return return the text for the like query...
	 */
	public String getContact251InfoLike() {
		return contact251InfoLike;
	}
	
	/**
	 * search the contact notes for contact 251.  Searching for text that is like what is passed
	 * @param return the text for the like query...
	 */
	public void setContact251InfoLike(String contact251InfoLike) {
		this.contact251InfoLike = contact251InfoLike;
	}

	public void setStudentId(String studentId) {
		StudentId = studentId;
	}

	public String getStudentId() {
		return StudentId;
	}
//    public List<StudentType> getStudentTypes() {
//		return studentTypes;
//	}
//
//	public void setStudentTypes(List<StudentType> studentTypes) {
//		this.studentTypes = studentTypes;
//	}
    
}

