package com.dao;

/**
 * Class that maps to the table of the same name
 * @author Mark Snider
 *
 */
public class State implements java.io.Serializable  {
	private long stateid;
	private String abbrev;
	private String selected;
	
	public long getStateid() {
		return stateid;
	}
	public void setStateid(long stateid) {
		this.stateid = stateid;
	}
	public String getAbbrev() {
		return abbrev;
	}
	public void setAbbrev(String abbrev) {
		this.abbrev = abbrev;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}

	
}
