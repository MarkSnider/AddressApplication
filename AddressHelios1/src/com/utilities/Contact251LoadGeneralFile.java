package com.utilities;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dao.Contact251;
import com.service.Contact251Service;

/**
 * Creates the basic template or outline for an episode associated to contact251.
 * That includes introductory text before each section and the name of the associated mp3
 * that will be played.
 * @author mc28881
 *
 */
public class Contact251LoadGeneralFile extends LoadContact251 {
	
	// Construct the list that will store the desired data between start and end range
	private List<Contact251> theDataList = new ArrayList<Contact251>();
	
	// the start number of lines that we are interested in
	private Long start;
	
	// the ending number of lines that we are interested in
	private Long end;
	
	// Pointer to the file that is generated that contains the script so to speak for show
	public static String OUT_FILE = "C:\\heliosWorkspace\\AddressHelios1\\src\\com\\utilities\\GalacticHistoryBillyMeierPart.txt";		
	
	
	/**
	 * Can be overridden by classes that extend the current class
	 * @return the file that will be used for output
	 */
	public String getOutFile() {
		return(OUT_FILE);
	}
	/**
	 * Over ride this method and just save off the lines information
	 * @param line the data read out of the file
	 * @param lineNumber the line number for the file
	 */
	public void saveInfo(String line, int lineNumber) {
		
		// Make a new object so that hibernate will do the add
		Contact251 the251Data = new Contact251();
		
		// Add the line data information and the number
		the251Data.setInfo(line);
		
		// store off the line number
		the251Data.setLineNumber(lineNumber);
		
		// Make sure that information is in the range that we want
		if (getStart() <= the251Data.getLineNumber() && getEnd() >= the251Data.getLineNumber()) {
			// add the object
			theDataList.add(the251Data);			
		}


		//System.out.println(" the251Data " + the251Data.toString());		
	}
	
	//throws IOException, FileNotFoundException
	public void generateOutPutFile() throws IOException, FileNotFoundException {
		
		// Create the out put file user the buffered writer so that we can use the new line function
		BufferedWriter out = new BufferedWriter(new FileWriter(getOutFile()));
		long start = this.getStart();
		long end = getStart()+10;
		
		// Number of entries into the file generated
		int totalCnt=1;		
		
		// We need to enter the filename into the file each ten lines
		int processedLines=0;
		
		// index into the list
		int index=0;
		
		// Write the data into the output file
		writeData(totalCnt, start, end, out, index);
		
		// loop through the list of data
		for (Contact251 current: theDataList) {
			
			// Make an entry into the file for every ten actual text lines and dont go past 20
			if (processedLines == 10 && totalCnt < 20) {
				
				// Count the number of sections generated
				totalCnt++;
				
				start=current.getLineNumber();
				end = current.getLineNumber()+10;
				
				// Write the data into the output file
				writeData(totalCnt, start, end, out, index);
				
				// Reset the counter
				processedLines=0;

				
			}

			
			System.err.println("current.toString() " + current.toString());
			// increment the lines
			processedLines++;
			index++;
		}
		// Close the wfile when done
		out.close();
	}
	
	/**
	 * Writes the data out into the file.  The information consists of a couple of 
	 * lines of information from the contact and finally the name of the associated mp3.
	 * @param totalCnt the number of entries placed into the file
	 * @param start starting line number for this section
	 * @param end ending line number for this section
	 * @param out the Buffered Writer object which contains the write line code
	 * @param index the index into our list of data
	 */
	private void writeData(int totalCnt, long start, long end, BufferedWriter out, int index) 
		throws IOException, FileNotFoundException {
		
		// write a couple of lines of text
		out.write(totalCnt + ". " + getTheDataList().get(index).getInfo() + ".");
		out.newLine();
		out.write(getTheDataList().get(index+1).getInfo() + ". ");
		out.newLine();
		
		// Create the basic filename
		String fileName = "Contact251_" + start + "_" + end + ".mp3";
		
		// write the filename out
		out.write(fileName);
		
		// skip a couple of lines
		out.newLine();	out.newLine(); 
		
	}
	/**
	 * the start number of lines that we are interested in
	 * @return beginning of lines for this episode
	 */
	public Long getStart() {
		return start;
	}
	
	/**
	 * the start number of lines that we are interested in
	 * @param beginning of lines for this episode
	 */
	public void setStart(Long start) {
		this.start = start;
	}
	
	/**
	 * Last line we will cover in this episode
	 * @return Last line we will cover in this episode
	 */
	public Long getEnd() {
		return end;
	}
	
	/**
	 *  Last line we will cover in this episode
	 * @param Last line we will cover in this episode
	 */
	public void setEnd(Long end) {
		this.end = end;
	}

	/**
	 * The data list of information that we are currently processing.
	 * @return the list of data
	 */
	public List<Contact251> getTheDataList() {
		return theDataList;
	}
	
	/**
	 * The data list of information that we are currently processing.
	 * @param the list of data
	 */
	public void setTheDataList(List<Contact251> theDataList) {
		this.theDataList = theDataList;
	}

	public static void main(String[] args) {
		Contact251LoadGeneralFile theLoad = new Contact251LoadGeneralFile();
		theLoad.setStart(new Long(600));
		theLoad.setEnd(new Long(800));
		theLoad.processFile();
		try {
			theLoad.generateOutPutFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	
}
