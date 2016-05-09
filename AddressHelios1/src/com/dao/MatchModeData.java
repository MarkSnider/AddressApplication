/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dao;

/**
 * Used for support the search types Anywhere, start, exact, end
 * @author Mark Snider
 */
public class MatchModeData implements java.io.Serializable  {

    private String matchMode;

    public MatchModeData(String matchMode)
    {
    	this.matchMode=matchMode;
    }

	public String getMatchMode() {
		return matchMode;
	}

	public void setMatchMode(String matchMode) {
		this.matchMode = matchMode;
	}




}

