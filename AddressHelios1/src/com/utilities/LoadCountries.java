package com.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class LoadCountries {

	File countriesFile;					// File object created for the countries
	String path;						// the path to the files
	List<String> countriesList;			// List of countries abbreviations
	
	public static String COUNTRIESFILE = "C:\\heliosWorkspace\\AddressHelios1\\src\\com\\utilities\\Countries.txt";		// Current location of the state file
	
	/**
	 * Construct the list of states
	 * @param path
	 */
	public LoadCountries(String path) {
		this.path=path;
		
	}
	
	public File getCountriesFile() {
		return countriesFile;
	}

	public void setCountriesFile(File countriesFile) {
		this.countriesFile = countriesFile;
	}

	public List<String> getCountriesList() {
		return countriesList;
	}

	public void setCountriesList(List<String> countriesList) {
		this.countriesList = countriesList;
	}

	/**
	 * Loop through the file populate the list of states
	 */
	public void processFile() {
		countriesFile = new File(path);
		try {
			RandomAccessFile theRAFile = new RandomAccessFile(countriesFile, "r");
			countriesList = new ArrayList<String>();
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
			countriesList.add(line);		// Add the state to the list
		}
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}


	public static void main(String[] args) {
		LoadCountries theCountries = new LoadCountries(LoadCountries.COUNTRIESFILE);
		theCountries.processFile();
		System.err.println(" theCountries.getCountriesList().size() " + theCountries.getCountriesList().size());

	}

}
