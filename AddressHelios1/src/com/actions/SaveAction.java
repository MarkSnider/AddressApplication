package com.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.junit.Assert;

import com.dao.Address;
import com.dao.Email;
import com.dao.Phone;
import com.dao.RadioShow;
import com.dao.State;
import com.dao.Student;
import com.dao.StudentType;
import com.dao.Web;
import com.forms.InputForm;
import com.forms.SaveEditForm;
import com.service.StudentService;

public class SaveAction extends DispatchAction {
	
	/**
	 * Delete the student record from the database using the id that was passed in.
	 * @throws Exception
	 */
    public ActionForward DeleteStudent(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	// Get the student id out of the request...
    	String StudentId = request.getParameter("StudentId");
    	
    	// Get the student object
    	Student theStudent = StudentService.SelectStudentById(new Long(StudentId));
    	
    	// Delete the student object from the database 
    	StudentService.deleteStudent(theStudent);
    	
        // Get All of the students
    	ArrayList<Student> theList = (ArrayList<Student>)StudentService.SelectStudent();
    	
    	// This is the list in memory for logic iterate tag to display list
    	request.setAttribute("studentlist", theList);   
    	
    	return mapping.findForward("datatable"); 
    }
	/**
	 * Called when the user presses the add button to create a new record.
	 * @return
	 * @throws Exception
	 */
    public ActionForward AddStudent(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	SaveEditForm theForm = (SaveEditForm)form;
    	
    	// This tells us we are doing and ADD
    	theForm.setCurrentSaveAction("ADD");
    	
        // Get the information for the student type drop down
        List<StudentType> theTypesList = (ArrayList<StudentType>)StudentService.SelectStudentType();   
	    theForm.setTypesList(theTypesList);
	    
	    // NOTE!!! I had to put this in session scope or I would get a JSP exception saying it could not find this attribute in any scope
	    request.getSession().setAttribute("studentTypes", theTypesList);

        // Get the list of states
        List<State> theStates = StudentService.selectStates();
        
        // I have to put these in the Session or we get a JSP Exception on saving saying it cannot find statesList
        request.getSession().setAttribute("statesList", theStates);		
        
	    // Place the empty phone, web and email information in the request
	    request.getSession().setAttribute("emailList", new ArrayList<Email>());	  
	    request.getSession().setAttribute("phoneList", new ArrayList<Phone>());	 
	    
	    // So we dont come up with the message that we just saved information
	    theForm.setSaveEvent("ADD");
	    theForm.setName("");  // Avoid the shine through
	    theForm.setCity("");
	    theForm.setCountry("");
	    theForm.setState("");
	    theForm.setStreet("");
	    
    	return mapping.findForward("success"); 
    }
	/**
	 * This is called when the user presses the link on the main data table.
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
    public ActionForward EditStudent(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	// Get the student id from the request parameter
    	String StudentId = request.getParameter("StudentId");    
    	
    	SaveEditForm theForm = (SaveEditForm)form;
    	
    	// This tells us we are doing and EDIT
    	theForm.setCurrentSaveAction("EDIT");
    	
        theForm.setSaveEvent("");
        
        // Get the single student by id
        ArrayList<Student> theList = (ArrayList<Student>)StudentService.SelectStudentsById(new Long(StudentId));
        
        // Get the information for the student type drop down
        List<StudentType> theTypesList = (ArrayList<StudentType>)StudentService.SelectStudentType();
        
	    // Should just be a single student
	    Student data = theList.get(0);
	        
	    // Save the values in the form
	    theForm.setSingleStudent(data);	        
	    theForm.setName(data.getStudentName());
	    theForm.setStreet(data.getStreet());
	    theForm.setCity(data.getCity());
	    theForm.setCountry(data.getCountry());
	    theForm.setZip(data.getZip());
	    
	    // Get that potentially long string of general information
	    String studentInformation = data.getStudentInfo();
	    
	    // So we can place that information in the text area
	    request.setAttribute("STUDENT_INFORMATION", studentInformation);
	    
	    
	    theForm.setTypesList(theTypesList);
	    
	    // NOTE!!! I had to put this in session scope or I would get a JSP exception saying it could not find this attribute in any scope
	    request.getSession().setAttribute("studentTypes", theTypesList);
	    
	    // Loop through the student types
	    for (StudentType current: theTypesList) {
	        	
	        	// Find one that matches
	        	if (current.getStudentType().equals(data.getStudentType())) {
	        		
	        		// Set the selected value
	        		current.setSelected("SELECTED");
	        	}
	    }	    
	    
	    // Place the student types in the request
	    request.getSession().setAttribute("studentTypes", theTypesList);
	        
	    // Sets the state to selected
	    SetSelectedState(mapping, form,  request, response, data);	     
	        
	    List<Email> EmaiList=(ArrayList<Email>)StudentService.SelectEmailForStudent(data.getStudentId());
	    
	    // Place the email information in the request
	    request.getSession().setAttribute("emailList", EmaiList);	   
	    
	    // Get the phone information
	    List<Phone> thePhones = (ArrayList<Phone>)StudentService.selectPhones(data.getStudentId());
	    
	    // Place the phone information in the request
	    request.getSession().setAttribute("phoneList", thePhones);	
	    
	    // Get the Web URLS
	    List<Web> WebList=(ArrayList<Web>)StudentService.selectWeb(data.getStudentId());

	    // Place the web information in the request
	    request.setAttribute("webList", WebList);	
	    
    	return mapping.findForward("success"); 
    }
    /**
     * Sets the selected state and puts the states list in the request
     * @param data - The current Student object
     */
    public void SetSelectedState(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response, Student data) {
    	
        // Get the list of states
        List<State> theStates = StudentService.selectStates();
        
        // I have to put these in the Session or we get a JSP Exception on saving saying it cannot find statesList
        request.getSession().setAttribute("statesList", theStates);		  
        
        // Lets try to select the state
        // Loop through the student types
        for (State current: theStates) {
        	
        	// Get the state
        	String CurrentState = current.getAbbrev();
        	
        	// Find one that matches
        	if (CurrentState.equals(data.getState())) {
        		
        		// Set the selected value
        		current.setSelected("SELECTED");
        	}
        }	  	
    }
    
