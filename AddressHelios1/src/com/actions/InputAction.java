/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForward;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.HibernateException;
import org.hibernate.criterion.MatchMode;

import com.constants.Constants;
import com.dao.Address;
import com.dao.CurrentSort;
import com.dao.Email;
import com.dao.MatchModeData;
import com.dao.Phone;
import com.dao.RadioShow;
import com.dao.ShowDetail;
import com.dao.State;
import com.dao.Student;
import com.dao.StudentType;
import com.dao.Web;
import com.forms.DeleteForm;
import com.forms.EditForm;
import com.forms.InputForm;
import com.forms.RadioShowForm;
import com.forms.SearchForm;
import com.forms.UserForm;
import com.service.StudentService;
import com.sorting.RadioShowComparator;
import com.sorting.SortEnum;



/**
 * This action responds to the possible button clicks on the main address display.
 * Button clicks like add, edit, delete
 * @author Mark Snider
 */
public class InputAction extends DispatchAction {
    
    private final static String SUCCESS = "success";	// Find forward to take us to the main display
    private final static String EDIT = "edit";			// used for the find forward to take us to the add / edit screen
    public final static String ACTION = "ACTION";		// Used as a request attribute to tell add/edit JSP what header to display
    private final static String DELETE = "delete";		// used for going to the delete jsp
    private final static String ERROR = "error";
    private final static String SEARCH = "search";
    private final static String PAGE = "page";
    private final static String HELP = "help";
    private final static String UPLOAD = "upload";
    private final static String RADIO = "radio";
    public final static String INFO = "INFO";	// Lets us 
    public final static String TEST="test";
    public final static String TEST2="test2"; 
    private String action;	// This will tell us if the user is doing an add or an edit since the same JSP is used for both functions							
 
