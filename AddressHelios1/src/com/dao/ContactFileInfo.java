package com.dao;

/**
 * Data object for storing the a line of data in the contact note
 * @author User
 *
 */
public class ContactFileInfo {
	
	// The complete path to the location of the uploaded file
	String filePath;
	
	// This is the line of data or text from the contact note
	String data;
	
	// Not the true line number in the contact notes but rather just counter
	String lineNumber;

	
	public ContactFileInfo(String data) {
		super();
		this.data = data;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	
	

}
