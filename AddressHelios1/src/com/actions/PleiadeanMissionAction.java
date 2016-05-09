package com.actions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.dao.Contact251;
import com.dao.PleiadeanMission;
import com.forms.PleiadeanMissionForm;
import com.forms.SearchContact251Form;
import com.service.Contact251Service;
import com.service.PleiadeanMissionService;

public class PleiadeanMissionAction extends DispatchAction{
	
	/**
	 * Select and display all of the data for contact 251
	 */
    public ActionForward displayAll(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	// Select all of the data for contact 251
    	List allData = PleiadeanMissionService.selectAll();
		
		// Place the information into the request so we can display it in the jsp
		request.setAttribute("datalist", allData);
		
    	ActionForward forward = mapping.findForward("success");
    	
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
    	
    	// Construct the PleiadeanMissionForm form
    	PleiadeanMissionForm theSearchForm = new PleiadeanMissionForm();
    	
    	// Construct any empty list to keep from getting the null pointer error
    	List allData = new ArrayList();
    	
    	// check if the form that is coming off the request is null then its the first time in
    	if (form == null) {
    		// Place the search form in the session
    		theSession.setAttribute("PleiadeanMissionForm", theSearchForm);
    	}
    	else {
    		
    		// set the search form based on what is passed in
    		theSearchForm = (PleiadeanMissionForm)form;
    		
        	// Get the parameter off of the request url
        	String text = theSearchForm.getSearchText();
        	
        	
        	// Select all of the data for contact 251 like what is passed
        	allData = PleiadeanMissionService.selectInfoLike(text);  		
    	}
    	
		
		// Place the information into the request so we can display it in the jsp
		request.setAttribute("datalist", allData);
		
		// Place in session as well to handle case where user did not enter anything text field
		theSession.setAttribute("datalist", allData);		
		
    	ActionForward forward = mapping.findForward("successlike");
    	
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
    	    	
    	// Construct any empty list to keep from getting the null pointer error
    	List allData = new ArrayList();
    	
        // Get the parameter off of the request url
        String text = request.getParameter("searchtext");
        		
        // Select PleiadeanMission like what is passed
        allData = PleiadeanMissionService.selectInfoLike(text);  		
		
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
    	String firstHalf = "select linenumber, info from pleiadean_mission where linenumber < " + max;
    	
    	// Build the second half of the sql
    	String secondHalf = " and lineNumber > " + min;
    	
    	// Create the whole SQL
    	String sql = firstHalf + secondHalf;
    	
    	System.out.println("sql "+sql);
    	
    	// Get the list of information for those line numbers
    	List<PleiadeanMission> theList = PleiadeanMissionService.selectByLineNumber(sql);

    	// create the session object
    	HttpSession theSession = request.getSession(true);	
    	
		// Place in session as well to handle case where user did not enter anything text field
		theSession.setAttribute("datalist", theList);		
		
    	ActionForward forward = mapping.findForward("successexpand");
    	
    	return(forward);      	
    
    }

}
