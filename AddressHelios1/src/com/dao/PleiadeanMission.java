package com.dao;

/**
 * The basic DAO that maps to the PleiadeanMission table
 * @author mc28881
 *
 */
public class PleiadeanMission {
	
	// The key to the pleiadean_mission table
	private long pleiadean_missionId;
	
	// the number associated to the key word
	private long keyNumber1;
	
	// the number associated to the second key word in the table
	private long keyNumber2;
	
	// The line number associated to the text
	private long lineNumber;
	
	
	// The actual text for the pleiadean_mission1
	private String info;

	/**
	 * The key to the pleiadean_mission table
	 * @return the id associated to the table
	 */
	public long getPleiadean_missionId() {
		return pleiadean_missionId;
	}

	/**
	 * The key to the pleiadean_mission table
	 * @param the id associated to the table
	 */
	public void setPleiadean_missionId(long pleiadean_missionId) {
		this.pleiadean_missionId = pleiadean_missionId;
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
	 * @param the number associated to the Second key word in the line
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
	 * @param The line number associated to the text
	 */
	public void setLineNumber(long lineNumber) {
		this.lineNumber = lineNumber;
	}

	/**
	 * The actual text for PleiadeanMission
	 * @return the information with that row
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * The actual text for PleiadeanMission
	 * @return the information with that row
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * Get the line number and the text
	 */
	public String toString() {
		String text = "Line Number " + getLineNumber() + " " + getInfo();
		return(text);
	}	
	
}
