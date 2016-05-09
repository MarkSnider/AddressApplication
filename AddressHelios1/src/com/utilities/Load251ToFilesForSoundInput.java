package com.utilities;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.dao.Contact251;
import com.service.Contact251Service;

/** 
 * This class takes the raw input file For contact 251 and writes out a series of 
 * 10 line files starting at the given line number.  These files are then used as input into the 
 * software that creates MP3 that are played during the radio show.  These files are just made up
 * of raw text.
 * @author User
 *
 */
public class Load251ToFilesForSoundInput extends LoadContact251{
	// Location of the output file text file one line per sentence
	public static String FILE_OUT_PATH = "C:\\heliosWorkspace\\AddressHelios1\\src\\com\\utilities\\";		
	
	// Create the output file for writing data out
	File outputFile;
	
	// Define the list of output information
	List<Contact251> outputList = new ArrayList<Contact251>();

	// define the start of the range
	Long startRange;
	
	// define the end of the range of the information that we are interested in
	Long endRange;
	
	// Define the current position or line number that is being processed
	Long currentPosition;
	
	// We write data out to a series of files this is the current file name we are using for output
	String fileName;
	
	/**
	 * This method saves the text data and the line number in our object and then places that information
	 * in a list which will be processed later to create the output files
	 * @param line the data read out of the file
	 * @param lineNumber the line number for the file
	 */
	public void saveInfo(String line, int lineNumber) {
		
		// Make a new object so that hibernate will do the add
		Contact251 the251Data = new Contact251();
		
		// Add the line data information and the number
		the251Data.setInfo(line);
		
		// set the line number
		the251Data.setLineNumber(lineNumber);
		
		// check if the line is in the desired range
		Long start = getStartRange();
		Long end = getEndRange();
		
		if (lineNumber >= start && lineNumber <= end) {
			// add the item to the list
			getOutputList().add(the251Data);
			//System.out.println(" the251Data " + the251Data.toString());	
		}	
	}
	
	public void buildFiles() throws IOException, FileNotFoundException{
		// Get the list of data
		List<Contact251> theList = getOutputList();
		
		// start the line count at zero
		int lineCnt=0;
		
		// Create a filename like 600_610
		Long endLine = getStartRange()+10;
		
		// Create the entire filename
		fileName=FILE_OUT_PATH+getStartRange() + "_" + endLine+".rtf";		
		
		// Create the data output stream
		DataOutputStream fos = new DataOutputStream(new FileOutputStream(fileName));
		
		// Move through the list
		for (Contact251 current: theList) {

			// Reset the line count when it reaches ten
			if (lineCnt == 10) {
				
				// close the current file
				fos.close();
				
				// Create a filename like 600_610
				endLine = current.getLineNumber()+10;
				
				// Build the complete file
				fileName=FILE_OUT_PATH+current.getLineNumber() + "_" + endLine+".rtf";		
				
				// Open another DataOutputStream stream
				fos = new DataOutputStream(new FileOutputStream(fileName));
				
				// set to negative 1 so that when we increment we know its time to start a new file
				lineCnt=0;	
				System.out.println(" fileName " + fileName);
			}
			//System.out.println(" current " + current.toString());
			// Adding the period helps the speech software slow down
			fos.writeUTF(current.getInfo()+".");
			fos.writeUTF("\n\n");
			lineCnt++;
		}
		// close the last file
		fos.close();
	}

	/**
	 * Create the output file for writing data out
	 * @return Create the output file for writing data out
	 */
	public File getOutputFile() {
		return outputFile;
	}
	/**
	 * Create the output file for writing data out
	 * @param Create the output file for writing data out
	 */
	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	/**
	 * Define the list of output information
	 * @return Define the list of output information
	 */
	public List<Contact251> getOutputList() {
		return outputList;
	}
	/**
	 * Define the list of output information
	 * @oaram Define the list of output information
	 */
	public void setOutputList(List<Contact251> outputList) {
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

	/**
	 * The current position in the file
	 * @return
	 */
	public Long getCurrentPosition() {
		return currentPosition;
	}
	
	/**
	 * he current position in the file
	 * @param currentPosition
	 */
	public void setCurrentPosition(Long currentPosition) {
		this.currentPosition = currentPosition;
	}

	/**
	 * We write data out to a series of files this is the current file name we are using for output
	 * @return We write data out to a series of files this is the current file name we are using for output
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * We write data out to a series of files this is the current file name we are using for output
	 * @param fileName We write data out to a series of files this is the current file name we are using for output
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public static void main(String[] args) {
		Load251ToFilesForSoundInput theLoad = new Load251ToFilesForSoundInput();
		theLoad.setStartRange(new Long(600));
		theLoad.setEndRange(new Long(800));
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
