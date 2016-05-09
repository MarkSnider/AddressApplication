package com.constants;

public class Constants {

	public static final String EXCEPTION_REPORT = "Exception_Report";		// The exception toString is placed in the request for easy display
	public static final String EXCEPTION_MESSAGE = "Exception_Message";		// places the exception message in the request	
	public static final String EXCEPTION = "Exception";						// places the actual exception into the request
	public static final String SELECT_RADIO_MSG = "It is necessary to select a radio button when doing taking this action (edit, delete or radio show).";
	public static final String SORT_UP_IMG = "upArrow.jpg";					// The actual image name for the up arrow
	public static final String SORT_DOWN_IMG = "downArrow.jpg";				// The actual image name for the down arrow
	public static final String SORT_ASC = "Ascending Sort";					// The request value for sort ascending
	public static final String SORT_DESC = "Descending Sort";				// The request value for sort ascending
	public static final String CURRENT_SORT = "Current Sort";				// The request value for the current sort
	public static final String STUDENT = "Student";							// The constant for the student information
	/* Search Constants */
	public static final String START = "START";
	public static final String END = "END";
	public static final String ANYWHERE = "ANYWHERE";
	public static final String EXACT = "EXACT";
	// Get the people that are radio shows guests using this constant
	public static final String RADIO_SHOW = "RADIO_SHOW";
	public final static String ACTION = "ACTION";		// Used as a request attribute to tell add/edit JSP what header to display
}
