package com.utilities;

import java.io.File;

import com.dao.Contact251;
import com.dao.PleiadeanMission;
import com.service.Contact251Service;
import com.service.PleiadeanMissionService;

/**
 * This class reads from the the_pleiadean_mission_ebook.txt file
 * and adds records to appropriate database table where this information is stored.
 * @author User
 *
 */
public class LoadPleiadianMission extends LoadContact251{
	
	// Location of text file
	public static String PLEIADEAN_FILE = "C:\\heliosWorkspace\\AddressHelios1\\src\\com\\utilities\\the_pleiadean_mission_ebook.txt";		

	/**
	 * Just basically creates the input file
	 */
	public void createInputFile() {
		inputFile = new File(PLEIADEAN_FILE);
	}
	
	/**
	 * This method saves the data for this specific line number and line
	 * @param line the data read out of the file
	 * @param lineNumber the line number for the file
	 */
	public void saveInfo(String line, int lineNumber) {
		
		// Make a new object so that hibernate will do the add
		PleiadeanMission theData = new PleiadeanMission();
		
		// Add the line data information and the number
		theData.setInfo(line);
		
		theData.setLineNumber(lineNumber);
		PleiadeanMissionService.savePleiadeanMission(theData);
		lineNumber++;									// increment the counter
		System.out.println(" PleiadeanMission " + theData.toString());		
	}	
	
	public static void main(String[] args) {
		LoadPleiadianMission theLoader = new LoadPleiadianMission();
		theLoader.processFile();
	}
}
