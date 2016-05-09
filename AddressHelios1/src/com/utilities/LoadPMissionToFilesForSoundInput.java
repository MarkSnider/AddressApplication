package com.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.dao.PleiadeanMission;

public class LoadPMissionToFilesForSoundInput extends LoadPleiadianMission {
	
	// Location of the output file text file one line per sentence
	public static String FILE_OUT_PATH = "C:\\heliosWorkspace\\AddressHelios1\\src\\com\\utilities\\";	
	
	// Create the output file for writing data out
	File outputFile;
	
	// Define the list of output information
	List<PleiadeanMission> outputList = new ArrayList<PleiadeanMission>();	
	
	// define the start of the range
	Long startRange;
	
	// define the end of the range of the information that we are interested in
	Long endRange;
	
	// Define the current position or line number that is being processed
	Long currentPosition;
	
	// We write data out to a series of files this is the current file name we are using for output
	String fileName;
	
	// Number of lines per each file
	int LINES_PER_FILE=40;
	
	/**
	 * This method saves the text data and the line number in our object and then places that information
	 * in a list which will be processed later to create the output files
	 * @param line the data read out of the file
	 * @param lineNumber the line number for the file
	 */
	public void saveInfo(String line, int lineNumber) {
		
		// Make a new object so that hibernate will do the add
		PleiadeanMission data = new PleiadeanMission();
		
		// Add the line data information and the number
		data.setInfo(line);
		
		// set the line number
		data.setLineNumber(lineNumber);
		
		// check if the line is in the desired range
		Long start = getStartRange();
		Long end = getEndRange();
		
		if (lineNumber >= start && lineNumber <= end) {
			// add the item to the list
			getOutputList().add(data);
			
			System.out.println(" data " + data.toString());	
		}	
	}
	
	/**
	 * Creates the files that are used for the sound, these are text files used to create the mp3s.
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void buildFiles() throws IOException, FileNotFoundException{
		// Get the list of data
		List<PleiadeanMission> theList = getOutputList();
		
		// start the line count at zero
		int lineCnt=0;
		
		// Create a filename like 600_610
		Long endLine = getStartRange()+LINES_PER_FILE;
		
		// Create the entire filename
		fileName=FILE_OUT_PATH+"PleiadianMission"+getStartRange() + "_" + endLine+".txt";		
		

		// Create the out put file user the buffered writer so that we can use the new line function
		BufferedWriter fos = new BufferedWriter(new FileWriter(fileName));
		
		// Move through the list
		for (PleiadeanMission current: theList) {

			// Reset the line count when it reaches ten
			if (lineCnt == LINES_PER_FILE) {
				
				// close the current file
				fos.close();
				
				// Create a filename like 600_610
				endLine = current.getLineNumber()+LINES_PER_FILE;
				
				// Build the complete file
				fileName=FILE_OUT_PATH+"PleiadianMission"+current.getLineNumber() + "_" + endLine+".txt";		
				
				// Open another DataOutputStream stream
				fos = new BufferedWriter(new FileWriter(fileName));
				
				// set to negative 1 so that when we increment we know its time to start a new file
				lineCnt=0;	
				System.out.println(" fileName " + fileName);
			}
			//System.out.println(" current " + current.toString());
			// Adding the period helps the speech software slow down
			//String data = current.getInfo()+".";
			String data = current.getInfo();
			
			boolean upperFound = false;				// assume we have not found the upper case character
			for (char c : data.toCharArray()) {		// convert the string to character array
			    if (Character.isUpperCase(c)) {		// check for the upper case
			        upperFound = true;				// if found break out of the loop
			        break;
			    }
			}			
			
			// Dont write out insignificant data
			if (data != null && data.length() > 2) {
				fos.write(data);
				fos.newLine(); fos.newLine();				
			}

			lineCnt++;
		}
		// close the last file
		fos.close();
	}
	
	
	/**
	 * Define the list of output information
	 * @return Define the list of output information
	 */
	public List<PleiadeanMission> getOutputList() {
		return outputList;
	}
	/**
	 * Define the list of output information
	 * @oaram Define the list of output information
	 */
	public void setOutputList(List<PleiadeanMission> outputList) {
		this.outputList = outputList;
	}
	/**
	 * define the start of the range
	 * @return define the start of the range
	 */
	public Long getStartRange() {
		return startRange;
	}
	/**
	 * define the start of the range
	 * @param define the start of the range
	 */
	public void setStartRange(Long startRange) {
		this.startRange = startRange;
	}

	/**
	 * define the end of the range of the information that we are interested in
	 * @return define the end of the range of the information that we are interested in
	 */
	public Long getEndRange() {
		return endRange;
	}
	/**
	 * define the end of the range of the information that we are interested in
	 * @param define the end of the range of the information that we are interested in
	 */
	public void setEndRange(Long endRange) {
		this.endRange = endRange;
	}
	public static void main(String[] args) {
		LoadPMissionToFilesForSoundInput theLoad = new LoadPMissionToFilesForSoundInput();
		theLoad.setStartRange(new Long(7000));
		theLoad.setEndRange(new Long(8000));
		theLoad.processFile();
		try {
			theLoad.buildFiles();
		} catch (FileNotFoundException e) {
	
			e.printStackTrace();
		} catch (IOException e) {
		
			e.printStackTrace();
		}
	}	
}
