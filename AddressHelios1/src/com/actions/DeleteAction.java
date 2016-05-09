package com.actions;

import java.util.ArrayList;
import java.util.HashSet;
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
import com.dao.Student;
import com.forms.DeleteForm;
import com.forms.EditForm;
import com.forms.InputForm;
import com.service.StudentService;


public class DeleteAction extends DispatchAction{
	
	   /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";
    private final static String ERROR = "error";
    /**
     * This is called when the user presses the ok button on the edit JSP. This method will
     * handle both the add and the edit.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward OK(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
    	String forwardLocation=SUCCESS;
		try {
	    	DeleteForm theForm = (DeleteForm)form;												// Get the form that was passed
       		// Get complete student information using the id
    	    Student theStudent = StudentService.getCompleteStudentById(theForm.getStudentId());		    	
    	    StudentService.deleteStudent(theStudent);											// and then delete
	    	
	        // Get the list of student / address information which includes all of the sub or nested objects that hibernate does not populate by default
	        ArrayList<Student> theList = (ArrayList<Student>)StudentService.listCompleteStudentInfo();
	       
	    	HttpSession theSession = request.getSession(false);									// Get the session object
	    	if (theSession != null) {															// Make sure session is not expired etc...
	    		InputForm theInputForm = (InputForm)theSession.getAttribute("InputForm");		// Get our form that displays the list
	    		theInputForm.setStudentList(theList);											// Update the student list so that jsp will display things as new info
		        request.getSession().setAttribute(Constants.CURRENT_SORT,null);								// Set us back to the default sort
		        // This is the list in memory for logic iterate tag to display list
				request.setAttribute("studentlist", theList);    
	    	}	    
	    }
		catch(HibernateException e) {
			forwardLocation=ERROR;																// set the flag so that we go the error JSP
			request.setAttribute(Constants.EXCEPTION_REPORT, e.toString());						// place the short text 
			request.setAttribute(Constants.EXCEPTION, e);						    			// place the exception into the request
		}
        return mapping.findForward(forwardLocation);         	

    }
    public ActionForward Cancel(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
    	System.out.println("Cancel pressed in delete action");

        return mapping.findForward(SUCCESS);
    }

}
