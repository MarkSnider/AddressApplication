package com.dao;

/**
 * One to One relationship with the student
 * @author Mark Snider
 *
 */
public class Partner implements java.io.Serializable {
	
	private long studentId;				// one to one relationship points to the student
	private String partnerName;				// equivalent to a spouse name

	

	public Partner() {
		
	}
	public Partner(String name) {
		this.partnerName=name;
	}
	

	
	public long getStudentId() {
		return studentId;
	}
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	/**
	 * equivalent to a spouse name
	 * @return the name of the significant other
	 */
	public String getPartnerName() {
		return partnerName;
	}
	
	/**
	 * equivalent to a spouse name
	 * @param the name of the significant other
	 */
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

}
