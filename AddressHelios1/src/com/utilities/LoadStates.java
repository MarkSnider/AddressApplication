package com.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class LoadStates {

	File stateFile;						// File object created for the states
	String path;						// the path to the files
	List<String> stateAbbrevList;		// List of state abbreviations
	
	public static String STATEFILE = "C:\\heliosWorkspace\\AddressHelios1\\src\\com\\utilities\\StateAbbr.txt";		// Current location of the state file
	
	/**
	 * Construct the list of states
	 * @param path
	 */
	public LoadStates(String path) {
		this.path=path;
		
	}
	
	/**
	 * Loop through the file populate the list of states
	 */
	public void processFile() {
		stateFile = new File(path);
		try {
			RandomAccessFile theRAFile = new RandomAccessFile(stateFile, "r");
			stateAbbrevList = new ArrayList<String>();
			String line="";
			try {
				line = theRAFile.readLine();					// Read the first record out of the file
				addLineToList(line);							// Add the state to the list								
				while(line != null) {							// Loop through the list
					line = theRAFile.readLine();				// read the next line
					addLineToList(line);						// add the line to the list
				}
				theRAFile.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Add a state abbreviation to the list
	 * @param line the state that is coming from the file
	 */
	private void addLineToList(String line) {
		if (line != null) {					// Check for a null state
			stateAbbrevList.add(line);		// Add the state to the list
		}
	}
	public List<String> getStateAbbrevList() {
		return stateAbbrevList;
	}

	public void setStateAbbrevList(List<String> stateAbbrevList) {
		this.stateAbbrevList = stateAbbrevList;
	}
	public File getStateFile() {
		return stateFile;
	}
	public void setStateFile(File stateFile) {
		this.stateFile = stateFile;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}


	public static void main(String[] args) {
		LoadStates theStates = new LoadStates(LoadStates.STATEFILE);
		theStates.processFile();
		System.err.println(" theStates.getStateAbbrevList().size() " + theStates.getStateAbbrevList().size());

	}

}
