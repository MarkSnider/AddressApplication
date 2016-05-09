package com.forms;

import java.util.ArrayList;
import java.util.List;

import com.dao.MatchModeData;

/**
 * This class backs the Search.JSP.  It gets the selected search match mode the user has selected
 * on the screen in the drop down and gets the name related information that we are searching for.
 * @author Mark Snider
 *
 */
public class SearchForm extends org.apache.struts.action.ActionForm {
	
	private String startsWith;  					// used for searching for names that start with some string
	private String selectedMatchMode;				// the one the user selected in the list
	private String studentType;
	
	public String getStudentType() {
		return studentType;
	}


	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}


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



}
