package com.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.HibernateException;

import com.constants.Constants;
import com.dao.Address;
import com.dao.Email;
import com.dao.Phone;
import com.dao.Student;
import com.forms.EditForm;
import com.forms.InputForm;
import com.forms.SearchForm;
import com.service.StudentService;

public class SearchAction extends DispatchAction{
    private final static String SUCCESS = "success";
    private final static String ERROR = "error";
    /**
     * This is called when the user presses the ok button on the advanced search screen.
     * Do the desired search with the appropriate match mode
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return success
     */
    public ActionForward OK(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
    	String forwardLocation=SUCCESS;									// assume all went well

		try {
			List<Student> theList = null;														
	    	SearchForm theForm = (SearchForm)form;						// Get the list related form out of the session
	    	if (theForm.getStartsWith() != null && theForm.getStartsWith().length() > 0) {
	    		theList =  StudentService.searchWithMatchMode(theForm.getSelectedMatchMode(),theForm.getStartsWith());
	    	}
	    	else {
	    		// allows us to make queries of things like only radio show guests.
	    		theList = StudentService.selectStudentsByStudentType(theForm.getStudentType());
	    	}
	    	 
	    	InputForm theInputForm = (InputForm)request.getSession().getAttribute("InputForm");				// Get the list related form out of the session
	    	theInputForm.setStudentList((ArrayList<Student>)theList);					// Make sure to update the form that backs the list display JSP			
	        // This is the list in memory for logic iterate tag to display list
			request.setAttribute("studentlist", theList);    
	    	request.getSession().setAttribute(Constants.CURRENT_SORT,null);				// Set us back to the default sort

		}
		catch(HibernateException e) {
			forwardLocation=ERROR;																		// set the flag so that we go the error JSP
			request.setAttribute(Constants.EXCEPTION_REPORT, e.toString());								// place the short text 
			request.setAttribute(Constants.EXCEPTION, e);						    					// place the exception into the request
		}
        return mapping.findForward(forwardLocation);
    }
    
    /**
     * Get the records that match based on GUITAR, LISTENER, FAMILY etc...
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return the list of students
     * @throws Exception
     */
    public ActionForward ListChange(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
    	String forwardLocation=SUCCESS;									// assume all went well

		try {
			List<Student> theList = null;	
			// Get the value in the drop down list when the user changes to GUITAR, LISTENER, etc...
			String theValue = request.getParameter("listValue");
			
			if (theValue.equals("ALL")) {
				
		        // Get the list of student / address information which includes all of the sub or nested objects that hibernate does not populate by default
		        theList = (ArrayList<Student>)StudentService.listCompleteStudentInfo();
			}
			else {
				
		    	// allows us to make queries of things like only radio show guests.
		    	theList = StudentService.selectStudentsByStudentType(theValue);				
			}

			// Get the session
	    	HttpSession session = request.getSession();
	    	
	    	// place the selected list value in the session
	    	session.setAttribute("listValue", theValue);
	    	
	    	InputForm theInputForm = (InputForm)request.getSession().getAttribute("InputForm");				// Get the list related form out of the session
	    	theInputForm.setStudentList((ArrayList<Student>)theList);					// Make sure to update the form that backs the list display JSP			
	        // This is the list in memory for logic iterate tag to display list
			request.setAttribute("studentlist", theList);    
	    	request.getSession().setAttribute(Constants.CURRENT_SORT,null);				// Set us back to the default sort

		}
		catch(HibernateException e) {
			forwardLocation=ERROR;																		// set the flag so that we go the error JSP
			request.setAttribute(Constants.EXCEPTION_REPORT, e.toString());								// place the short text 
			request.setAttribute(Constants.EXCEPTION, e);						    					// place the exception into the request
		}
        return mapping.findForward(forwardLocation);
    } 
    /**
     * Just take us back to the main display
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward Cancel(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
    	String forwardLocation=SUCCESS;									// assume all went well


        return mapping.findForward(forwardLocation);
    }

}
