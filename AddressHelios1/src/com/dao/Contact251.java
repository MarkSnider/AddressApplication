package com.dao;

/**
 * The object associated to the Contact 251 table
 * @author User
 *
 */
public class Contact251 {

	// The key to the Contact 251 table
	private long contact251Id;
	
	// the number associated to the key word
	private long keyNumber1;
	
	// the number associated to the second key word in the table
	private long keyNumber2;
	
	// The line number associated to the text
	private long lineNumber;
	
	
	// The actual text for Contact 251
	private String info;

	/**
	 * The key to the Contact 251 table the id
	 * @return the id associated to the table
	 */
	public long getContact251Id() {
		return contact251Id;
	}
	
	/**
	 * The key to the Contact 251 table the id
	 * @param the id associated to the table
	 */
	public void setContact251Id(long contact251Id) {
		this.contact251Id = contact251Id;
	}
	
	/**
	 * first number that allows us to point to the associated keyword
	 * @return the number associated to the first key word in the line
	 */
	public long getKeyNumber1() {
		return keyNumber1;
	}
	
	/**
	 * first number that allows us to point to the associated keyword
	 * @param the number associated to the first key word in the line
	 */
	public void setKeyNumber1(long keyNumber1) {
		this.keyNumber1 = keyNumber1;
	}
	/**
	 * the Second number that allows us to point to the associated keyword
	 * @return the number associated to the Second key word in the line
	 */
	public long getKeyNumber2() {
		return keyNumber2;
	}
	/**
	 * the Second number that allows us to point to the associated keyword
	 * @return the number associated to the Second key word in the line
	 */
	public void setKeyNumber2(long keyNumber2) {
		this.keyNumber2 = keyNumber2;
	}

	
	/**
	 * The line number associated to the text
	 * @return The line number associated to the text
	 */
	public long getLineNumber() {
		return lineNumber;
	}

	/**
	 * The line number associated to the text
	 * @param lineNumber he line number associated to the text
	 */
	public void setLineNumber(long lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * Get the line number and the text
	 */
	public String toString() {
		String text = "Line Number " + getLineNumber() + " " + getInfo();
		return(text);
	}

	/**
	 * The actual text for Contact 251
	 * @return the information with that row
	 */
	public String getInfo() {
		return info;
	}
	
	/**
	 * The actual text for Contact 251
	 * @param the information with that row
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	
}
