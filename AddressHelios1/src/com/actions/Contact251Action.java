package com.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.dao.Contact251;
import com.forms.SearchContact251Form;
import com.service.Contact251Service;


public class Contact251Action  extends DispatchAction {
	
	
	/**
	 * Select and display all of the data for contact 251
	 */
    public ActionForward displayAll(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	// Select all of the data for contact 251
    	List allData = Contact251Service.selectAll();
		
		// Place the information into the request so we can display it in the jsp
		request.setAttribute("datalist", allData);
		
    	ActionForward forward = mapping.findForward("success");
    	
    	return(forward);
    	
    }	
    
	/**
	 * Called to first display the search form... All of the data displays initially
	 */
    public ActionForward displayLike(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	// create the session object
    	HttpSession theSession = request.getSession(true);	
    	
    	// Construct the Contact251 form
    	SearchContact251Form theSearchForm = new SearchContact251Form();
    	
    	// Construct any empty list to keep from getting the null pointer error
    	List allData = new ArrayList();
    	
    	// check if the form that is coming off the request is null then its the first time in
    	if (form == null) {
    		// Place the search form in the session
    		theSession.setAttribute("SearchContact251Form", theSearchForm);
    	}
    	else {
        	// Get the parameter off of the request url
        	String text = request.getParameter("searchtext");
        	
        	
        	// Select all of the data for contact 251 like what is passed
        	allData = Contact251Service.selectInfoLike(text);  		
    	}
    	
		
		// Place the information into the request so we can display it in the jsp
		request.setAttribute("datalist", allData);
		
		// Place in session as well to handle case where user did not enter anything text field
		theSession.setAttribute("datalist", allData);
		
    	ActionForward forward = mapping.findForward("successlike");
    	
    	return(forward);
    	
    }	    
	/**
	 * Called when the user enters a search value into the text field and presses search
	 */
    public ActionForward search(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	// create the session object
    	HttpSession theSession = request.getSession(true);	
    	
    	// Construct the Contact251 form
    	SearchContact251Form theSearchForm = new SearchContact251Form();
    	
    	// Construct any empty list to keep from getting the null pointer error
    	List allData = new ArrayList();
    	
    	// check if the form that is coming off the request is null then its the first time in
    	if (form == null) {
    		// Place the search form in the session
    		theSession.setAttribute("SearchContact251Form", theSearchForm);
    	}
    	else {
    		
    		// set the search form based on what is passed in
    		theSearchForm = (SearchContact251Form)form;
    		
        	// Get the parameter off of the request url
        	String text = theSearchForm.getSearchText();
        	
        	
        	// Select all of the data for contact 251 like what is passed
        	allData = Contact251Service.selectInfoLike(text);  		
    	}
    	
		
		// Place the information into the request so we can display it in the jsp
		request.setAttribute("datalist", allData);
		
		// Place in session as well to handle case where user did not enter anything text field
		theSession.setAttribute("datalist", allData);		
		
    	ActionForward forward = mapping.findForward("successlike");
    	
    	return(forward);    	
    }
    
	/**
	 * Called when the user clicks on the link associated to a line number
	 */
    public ActionForward expand(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	// Get the parameter off of the request
    	String lineNumber = request.getParameter("linenumber");
    	
    	System.out.println("lineNumber "+lineNumber);
    	
    	// Turn the string value into an integer
    	Integer value = Integer.parseInt(lineNumber);
    	
    	// Get five lines before and after
    	Integer min = value-5;
    	Integer max = value+5;
    	
    	// Build the first half of the sql
    	String firstHalf = "select linenumber, info from Contact251 where lineNumber < " + max;
    	
    	// Build the second half of the sql
    	String secondHalf = " and lineNumber > " + min;
    	
    	// Create the whole SQL
    	String sql = firstHalf + secondHalf;
    	
    	System.out.println("sql "+sql);
    	
    	// Get the list of information for those line numbers
    	List<Contact251> theList = Contact251Service.selectByLineNumber(sql);

    	// create the session object
    	HttpSession theSession = request.getSession(true);	
    	
		// Place in session as well to handle case where user did not enter anything text field
		theSession.setAttribute("datalist", theList);		
		
    	ActionForward forward = mapping.findForward("successexpand");
    	
    	return(forward);      	
    
    }
}
