package com.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;



/**
 * Search form for searching information in contact 251
 * @author mc28881
 *
 */
public class SearchContact251Form extends org.apache.struts.action.ActionForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// User enters search text in the text field and we search with a like clause
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
	
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
//    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
//        ActionErrors errors = new ActionErrors();
//        
//        if (searchText == null || searchText.length() < 1) {
//        	
//            errors.add("searchText", new ActionMessage("error.searchText.required"));
//        
//        }
//                
//        return errors;
//    }	

}
