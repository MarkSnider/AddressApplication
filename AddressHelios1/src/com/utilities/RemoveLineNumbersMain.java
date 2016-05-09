package com.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.RandomAccessFile;

public class RemoveLineNumbersMain {
	
	// File object for creation the text file
	File inputFile;				
	
	public static String FILENAME = "ContactReport31_";
	// The long directory path and actual file name on the disk
	public static String PLEIADEAN_FILE = "C:\\BlogTalk\\BillyMeier\\PleiadianPlejarenContactReports\\Contact Report 0031\\ContactReport31.txt";		

	// Location of the output file text file one line per sentence
	public static String FILE_OUT_PATH = "C:\\BlogTalk\\BillyMeier\\PleiadianPlejarenContactReports\\Contact Report 0031\\";	
	/**
	 * Just basically creates the input file which we read all of the data from
	 * @return file object point to our file
	 */
	public File openInputFile() throws Exception{
		// Open the input file
		inputFile = new File(PLEIADEAN_FILE);
		
		// return to the caller
		return(inputFile);
	}
	/**
	 * Creates the entire file which will be used for outputing information
	 * @param startLine - the first line 
	 * @param endLine - the last line
	 * @return the formatted file name
	 */
	public String getOutPutFilePath() {
		// Create the entire filename
		String fileName=FILE_OUT_PATH+ FILENAME + "NO_LINE_NUMBERS" + ".txt";	
		return(fileName);
	}	
	
	public void buildOutPutFile() throws Exception {
		// Creates the input file
		File theFile = openInputFile();
		
		// Open a random access file for reading the file from the disk
		RandomAccessFile theRAFile = new RandomAccessFile(theFile, "r");	
		BufferedWriter theOutFile = null;
		// Create the file name
		String fileName = getOutPutFilePath();
		
		// Create the buffered writer
		theOutFile = new BufferedWriter(new FileWriter(fileName));
		
		String badChars[] = {"1","2","3","4","5","6","7","8","9","0"};
		
		
		String line = "";
		while(line != null) {							// Loop through the list
			line = theRAFile.readLine();				// read the next line
			String goodLine=line;						// set the good line equal to the line in the file
			// Loop through the line and replace all of the bad characters
			for (int i=0; i < badChars.length; i++) {
				
				if (goodLine != null) {
					// take out the beginning line numbers
					goodLine = goodLine.replace(badChars[i], "");	
					System.out.println("goodLine" + goodLine);					
				}

					
			}
			// Write the good file out
			writeFile(goodLine, theOutFile);			
		}
		theRAFile.close();								// close the reader 
		theOutFile.close();								// close the writer		
	}
	
	/**
	 * Creates the entire file which will be used for output information
	 * @param startLine - the first line 
	 * @param endLine - the last line
	 * @return the formatted file name
	 */
	public String getOutPutFilePath(int startLine, int endLine) {
		// Create the entire filename
		String fileName=FILE_OUT_PATH+startLine + "_" + endLine+".txt";	
		return(fileName);
	}	
	/**
	 * Appends the line to the end of the output file and also writes out a new line.
	 * @param line - the string of data
	 * @param theOutFile - the actual file object
	 * @throws Exception
	 */
	private void writeFile(String line, BufferedWriter theOutFile) throws Exception {
		theOutFile.append(line);			// write the line out
		theOutFile.newLine();				// skip to the next line		
	}	
	public static void main(String[] args) {
		RemoveLineNumbersMain theFiles = new RemoveLineNumbersMain();
		try {
			theFiles.buildOutPutFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
}
