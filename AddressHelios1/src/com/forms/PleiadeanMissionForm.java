package com.forms;

public class PleiadeanMissionForm extends org.apache.struts.action.ActionForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String searchText;

	/**
	 * User enters search text in the text field and we search with a like clause
	 * @return search string
	 */
	public String getSearchText() {
		return searchText;
	}
	/**
	 * User enters search text in the text field and we search with a like clause
	 * @param search string
	 */
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}


}
