package com.actions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.DateFormatter;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.constants.Constants;
import com.dao.RadioShow;
import com.dao.ShowDetail;
import com.dao.Student;
import com.forms.EditForm;
import com.forms.RadioShowForm;
import com.service.RadioShowService;
import com.service.StudentService;
import com.sorting.RadioShowComparator;
import com.sorting.RadioShowDateComparator;
import com.sorting.RadioShowLiveComparator;
import com.sorting.SortEnum;

/**
 * Used when an episode of the radio show is stored in the database
 * @author Mark Snider
 *
 */
public class RadioAction extends DispatchAction{
	   private final static String SUCCESS = "success";	// Find forward to take us to the main display
	   private final static String ADD= "add"; 			// When we want to add a new radio show
	   private final static String DELETE="delete";		// deletes a radio show
	   private final static String LIST="list";			// list the radio show information
	   private final static String GUEST="guest";       // list the radio show guests
	   private final static String SORTED="sorted";     // Sort the radio show information
	    /**
	     * List all of the radio show guests
	     * @param mapping
	     * @param form
	     * @param request
	     * @param response
	     * @return forward to the listing of the radio show information
	     * @throws Exception
	     */
	    public ActionForward listguest(ActionMapping mapping, ActionForm  form,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception {
	    	
	    	// Get all of the radio show guests
	    	List<Student> theList = StudentService.selectStudentsByStudentType(Constants.RADIO_SHOW);
	    	
	    	// place the radio show information in the request
	    	request.setAttribute("radioshows", theList); 
	    	
	    	// Go to the guest page
	    	return mapping.findForward(GUEST);	    	
	    }
	    
	    /**
	     * List all of the radio show information descending by show date
	     * @param mapping
	     * @param form
	     * @param request
	     * @param response
	     * @return forward to the listing of the radio show information
	     * @throws Exception
	     */
	    public ActionForward list(ActionMapping mapping, ActionForm  form,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception {
	    	
	    	// Get all of the radio show information
	    	List<RadioShow> theList = RadioShowService.selectRadioShowDes();
	    	
	    	// place the radio show information in the request
	    	request.setAttribute("radioshows", theList); 
	    	return mapping.findForward(LIST);
	    }
	    
	    /**
	     * Sort all of the radio show information based on the sort type
	     * @param mapping
	     * @param form
	     * @param request
	     * @param response
	     * @return forward to the listing of the radio show information
	     * @throws Exception
	     */
	    public ActionForward sortRadioShow(ActionMapping mapping, ActionForm  form,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception {
	    	
		    // Get the session
	        HttpSession theSession = request.getSession();
	        
	        // Get the edit form
	    	EditForm theEditForm = (EditForm)theSession.getAttribute("EditForm");	
	    	
	    	Long studentId = theEditForm.getStudentId();
       		// Get complete student information using the id
    	    Student theStudent = StudentService.getCompleteStudentById(studentId);	
    	    
    		// Construct a list of radio shows
    		List<RadioShow> radioShowList = new ArrayList<RadioShow>();
    		
    		// Make sure our set of radio show is not null
    		if (theStudent.getStudentRadioShows() != null) {
    			// convert the set to a regular list
    			radioShowList = new ArrayList<RadioShow>(theStudent.getStudentRadioShows());
    			 
    		}
    		
    		// Get the sort type
    		String sortType = (String)theSession.getAttribute("SORTTYPE");
    		
    		// Check if the sortType was null
    		if (sortType == null) {
        		// Sort the array list based on the number of archive listens sort descending by default
        		Collections.sort(radioShowList, new RadioShowComparator(SortEnum.DESC));   			
        		theSession.setAttribute("SORTTYPE","DESCENDING");
    		}
    		// sort type descending
    		else if (sortType.equals("DESCENDING")) {
    			// if its descending then switch to ascending
        		Collections.sort(radioShowList, new RadioShowComparator(SortEnum.ASC));   			
        		theSession.setAttribute("SORTTYPE","ASCENDING");    			
    		}
    		// sort type ascending
    		else if (sortType.equals("ASCENDING")) {
    			// if its ascending then switch to descending
        		Collections.sort(radioShowList, new RadioShowComparator(SortEnum.DESC));   			
        		theSession.setAttribute("SORTTYPE","DESCENDING");       			
    		}
    		

    		
	    	// place the radio show information in the request
	    	request.setAttribute("radioshows", radioShowList); 
	    	
    		// This is the show list on the front of the display
    		request.setAttribute("radioshowsmall", radioShowList);	 
    		
	    	return mapping.findForward(SORTED);
	    }	 
	    
	    /**
	     * Sort all of the radio show information based on the date.
	     * @param mapping
	     * @param form
	     * @param request
	     * @param response
	     * @return forward to the listing of the radio show information
	     * @throws Exception
	     */
	    public ActionForward sortRadioShowDate(ActionMapping mapping, ActionForm  form,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception {
	    	
		    // Get the session
	        HttpSession theSession = request.getSession();
	        
	        // Get the edit form
	    	EditForm theEditForm = (EditForm)theSession.getAttribute("EditForm");	
	    	
	    	Long studentId = theEditForm.getStudentId();
       		// Get complete student information using the id
    	    Student theStudent = StudentService.getCompleteStudentById(studentId);	
    	    
    		// Construct a list of radio shows
    		List<RadioShow> radioShowList = new ArrayList<RadioShow>();
    		
    		// Make sure our set of radio show is not null
    		if (theStudent.getStudentRadioShows() != null) {
    			// convert the set to a regular list
    			radioShowList = new ArrayList<RadioShow>(theStudent.getStudentRadioShows());
    			 
    		}
    		
    		// Get the sort type
    		String sortType = (String)theSession.getAttribute("SORTTYPE_DATE");
    		
    		// Check if the sortType was null
    		if (sortType == null) {
        		// Sort the array list based on the number of archive listens sort descending by default
        		Collections.sort(radioShowList, new RadioShowDateComparator(SortEnum.DESC));   			
        		theSession.setAttribute("SORTTYPE_DATE","DESCENDING");
    		}
    		// sort type descending
    		else if (sortType.equals("DESCENDING")) {
    			// if its descending then switch to ascending
        		Collections.sort(radioShowList, new RadioShowDateComparator(SortEnum.ASC));   			
        		theSession.setAttribute("SORTTYPE_DATE","ASCENDING");    			
    		}
    		// sort type ascending
    		else if (sortType.equals("ASCENDING")) {
    			// if its ascending then switch to descending
        		Collections.sort(radioShowList, new RadioShowDateComparator(SortEnum.DESC));   			
        		theSession.setAttribute("SORTTYPE_DATE","DESCENDING");       			
    		}
    		
    		
	    	// place the radio show information in the request
	    	request.setAttribute("radioshows", radioShowList); 
	    	
    		// This is the show list on the front of the display
    		request.setAttribute("radioshowsmall", radioShowList);	
    		    		
	    	return mapping.findForward(SORTED);
	    }	    
	    
	    
	    /**
	     * Sort all of the radio show information based on the live listens.
	     * @param mapping
	     * @param form
	     * @param request
	     * @param response
	     * @return forward to the listing of the radio show information
	     * @throws Exception
	     */
	    public ActionForward sortRadioShowLive(ActionMapping mapping, ActionForm  form,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception {
	    	
		    // Get the session
	        HttpSession theSession = request.getSession();
	        
	        // Get the edit form
	    	EditForm theEditForm = (EditForm)theSession.getAttribute("EditForm");	
	    	
	    	Long studentId = theEditForm.getStudentId();
       		// Get complete student information using the id
    	    Student theStudent = StudentService.getCompleteStudentById(studentId);	
    	    
    		// Construct a list of radio shows
    		List<RadioShow> radioShowList = new ArrayList<RadioShow>();
    		
    		// Make sure our set of radio show is not null
    		if (theStudent.getStudentRadioShows() != null) {
    			// convert the set to a regular list
    			radioShowList = new ArrayList<RadioShow>(theStudent.getStudentRadioShows());
    			 
    		}
    		
    		// Get the sort type
    		String sortType = (String)theSession.getAttribute("SORTTYPE_LIVE");
    		
    		// Check if the sortType was null
    		if (sortType == null) {
        		// Sort the array list based on the number of archive listens sort descending by default
        		Collections.sort(radioShowList, new RadioShowLiveComparator(SortEnum.DESC));   			
        		theSession.setAttribute("SORTTYPE_LIVE","DESCENDING");
    		}
    		// sort type descending
    		else if (sortType.equals("DESCENDING")) {
    			// if its descending then switch to ascending
        		Collections.sort(radioShowList, new RadioShowLiveComparator(SortEnum.ASC));   			
        		theSession.setAttribute("SORTTYPE_LIVE","ASCENDING");    			
    		}
    		// sort type ascending
    		else if (sortType.equals("ASCENDING")) {
    			// if its ascending then switch to descending
        		Collections.sort(radioShowList, new RadioShowLiveComparator(SortEnum.DESC));   			
        		theSession.setAttribute("SORTTYPE_LIVE","DESCENDING");       			
    		}
    		
    		
	    	// place the radio show information in the request
	    	request.setAttribute("radioshows", radioShowList); 
	    	
    		// This is the show list on the front of the display
    		request.setAttribute("radioshowsmall", radioShowList);	
    		    		
	    	return mapping.findForward(SORTED);
	    }	  
	    
	   /**
	    * Does the actual delete
	    * @param mapping
	    * @param form
	    * @param request
	    * @param response
	    * @return
	    * @throws Exception
	    */
	   public ActionForward deleteShow(ActionMapping mapping, ActionForm  form,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception {
		   
		    System.out.println("deleteShow ");
	        HttpSession theSession = request.getSession();			 // Get the session
	        
	    	// Get the edit form
	    	EditForm theEditForm = (EditForm)theSession.getAttribute("EditForm");	
	    	
	    	RadioShowForm theForm =  (RadioShowForm)form;			// Get the radio show form
	    	long radioShowId = theForm.getShowId();					// The show id was populated in the form earlier
    		RadioShowService.deleteRadioShow(radioShowId);			// Delete radio show from the database
    		List<RadioShow> radioShowList = new ArrayList<RadioShow>();	 // Construct a list of radio shows 
			boolean found = false;									// Set flag to false
			
    		// Make sure our set of radio show is not null
    		if (theEditForm.getStudentRadioShows() != null) {
    			// convert the set to a regular list
    			radioShowList = new ArrayList<RadioShow>(theEditForm.getStudentRadioShows());
    			
    			// find the radio show in our list, first get the iterator
    			Iterator<RadioShow> it = radioShowList.iterator();
    			
    			//loop through the iterator
    			while(it.hasNext() && found == false) {
    				
    				// Get the radio show object
    				RadioShow current = it.next();
    				
    				// compare the ids 
    				if (current.getShowId() == theForm.getShowId()) {
    					
    					// remove the radio show from the list 
    					radioShowList.remove(current);
    					// set flag to get out of the loop
    					found=true;
    				}
    			} 
    		}
    		
    		// Create a new HashSet
    		Set<RadioShow> radioSet = new HashSet<RadioShow>(0);
    		
    		// if we remove one from the list then make sure the edit form Hashset reflects that
    		if (found == true) {
    			
    			// since we modified things update our hashset with items in the list
    			radioSet = new HashSet<RadioShow>(radioShowList);
    			
    			// Set the hashset in the form commit to the database occurs when user presses ok on Edit
    			theEditForm.setStudentRadioShows(radioSet);
    			
    		}
    		request.setAttribute("radioshows", radioShowList);   	    	
	    	
	    	// Go back to the edit or the add screen Edit.jsp
	        return mapping.findForward(SUCCESS);
	    	
	    }
	   /**
	    * Forwards to the delete radio show jsp...
	    * @param mapping
	    * @param form
	    * @param request
	    * @param response
	    * @return
	    * @throws Exception
	    */
	   public ActionForward deleteprep(ActionMapping mapping, ActionForm  form,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception {
	    	
		    // Get the id that we use during the delete
	    	String id = (String)request.getParameter("radioid");
	    	
	    	// Cast the form to the radio show form
	    	RadioShowForm theForm = (RadioShowForm)form;
	    	
	    	// set the id on the form so we can use it later
	    	theForm.setShowId(Long.parseLong(id));
	    	
	    	// get the title from the request
	    	String title = (String)request.getParameter("title");
	    	
	    	// set the title on the form
	    	theForm.setTitle(title);
	    	
	    	// get the description
	    	String desc = (String)request.getParameter("desc");
	    	
	    	// set the description in the form
	    	theForm.setDescription(desc);
	    	
	    	// get the archive listens
	    	String arch = (String)request.getParameter("arch");  
	    	
	    	//set the value on the form
	    	theForm.setArchiveListens(arch);
	    	
	    	// get the live listens
	    	String live = (String)request.getParameter("live");  
	    	
	    	//set the value on the form
	    	theForm.setLiveListens(live); 	

	    	// get the date of the show
	    	String ldate = (String)request.getParameter("ldate");  
	 
	    	//set the value on the form
	    	theForm.setShowDate(ldate);
	    	return mapping.findForward(DELETE);
	    	
	    }
    /**
     * Called from the radio show form to save radio show information.  This is called when the user presses ok.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward OK(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
 
		// Get the edit form out of the session
		EditForm theEditForm = (EditForm)request.getSession().getAttribute("EditForm");	
		
		// Get the student id
		Long studentId = theEditForm.getStudentId();
		String theStudentId="";
		
		// Make sure we have a student id
		if (studentId == null) {
			// Get the string version
			theStudentId = studentId.toString();
		}
		
    	String dateSelected = request.getParameter("showdate");		// Get the date the user selected from the date picker
        RadioShow show = new RadioShow();							// Construct the radio show object
        RadioShowForm theForm = (RadioShowForm)form;				// Get our form 
        show.setTitle(theForm.getTitle());							// Set the title
        show.setDescription(theForm.getDescription());				// Get the description field				
        DateFormat df =null;
        Date theDate = null;
        try {
        	df = new SimpleDateFormat("MM/dd/yyyy"); 				// Get the date formatter
        	theDate = df.parse(dateSelected);						// Place the selected date into the date object
        }
        catch(ParseException e) {
        	df = new SimpleDateFormat("yyyy-dd-MM"); 				// Try another format
        	theDate = df.parse(dateSelected);						// Place the selected date into the date object
        }
        
        
        show.setListedDate(theDate);							// Set the date the user selected in the date picker
        show.setArchiveListens(new Integer(theForm.getArchiveListens()));		// Get the archive and the live listens
        show.setLiveListens(new Integer(theForm.getLiveListens()));			// Convert them to integers
        
        // set the radio show id
        long theId = theForm.getShowId();
        
        // set the radio show id into show object
        show.setShowId(theId);
               
        HttpSession theSession = request.getSession();
           	
    	// Get the set of radio shows
    	Set<RadioShow> theRadioShowSet = theEditForm.getStudentRadioShows();
    	
    	// Check if this show set is null
    	if (theRadioShowSet == null) {
    		
    		// Create the set
    		theRadioShowSet = new HashSet<RadioShow>();
    	}
    	
    	// Get the iterator for the current set of radio shows
    	Iterator<RadioShow> it = theRadioShowSet.iterator();
    	
    	boolean found = false;
    	
    	// loop while we have items and the one we are looking for is not found
    	while(it.hasNext() && found == false) {
    		
    		// Get an item out of the set
    		RadioShow current = it.next();
    		
    		// check if the item we are looking at matches the one edited
    		if (current.getShowId() == show.getShowId()) {
    			
    			// update all of our items to match what was on the form
    			current.setArchiveListens(show.getArchiveListens());
    			current.setLiveListens(show.getLiveListens());
    			current.setDescription(show.getDescription());
    			current.setTitle(show.getTitle());
    			current.setListedDate(show.getListedDate());
    			
    			// set the flag indicating we found the one we want
    			found=true;
    		}
    		
    	}
    	
    	// check if we never found the one we wanted it must have been an add
    	if (found == false) {
        	// Add the radio show to the set
        	theRadioShowSet.add(show);    		
    	}


		// Construct a list of radio shows
		List<RadioShow> radioShowList = new ArrayList<RadioShow>();
	
		// convert the set to a regular list
		radioShowList = new ArrayList<RadioShow>(theRadioShowSet);

		// Place the radio shows back into the request
		request.setAttribute("radioshows", radioShowList);
		
		// Place the radio shows back into the request
		request.setAttribute("radioshowsmall", radioShowList);	
		
    	// Go back to the edit or the add screen Edit.jsp
        return mapping.findForward(SUCCESS);
        
    } 
    
    /**
     * Called when the add button is pressed on the Edit.jsp.  This will
     * bring up the add radio show page.
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return forward to the add radio show page.
     * @throws Exception
     */
    public ActionForward add(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
    	
    	// check if form is null
    	if (form != null) {
    		// Get the radio show form
    		RadioShowForm theRadioSForm = (RadioShowForm)form;
    		// clear out all of the values
    		theRadioSForm.setArchiveListens("");
    		theRadioSForm.setDescription("");
    		theRadioSForm.setTitle("");
    		theRadioSForm.setLiveListens("");
    		theRadioSForm.setShowDate("");
    		theRadioSForm.setShowId(0L);
    	}
    	// GO back to the add/edit radio show jsp
    	return mapping.findForward(ADD);
    }

    /**
     * Goes to the edit radio show jsp which also functions as the add.
     * We parse all of the parameters off of the url and populate the form.
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
    	// Get the id associated to the radio show link the user clicked on the preceding page
    	String id = (String)request.getParameter("radioid");
    	
    	// Cast the form to the radio show form
    	RadioShowForm theForm = (RadioShowForm)form;
    	
    	// set the id on the form so we can use it later
    	theForm.setShowId(Long.parseLong(id));
    	
    	// get the title from the request
    	String title = (String)request.getParameter("title");
    	
    	// set the title on the form
    	theForm.setTitle(title);
    	
    	// get the description
    	String desc = (String)request.getParameter("desc");
    	
    	// set the description in the form
    	theForm.setDescription(desc);
    	
    	// get the archive listens
    	String arch = (String)request.getParameter("arch");  
    	
    	//set the value on the form
    	theForm.setArchiveListens(arch);
    	
    	// get the live listens
    	String live = (String)request.getParameter("live");  
    	
    	//set the value on the form
    	theForm.setLiveListens(live); 	

    	// get the date of the show
    	String ldate = (String)request.getParameter("ldate");  
 
    	//set the value on the form
    	theForm.setShowDate(ldate);
    	
    	
    	// GO back to the add/edit radio show jsp
    	return mapping.findForward(ADD);	
    }
}
