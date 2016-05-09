package com.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import com.dao.Contact251;
import com.service.Contact251Service;

/**
 * This class reads from the Contact251 file and stores that information
 * in the associated table.
 * @author User
 *
 */
public class LoadContact251 {
	File inputFile;						// File object created for the countries
	String path;							// the path to the files
	List<Contact251> contact251List;		// List of contact 251 data objects
	
	// Location of text file
	public static String CONTACT251_FILE = "C:\\heliosWorkspace\\AddressHelios1\\src\\com\\utilities\\Contact251.txt";		
	
	/**
	 * Just basically creates the input file this can be overridden by 
	 */
	public void createInputFile() {
		inputFile = new File(CONTACT251_FILE);
	}
	/**
	 * Loop through the file populate the list of states
	 */
	public void processFile() {
		
		// Create the file for reading the text information
		createInputFile();
		
		try {
			// Open a random access file for reading the file from the disk
			RandomAccessFile theRAFile = new RandomAccessFile(getInputFile(), "r");
			
			String line="";
			try {
				int lineNumber=1;								// We keep track of the line numbers
				line = theRAFile.readLine();					// Read the first record out of the file
								
				while(line != null) {							// Loop through the list
					line = theRAFile.readLine();				// read the next line
					
					// Make sure the line is not empty there are some empty lines in the file
					if (line != null && line.length() > 0) {
						
						// Check for a multi-line situation
						if (line.contains(".") == true) {
							// Create the delimiter array
							String delims = "[.]";
							
							// Split the line into an array
							String[] data = line.split(delims);
							
							// loop through the array
							for (int i=0; i < data.length; i++) {
								String lData= data[i];					// Get the single line of information
								saveInfo(lData, lineNumber);			// save the data
								lineNumber++;							// increment the line number	
							}
						}
						else {
							// save that line from the file and the associated line number in the database
							saveInfo(line, lineNumber);
							
							lineNumber++;									// increment the counter							
						}

						
					}
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
	 * This method saves the data for this specific line number and line
	 * @param line the data read out of the file
	 * @param lineNumber the line number for the file
	 */
	public void saveInfo(String line, int lineNumber) {
		
		// Make a new object so that hibernate will do the add
		Contact251 the251Data = new Contact251();
		
		// Add the line data information and the number
		the251Data.setInfo(line);
		
		the251Data.setLineNumber(lineNumber);
		Contact251Service.saveContact251(the251Data);	// Add data to database
		lineNumber++;									// increment the counter
		System.out.println(" the251Data " + the251Data.toString());		
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * Gets the list of contact 251 objects
	 * @return a list of contact 251 objects
	 */
	public List<Contact251> getContact251List() {
		return contact251List;
	}
	
	/**
	 * Sets the list of contact 251 objects
	 * @return a list of contact 251 objects
	 */
	public void setContact251List(List<Contact251> contact251List) {
		this.contact251List = contact251List;
	}

	/**
	 * Gets the path to the input file
	 * @return the file object that is being opened
	 */
	public File getInputFile() {
		return inputFile;
	}
	/**
	 * Gets the path to the input file
	 * @param the file object that is being opened
	 */
	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}

	public static void main(String[] args) {
		LoadContact251 theProcessor = new LoadContact251();
		theProcessor.processFile();
		

	}
}
