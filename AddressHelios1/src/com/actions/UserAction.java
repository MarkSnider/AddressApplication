package com.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.dao.Student;
import com.forms.UserForm;
import com.service.StudentService;



public class UserAction extends org.apache.struts.action.Action {
	
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        UserForm userForm = (UserForm) form;
        // Get the list of student / address information which includes all of the sub or nested objects that hibernate does not populate by default
        ArrayList<Student> theList = (ArrayList<Student>)StudentService.listCompleteStudentInfo();    
        userForm.setActorList(theList);
        
       
        return mapping.findForward(SUCCESS);
    }	

}
