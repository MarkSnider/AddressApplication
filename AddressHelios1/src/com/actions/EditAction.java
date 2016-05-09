package com.actions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.hibernate.HibernateException;

import com.dao.Address;
import com.dao.Email;
import com.dao.Phone;
import com.dao.RadioShow;
import com.dao.ShowDetail;
import com.dao.Student;
import com.dao.Web;
import com.forms.EditForm;
import com.forms.InputForm;
import com.service.StudentService;
import com.constants.Constants;


public class EditAction extends DispatchAction{
	
    private final static String SUCCESS = "success";
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
        

    	String forwardLocation=SUCCESS;					// assume all went well
    	doSaveAndList(form,request);					// Save the information and lists it

        return mapping.findForward(forwardLocation);
    }
    /**
     * Saves the information to the database.  Does a list to get the new information back
     * and then places the new list of student information into the session
     * @param form - the current form
     * @param request	- HTTP request object
     * @throws Exception - Any exception is thrown
     */
    private void doSaveAndList( ActionForm  form,
    		 					HttpServletRequest request) 
    							throws Exception {
    	
    	// Convert the passed form to the edit form
    	EditForm theEditForm = (EditForm)form;	

    	Set<Phone> phoneNumbers = new HashSet<Phone>();					// Create the set of phone numbers    	
    	Phone thePhone = new Phone();									// Create the phone object
    	thePhone.setPhoneNumber(theEditForm.getHomePhone());			// get the home phone from the form
    	thePhone.setPhoneType("Home");									// set the phone type
    	phoneNumbers.add(thePhone);										// Add the first phone number
    	thePhone = new Phone();											// Create the phone object
    	thePhone.setPhoneNumber(theEditForm.getCellPhone());			// get the cell phone from the form
    	thePhone.setPhoneType("Cell");									// set the phone type    
    	phoneNumbers.add(thePhone);										// Add the cell phone number
    	
    	Set<Email> emailAddresses = new HashSet<Email>();				// Create the set of Email address
    	Email Email = new Email();										// Create the Email object
    	Email.setEmailAddr(theEditForm.getHomeEmail());					// get the home Email from the form
    	Email.setEmailType("Home");									    // set the Email type
    	emailAddresses.add(Email);										// Add the first Email address
    	Email = new Email();											// Create the phone object
    	Email.setEmailAddr(theEditForm.getWorkEmail());					// get the cell phone from the form
    	Email.setEmailType("Work");										// set the email type
    	emailAddresses.add(Email);										// Add the second email address  	
    	
    	Set<Web> webAddresses = new HashSet<Web>();						// Create the set of web address
    	Web theWeb = new Web();											// We support three web sites per student
    	theWeb.setUrl(theEditForm.getUrlOne());					
    	webAddresses.add(theWeb);
    	theWeb = new Web();
    	theWeb.setUrl(theEditForm.getUrlTwo());					
    	webAddresses.add(theWeb); 
    	theWeb = new Web();
    	theWeb.setUrl(theEditForm.getUrlThree());					
    	webAddresses.add(theWeb);  	
    	
    	
    	// Create the address object
		Address address = new Address(theEditForm.getStreet(), theEditForm.getCity(), theEditForm.getState(), theEditForm.getZip(), theEditForm.getCountry());
	
		//Create the student object and add the addresses, phone numbers, email addresses and radio show information
		Student student1 = new Student(theEditForm.getName(),  address, phoneNumbers,emailAddresses,webAddresses, theEditForm.getStudentRadioShows());	
		
		student1.setStudentInfo(theEditForm.getInformation());			// Set that new basic information field
		student1.setStudentType(theEditForm.getStudentType());			// Get the student type information out of the form.
		if ( theEditForm.getStudentId() != 0) {
			student1.setStudentId(theEditForm.getStudentId());
		}
		
		System.out.println("Before StudentService.saveStudent...");
    	StudentService.saveStudent(student1);														// this will do a saveOrUpdate so will work for add or edit
    	System.out.println("After StudentService.saveStudent...");
    	InputForm theForm = (InputForm)request.getSession().getAttribute("InputForm");				// Get the list related form out of the session
    	System.out.println("Before StudentService.listCompleteStudentInfo()...");
    	ArrayList<Student> theList = (ArrayList<Student>)StudentService.listCompleteStudentInfo();	// list the newly added information		
    	System.out.println("After StudentService.listCompleteStudentInfo()...");
    	theForm.setStudentList(theList);															// Make sure to update the form that backs the list display JSP			
        request.getSession().setAttribute(Constants.CURRENT_SORT,null);								// Set us back to the default sort
        // This is the list in memory for logic iterate tag to display list
		request.setAttribute("studentlist", theList); 
    	
    }
    public ActionForward Cancel(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
    	System.out.println("Cancel pressed ");

        return mapping.findForward(SUCCESS);
    }  
}
