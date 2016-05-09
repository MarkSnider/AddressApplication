package com.constants;

public class SqlQuery {
	
	// The standard query for the display
	public static String SELECT_STUDENTS = "SELECT S.STUDENT_ID as studentId, S.Student_Name as studentName, S.STUDENT_TYPE as studentType, " +
			" S.STUDENT_INFO as studentInfo, A.ADDRESS_STATE as State, A.ADDRESS_CITY as City, A.ADDRESS_STATE, S.STUDENT_ADDRESS as addressId from Student S " +
			" INNER JOIN address A " +
			" ON S.STUDENT_ADDRESS=A.ADDRESS_ID order by S.Student_Name ASC";
	
	// Used in the Email Pop up
	public static String SELECT_EMAIL = " Select E.EMAIL_ID as emailId, E.EMAIL_TYPE as emailType, " +
			" E.EMAIL_ADDR as emailAddr from student_email SE " +
			" inner join email E on E.EMAIL_ID = " +
			" SE.EMAIL_ID and SE.STUDENT_ID = :studentNum";
	
	// Used in the filter drop down
	public static String SELECT_STUDENTS_BY_TYPE = "SELECT S.STUDENT_ID as studentId, S.Student_Name as studentName, S.STUDENT_TYPE as studentType, " +
	" S.STUDENT_INFO as studentInfo, A.ADDRESS_STATE as State, A.ADDRESS_CITY as City, A.ADDRESS_STATE, S.STUDENT_ADDRESS as addressId from Student S " +
	" INNER JOIN address A ON S.STUDENT_ADDRESS=A.ADDRESS_ID and S.STUDENT_TYPE = :studentType";	
	
	// Initial query for edit
	public static String SELECT_STUDENT_BY_ID = "SELECT S.STUDENT_ID as studentId, S.Student_Name as studentName, S.STUDENT_TYPE as studentType, " +
	" S.STUDENT_INFO as studentInfo, A.ADDRESS_STATE as State, A.ADDRESS_CITY as City, A.ADDRESS_STATE, A.ADDRESS_STREET as Street, " +
	" A.Address_Country as country, A.Address_ZipCode as zip, " + 
	" S.STUDENT_ADDRESS as addressId from Student S " +
	" INNER JOIN address A ON S.STUDENT_ADDRESS=A.ADDRESS_ID and S.STUDENT_ID = :studentId";
	
	// Query for the student type drop down
	public static String SELECT_STUDENT_TYPE = "select STUDENT_TYPE_ID as studenttypeid, STUDENT_TYPE as studentType from STUDENT_TYPE order by studentType asc";
	
	// Query for the State drop down
	public static String SELECT_STATES = "select STATE_ID as stateid, STATE_ABREV as abbrev from state";
	
	// Query for the phone information
	public static String SELECT_PHONE = "SELECT P.PHONE_ID as phoneId, P.PHONE_TYPE as phoneType, P.PHONE_NUMBER as phoneNumber " + 
										" from PHONE P " +
										" INNER JOIN " + 
										" STUDENT_PHONE SP " +
										" ON SP.PHONE_ID = P.PHONE_ID " +
										" and SP.STUDENT_ID= :studentNum ";
	
	// Query the WEB information
	public static String SELECT_WEB = "select W.WEB_URL as url, W.WEB_ID as webid from WEB W " +
										" INNER JOIN STUDENT_WEB SW " +
										" ON W.WEB_ID = SW.WEB_ID " + 
										" and SW.STUDENT_ID = :studentNum";		
	

}