    /**
     * Go to the  test page
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward testpage(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	return mapping.findForward(TEST); 
    	
    }
    /**
     * Go to the 2nd test page
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward testpage2(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	return mapping.findForward(TEST2); 
    	
    } 
    /**
     * Go to the help page
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward helppage(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	return mapping.findForward(HELP); 
    	
    } 
    /**
     * Go to the Upload Page.  So that we can upload a file
     * and the process that file for creating MP3(s) for the radio show.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward upload(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	// Takes us to the upload forward
    	return mapping.findForward(UPLOAD); 
    	
    }      
    /**
     * Sets the request variable for when the user clicks the radio button.  This 
     * information then can be displayed by the more info button.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward moreinfo(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    		String forwardLocation=SUCCESS;
    		String idSelected = request.getParameter("studentid");	// Get the user selected from the radio buttons	
    		System.out.println("idSelected " + idSelected);
       		// Get complete student information using the id, minus the address, email etc...
    	    Student theStudent = StudentService.getCompleteStudentById(new Long(idSelected));	
    	    
    	    System.out.println("idSelected " + idSelected);
    	    System.out.println("more info" + theStudent.getStudentInfo());
    	    
    	    request.setAttribute(INFO, theStudent.getStudentInfo());
    		return mapping.findForward(forwardLocation);   
    }
    /**
     * Takes the application to the radio show page
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward radioshow(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    
    	String forwardLocation=RADIO;													
    	String idSelected = request.getParameter("studentid");					// Get the user selected from the radio buttons	
		if (idSelected == null) {												// user did not select a radio button
			forwardLocation=ERROR;												// set the flag so that we go the error JSP
			request.setAttribute(Constants.EXCEPTION_REPORT, Constants.SELECT_RADIO_MSG);		// place the short text 	
		}
		else {
	   	    Student theStudent = StudentService.getCompleteStudentById(new Long(idSelected));	// Get complete student information using the id
	   	    request.setAttribute(Constants.STUDENT, theStudent);								// Place the selected student object into the request
	   	    RadioShowForm theForm = (RadioShowForm)request.getSession().getAttribute("RadioShowForm");
	   	    if (theForm == null) {
	   	    	theForm = new RadioShowForm();
	   	    	HttpSession theSession = (HttpSession)request.getSession(true);
	   	    	theSession.setAttribute("RadioShowForm", theForm);
	   	    }
	   	    theForm.setTitle(theStudent.getStudentName());						// Set the title equal to the name for now
	   	    
	   	    
		}
    	
 		

        return mapping.findForward(forwardLocation);        
       
       
    }  
    
    /**
     * Takes the application to the help page.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward helpdocs(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    
    	String forwardLocation=SUCCESS;													
    	System.err.println(" helpdocs");

        return mapping.findForward(forwardLocation);        
       
       
    }  
    
    /**
     * Does a forward to the JSP that allows us to display things as pages
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward page(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	// Goes to the pagination display
    	String forwardLocation=PAGE;													


        return mapping.findForward(forwardLocation);        
       
       
    }  
    
    /**
     * Takes you to the advanced search page where you can look for people that are guests to the radio show
     * and things like that.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward advancedSearch(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	 return mapping.findForward(SEARCH);
    }
    /**
     * Does a forward to the search JSP.  Loads up the potential search types in the matchMode list.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward search(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	String forwardLocation=SUCCESS;									// assume all went well

		try {
	    															
			InputForm theForm = (InputForm)form;						// Get the list related form out of the session
	    	String startsWith = theForm.getStartsWith();
	    	List<Student> theList =  StudentService.searchWithMatchMode(theForm.getSelectedMatchMode(),startsWith);
	    	InputForm theInputForm = (InputForm)request.getSession().getAttribute("InputForm");				// Get the list related form out of the session
	    	theInputForm.setStudentList((ArrayList<Student>)theList);					// Make sure to update the form that backs the list display JSP			
	        request.getSession().setAttribute(Constants.CURRENT_SORT,null);				// Set us back to the default sort
	        // This is the list in memory for logic iterate tag to display list
			request.setAttribute("studentlist", theList);    	  
		}
		catch(HibernateException e) {
			forwardLocation=ERROR;																		// set the flag so that we go the error JSP
			request.setAttribute(Constants.EXCEPTION_REPORT, e.toString());								// place the short text 
			request.setAttribute(Constants.EXCEPTION, e);					    					// place the exception into the request
		}


        return mapping.findForward(forwardLocation);        
       
    }   
    /**
     * Called at initial startup to display the list of addresses
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward populate(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	String forwardLocation=SUCCESS;
       
		try {
	        InputForm inputForm = (InputForm) form;
	       
	        // Get the list of student / address information which includes all of the sub or nested objects that hibernate does not populate by default
	        ArrayList<Student> theList = (ArrayList<Student>)StudentService.listCompleteStudentInfo();
	       
	        inputForm.setStudentList(theList);		
	        
	        // This is the list in memory for logic iterate tag to display list
			request.setAttribute("studentlist", theList);    	        
	    }
		catch(HibernateException e) {
			forwardLocation=ERROR;																		// set the flag so that we go the error JSP
			request.setAttribute(Constants.EXCEPTION_REPORT, e.toString());								// place the short text 
			request.setAttribute(Constants.EXCEPTION, e);						    					// place the exception into the request
		}
        return mapping.findForward(forwardLocation);        
       
    }
    
    public ActionForward displayemail(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	String forwardLocation="displayemail";
    	String StudentId = request.getParameter("StudentId");
    	List<Email> theEmails = StudentService.SelectEmailForStudent(new Long(StudentId));
    	
        // This is the list in memory for logic iterate tag to display list
		request.setAttribute("emailslist", theEmails); 
		
        return mapping.findForward(forwardLocation);        
       
    }    
    
    // ListChange
    /*
     * The user changed the student type in the drop down.  So get the type out of the request parameter
     */
    public ActionForward ListChange(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	ArrayList<Student> theList = new ArrayList<Student>();
    	
    	String forwardLocation="datatable";
       
		try {
	        InputForm inputForm = (InputForm) form;
	        
	        // Get the list value
	        String listValue = request.getParameter("listValue");
	        
	        // Set the filter item so we can make that drop down retain the item that was selected
	        inputForm.setSelectedFilterItem(listValue);
	        
	        if (listValue != null && listValue.equals("ALL") != true) {
		        // Get those students by type
		        theList = (ArrayList<Student>)StudentService.SelectStudentsByType(listValue);
		       
		        inputForm.setStudentList(theList);		        	
	        }
	        else {
		        // Get All of the students
		        theList = (ArrayList<Student>)StudentService.SelectStudent();
		       
		        inputForm.setStudentList(theList);		        	
	        }
	
	        
	        // This is the list in memory for logic iterate tag to display list
			request.setAttribute("studentlist", theList);    	        
	    }
		catch(HibernateException e) {
			forwardLocation=ERROR;																		// set the flag so that we go the error JSP
			request.setAttribute(Constants.EXCEPTION_REPORT, e.toString());								// place the short text 
			request.setAttribute(Constants.EXCEPTION, e);						    					// place the exception into the request
		}
        return mapping.findForward(forwardLocation);        
       
    }     
    
    
    /**
     * This is called when the user presses edit.  We go out and get the information for a single student record.
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
    	
    	// Take us to the edit page
    	String forwardLocation="editpage";
    	
//    	// Get the student id from the request parameter
//    	String StudentId = request.getParameter("StudentId");
//		try {
//	        InputForm inputForm = (InputForm) form;
//	        inputForm.setSaveEvent("");
//	        
//	        // Get the single student by id
//	        ArrayList<Student> theList = (ArrayList<Student>)StudentService.SelectStudentsById(new Long(StudentId));
//	        
//	        // Get the information for the student type drop down
//	        List<StudentType> theTypesList = (ArrayList<StudentType>)StudentService.SelectStudentType();
//	        
//		    // Should just be a single student
//		    Student data = theList.get(0);
//		        
//		    // Save the value in the form
//		    inputForm.setSingleStudent(data);	 
//		        
//		    // Loop through the student types
//		    for (StudentType current: theTypesList) {
//		        	
//		        	// Find one that matches
//		        	if (current.getStudentType().equals(data.getStudentType())) {
//		        		
//		        		// Set the selected value
//		        		current.setSelected("SELECTED");
//		        	}
//		    }
//		        
//		    // Save the student Types as well
//		   // inputForm.setStudentTypes(theTypesList);
//		        
//		    // Place the student types in the request
//		    request.setAttribute("studentTypes", theTypesList);
//		        
//		    // Sets the state to selected
//		    SetSelectedState(mapping, form,  request, response, data);	     
//		        
//		    List<Email> EmaiList=(ArrayList<Email>)StudentService.SelectEmailForStudent(data.getStudentId());
//		    
//		    // Place the email information in the request
//		    request.setAttribute("emailList", EmaiList);	   
//		    
//		    // Get the phone information
//		    List<Phone> thePhones = (ArrayList<Phone>)StudentService.selectPhones();
//		    
//		    // Place the phone information in the request
//		    request.setAttribute("phoneList", thePhones);	
//		    
//		    // Get the Web URLS
//		    List<Web> WebList=(ArrayList<Web>)StudentService.selectWeb(data.getStudentId());
//
//		    // Place the web information in the request
//		    request.setAttribute("webList", WebList);	
		    

//	    }
//		catch(HibernateException e) {
//			forwardLocation=ERROR;																		// set the flag so that we go the error JSP
//			request.setAttribute(Constants.EXCEPTION_REPORT, e.toString());								// place the short text 
//			request.setAttribute(Constants.EXCEPTION, e);						    					// place the exception into the request
//		}
        return mapping.findForward(forwardLocation);        
       
    }   
    
    /**
     * Sets the selected state and puts the states list in the request
     * @param data - The current Student object
     */
    public void SetSelectedState(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response, Student data) {
    	
        // Get the list of states
        List<State> theStates = StudentService.selectStates();
        
        // Place list of states in the request
        request.setAttribute("statesList", theStates);		  
        
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
     * This is called when the user presses save on the Edit Student Page
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward SaveStudent(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	String forwardLocation="editpage";
        InputForm theForm = (InputForm)form;
        theForm.setSaveEvent("SAVE");
    	
        return mapping.findForward(forwardLocation);        
       
    } 
    /**
     * This method is the standard call to display the page
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward datatable(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	String forwardLocation="datatable";
       
		try {
	        InputForm inputForm = (InputForm) form;
	       
	        ArrayList<Student> theList = (ArrayList<Student>)StudentService.SelectStudent();
	       
	        inputForm.setStudentList(theList);		
	        
	        // This is the list in memory for logic iterate tag to display list
			request.setAttribute("studentlist", theList);    	        
	    }
		catch(HibernateException e) {
			forwardLocation=ERROR;																		// set the flag so that we go the error JSP
			request.setAttribute(Constants.EXCEPTION_REPORT, e.toString());								// place the short text 
			request.setAttribute(Constants.EXCEPTION, e);						    					// place the exception into the request
		}
        return mapping.findForward(forwardLocation);        
       
    }   
    /**
     * Called at initial startup to display the list of addresses
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward all(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	String forwardLocation=SUCCESS;
       
		try {
	        InputForm inputForm = (InputForm) form;
		    
	        if (inputForm != null) {
		        // Get the list of student / address information which includes all of the sub or nested objects that hibernate does not populate by default
		        ArrayList<Student> theList = (ArrayList<Student>)StudentService.listCompleteStudentInfo();
		        if (theList != null) {
			        inputForm.setStudentList(theList);	// Update the list if we find information	
			        inputForm.setStartsWith("");		// Clear last search this can cause a nasty search bug 			        	
		        }
		        // This is the list in memory for logic iterate tag to display list
				request.setAttribute("studentlist", theList);    	       	
	        }

	    }
		catch(HibernateException e) {
			forwardLocation=ERROR;																		// set the flag so that we go the error JSP
			request.setAttribute(Constants.EXCEPTION_REPORT, e.toString());								// place the short text 
			request.setAttribute(Constants.EXCEPTION, e);						    					// place the exception into the request
		}
        return mapping.findForward(forwardLocation);        
       
    }
    
    /**
     * Called when the user clicks on the sort icon to change the sort to ascending or descending
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return Success comes back to the input JSP.
     * @throws Exception
     */
    public ActionForward changesort(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

    	String forwardLocation=SUCCESS;
       
		try {
	        InputForm inputForm = (InputForm) form;
	       
	        CurrentSort theSort = getCurrentSort(request);	// Get the sort
	        // Get the list of student / address information which includes all of the sub or nested objects that hibernate does not populate by default
	        ArrayList<Student> theList = (ArrayList<Student>)StudentService.listCompleteStudentInfoWithSort(theSort);
	       
	        inputForm.setStudentList(theList);		}
		catch(HibernateException e) {
			forwardLocation=ERROR;																		// set the flag so that we go the error JSP
			request.setAttribute(Constants.EXCEPTION_REPORT, e.toString());								// place the short text 
			request.setAttribute(Constants.EXCEPTION, e);						    					// place the exception into the request
		}
        return mapping.findForward(forwardLocation);        
       
    }

    /**
     * Looks in the request to see if we are doing an ascending sort or a descending sort
     * @param request the request object
     * @return the actual sort object
     */
    private CurrentSort getCurrentSort(HttpServletRequest request) {
    	String currentSort=Constants.SORT_ASC;
    	CurrentSort sort = (CurrentSort)request.getSession().getAttribute(Constants.CURRENT_SORT);		// Check if the sort is in the session
    	
    	if (sort == null) {									// If null its the first time we are called so the upArrow is displaying
    		sort = new CurrentSort();						// Create the sort object
    		sort.setCurrentSort(Constants.SORT_DESC);		// set the sort to descending
    		sort.setSortImage(Constants.SORT_DOWN_IMG);		// set the sort image to the down image    		
    	}
    	else if (sort.getCurrentSort().equals(Constants.SORT_ASC)) {
    		
    		sort.setCurrentSort(Constants.SORT_DESC);		// set the sort to descending
    		sort.setSortImage(Constants.SORT_DOWN_IMG);		// set the sort image to the down image    		
    	}
    	else {
    		
    		sort.setCurrentSort(Constants.SORT_ASC);		// set the sort to ascending
    		sort.setSortImage(Constants.SORT_UP_IMG);		// set the sort image to the up image     		
    	}
    	request.getSession().setAttribute(Constants.CURRENT_SORT, sort);		// Set the sort object in the session
    	return(sort);
    }
    
    public ActionForward save(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
    	String userSelected = request.getParameter("tablecountries");
    	System.out.println("userSelected " + userSelected);
        return mapping.findForward(SUCCESS);
    }
    
    /**
     * When the user clicks the edit button on the main page then they are brought to this location.  The 
     * EditForm is gotten out of the session and is populated with all of the attributes associated to the 
     * user that was previously selected on the address list screen
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward edit(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
    	EditForm theEditForm = new EditForm();	
    	String idSelected = request.getParameter("studentid");							// Get the user selected from the radio buttons	
    	String forwardLocation=EDIT;													// Assume all is well and we will go to the edit screen
    	HttpSession theSession = request.getSession(true);								// Get the session object
    	
    	if (theSession != null) {														// Make sure session is not expired etc...
    		
    		try {
    			if (idSelected == null) {												// user did not select a radio button
        			forwardLocation=ERROR;												// set the flag so that we go the error JSP
        			request.setAttribute(Constants.EXCEPTION_REPORT, Constants.SELECT_RADIO_MSG);		// place the short text 	
    			}
    			else {
    	       		// Get complete student information using the id
            	    Student theStudent = StudentService.getCompleteStudentById(new Long(idSelected));	
            	    // Set basic address information in the form
            	    Address theAddress = theStudent.getStudentAddress();
            	    theEditForm.setName(theStudent.getStudentName());
            	    theEditForm.setStudentId(theStudent.getStudentId());
            	    theEditForm.setStreet(theAddress.getStreet());
            	    theEditForm.setCity(theAddress.getCity());
            	    theEditForm.setState(theAddress.getState());
            	    theEditForm.setCountry(theAddress.getCountry());
            	    theEditForm.setZip(theAddress.getZipcode());
            	    theEditForm=populateEmails(theEditForm,theStudent.getStudentEmailAddresses());	// Populate the email addresses on the form
            	    theEditForm=populatePhones(theEditForm,theStudent.getStudentPhoneNumbers());	// Populate the phone numbers on the form
            	    theEditForm=populateWebAddresses(theEditForm,theStudent.getStudentWebAddresses());
            	    // Get the student radio show information it is a HashSet
            	    theEditForm.setStudentRadioShows(theStudent.getStudentRadioShows());
            	    theEditForm.setStudentId(theStudent.getStudentId());							// Make sure that we have the student id populated
            	    theEditForm.setInformation(theStudent.getStudentInfo());				// Get our basic information
            	    theEditForm.setStudentType(theStudent.getStudentType());				// Get the student type information
            	    theSession.setAttribute("EditForm", theEditForm);						// make sure the object is in the session	
            		setAction("Edit");														// set the flag that indicates we are doing an edit
            		request.setAttribute(ACTION,getAction());								// put the value in the request for the jsp for displaying header
            		
            		// Construct a list of radio shows
            		List<RadioShow> radioShowList = new ArrayList<RadioShow>();
            		// Make sure our set of radio show is not null
            		if (theStudent.getStudentRadioShows() != null) {
            			// convert the set to a regular list
            			radioShowList = new ArrayList<RadioShow>(theStudent.getStudentRadioShows());
            			 
            		}

            		// Sort the array list based on the number of archive listens
            		Collections.sort(radioShowList, new RadioShowComparator(SortEnum.DESC));
            		
            		// Place the sorted list into the request
            		request.setAttribute("radioshows", radioShowList);   
            		
            		// This is the show list on the front of the display
            		request.setAttribute("radioshowsmall", radioShowList);
            		
            		theSession.setAttribute("SORTTYPE","DESCENDING");            		
    			}
    			
    		}catch(HibernateException e) {
    			forwardLocation=ERROR;																		// set the flag so that we go the error JSP
    			request.setAttribute(Constants.EXCEPTION_REPORT, e.toString());								// place the short text 
    			request.setAttribute(Constants.EXCEPTION, e);						    					// place the exception into the request
    			
    		}
    		
    	}
    	
        return mapping.findForward(forwardLocation);								// will go to edit or the error page
    }

    /**
     * populate the three possible URLs that are stored on the edit form
     * @param theEditForm	the current edit form object
     * @param the
     * @return	the EditForm object
     */
    EditForm populateWebAddresses(EditForm theEditForm,Set<Web> theWebAddrSet) {
    	Iterator<Web> it = theWebAddrSet.iterator();							// Get the iterator
    	int i=0;
    	while(it.hasNext()) {													// loop through the web address set
    		Web currentWeb = it.next();										// get the email object out of the iterator
    		switch (i) {
    		  case 0: 
    			  theEditForm.setUrlOne(currentWeb.getUrl());
    		    break;
    		  case 1: 
    			  theEditForm.setUrlTwo(currentWeb.getUrl());
    		    break;
    		  case 2: 
    			  theEditForm.setUrlThree(currentWeb.getUrl());
    		    break;

    		}  
    		i += 1;
    	}
    	return(theEditForm);
    }
   
    /**
     * the work phone or the home phone attribute is populated on the Edit form object
     * @param theEditForm	the current edit form object
     * @param theEmailSet	the set of phone numbers
     * @return	the EditForm object
     */
    EditForm populatePhones(EditForm theEditForm,Set<Phone> thePhoneSet) {
    	Iterator<Phone> it = thePhoneSet.iterator();							// Get the iterator
    	while(it.hasNext()) {													// loop through the email address set
    		Phone currentPhone = it.next();										// get the email object out of the iterator
    		if (currentPhone.getPhoneType().toUpperCase().startsWith("H")) { 	// check if its a work email
    			theEditForm.setHomePhone(currentPhone.getPhoneNumber());		// set the work email attribute on the form
    		}
    		else {
    			
    			theEditForm.setCellPhone(currentPhone.getPhoneNumber());		// Set the home email attribute
    		}
    	}
    	return(theEditForm);
    }
   
    /**
     * the work email or the home email attribute is populated on the Edit form object
     * @param theEditForm	the current edit form object
     * @param theEmailSet	the set of email addresses
     * @return	the EditForm object
     */
    EditForm populateEmails(EditForm theEditForm,Set<Email> theEmailSet) {
    	Iterator<Email> it = theEmailSet.iterator();						// Get the iterator
    	while(it.hasNext()) {												// loop through the email address set
    		Email currentEmail = it.next();									// get the email object out of the iterator
    		if (currentEmail.getEmailType().toUpperCase().startsWith("W")) {				// check if its a work email
    			theEditForm.setWorkEmail(currentEmail.getEmailAddr());		// set the work email attribute on the form
    		}
    		else {
    				
    			theEditForm.setHomeEmail(currentEmail.getEmailAddr());		// Set the home email attribute
    		}
    	}
    	return(theEditForm);
    }
    /**
     * Called from the Error.jsp when an error occurs perhaps other places
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward ok(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
          	
        return mapping.findForward(SUCCESS);
    }    
    
    /**
     * This method is called when the user presses the add button.  It will call the edit.jsp and
     * set the action as desired so that header appears as add.  It does a mapping.findforward the edit display
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward add(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
    	
		setAction("Add");														// set the flag that indicates we are doing an edit
		request.setAttribute(ACTION,getAction());								// put the value in the request for the JSP for displaying header
		request.getSession().setAttribute("EditForm", new EditForm());			// Clear out old values hanging around in the form
		// Construct a list of radio shows
		List<RadioShow> radioShowList = new ArrayList<RadioShow>();
		// The attribute still needs to be in the request so we dont have problems
		request.setAttribute("radioshows", radioShowList);    
		request.setAttribute("radioshowsmall", radioShowList);
        return mapping.findForward(EDIT);										// Add and Edit JSP are one and the same
    }
    
    /**
     * Prepares things for the delete of address information.  The forward goes to the delete page.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        
    	String forwardLocation = DELETE;
    	String idSelected = request.getParameter("studentid");
    	System.out.println("idSelected " + idSelected);
		if (idSelected == null) {												// user did not select a radio button
			forwardLocation=ERROR;												// set the flag so that we go the error JSP
			request.setAttribute(Constants.EXCEPTION_REPORT, Constants.SELECT_RADIO_MSG);			// place the short text 	
		}
		else {
	    	HttpSession theSession = request.getSession(true);										// Get the session object
	    	if (theSession != null) {																// Make sure session is not expired etc...
	    		DeleteForm theDeleteForm = (DeleteForm)theSession.getAttribute("DeleteForm");		// Get the delete form
	    		if (theDeleteForm == null) {
	    			theDeleteForm = new DeleteForm();
	    		}
	       		// Get complete student information using the id
        	    Student theStudent = StudentService.getCompleteStudentById(new Long(idSelected));		    		
	    		theDeleteForm.setName(theStudent.getStudentName());
	    		theDeleteForm.setStudentId(theStudent.getStudentId());
	    		theSession.setAttribute("DeleteForm",theDeleteForm);
	    	}
	    			
		}
		

        return mapping.findForward(forwardLocation);
    }

    /**
     * This will tell us if the user is doing an add or an edit since the same jps is used for both functions
     * @return string to tell jsp what header to display
     */
	public String getAction() {
		return action;
	}
    /**
     * This will tell us if the user is doing an add or an edit since the same jps is used for both functions
     * @param string to tell jsp what header to display
     */
	public void setAction(String action) {
		this.action = action;
	}    
}