    /**
     * Is called when the user presses the Submit button from the edit page.
     * This code will update the student information along with all of the related information
     * like address, phone, email and web information...
     * @return
     * @throws Exception
     */
    public ActionForward SaveData(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	SaveEditForm theForm = (SaveEditForm)form;
        theForm.setSaveEvent("SAVE");
        
        
        // This is some background information on the person and can be rather large
        String studentInformation = request.getParameter("generalinformation");
        
        // If called from add then we will not have a current student
        Student currentStudent = theForm.getSingleStudent();
        
        // Get the current action
        String currentAction = theForm.getCurrentSaveAction();
        
        // Set to zero assuming and ADD
        long addressId = 0;
        
        // We are doing an EDIT
        if (currentAction.equals("EDIT")) {
        	
        	// Get the actual address id
        	addressId = currentStudent.getAddressId();
        }

        
        Set<Email> theEmailSet =  StudentService.processEmailAddresses(request);	// Get the email information from the page
        Set<Phone> thePhoneSet = StudentService.processPhoneNumbers(request);		// Get the phone number information
        Set<Web> theWebSet = StudentService.processWebAddresses(request);			// Get the web information from the page
        
        String street = theForm.getStreet();
        String city = theForm.getCity();
        String state = theForm.getState();
        String country = theForm.getCountry();
        String zip = theForm.getZip();
    	// Create the address object
		Address address = new Address(street, city, state, zip, country);
		address.setAddressId(addressId);
		
		// Get the name
		String name = theForm.getName();
		
		//Create the student object and add the addresses, phone numbers, email addresses and radio show information
		Student student1 = new Student(name,  address, thePhoneSet,theEmailSet,theWebSet, new HashSet<RadioShow>(0));	
		
		student1.setStudentInfo(studentInformation);	// Set that new basic information field
		String studentType = theForm.getStudentType();
		student1.setStudentType(studentType);			// Get the student type information out of the form.
		
		// Set the student id using the object when we are doing an add
		if (currentAction.equals("EDIT")) {
			long studentId = currentStudent.getStudentId();
			student1.setStudentId(new Long(studentId));			
		}
		else {
			// For an add lets make sure nothing is left over as shine through
			student1.setStudentId(0L);	
		}

    	StudentService.saveStudent(student1);	        
        
        // Get All of the students
    	ArrayList<Student> theList = (ArrayList<Student>)StudentService.SelectStudent();
    	
    	// This is the list in memory for logic iterate tag to display list
    	request.setAttribute("studentlist", theList);   
    	
    	return mapping.findForward("datatable"); 
    }

    	
}
