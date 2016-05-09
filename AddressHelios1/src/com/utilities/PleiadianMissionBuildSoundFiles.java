package com.utilities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.RandomAccessFile;

/**
 * Build the text files that will ultimately be used to generate sound files.
 * This class will start at a specified line number and create text
 * files that are 40 lines long until it reaches the last line.
 * @author User
 *
 */
public class PleiadianMissionBuildSoundFiles {
	
	public static String OUTFILE_BASENAME = "ContactReport59_NOLINENUMBERS_";
	// File object for creation the text file
	File inputFile;				
	
	// The long directory path and actual file name on the disk
	public static String PLEIADEAN_FILE = "C:\\BlogTalk\\BillyMeier\\PleiadianPlejarenContactReports\\ContactReport0059\\part2\\Contact59_NOLINENUMBERS.txt";		

	// Location of the output file text file one line per sentence
	public static String FILE_OUT_PATH = "C:\\BlogTalk\\BillyMeier\\PleiadianPlejarenContactReports\\ContactReport0059\\part2\\";	
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
	public String getOutPutFilePath(int startLine, int endLine) {
		// Create the entire filename
		String fileName=FILE_OUT_PATH+startLine + "_" + endLine+".txt";	
		return(fileName);
	}
	/**
	 * Creates the output file which will be for about 40 lines of text typically
	 * @param fileName the complete path name to the file
	 * @return fos - which is a BufferedWriter class used to output data
	 * @throws Exception
	 */
	public BufferedWriter getOutPutFile(String fileName) throws Exception {
		
		// Create the out put file user the buffered writer so that we can use the new line function
		BufferedWriter fos = new BufferedWriter(new FileWriter(fileName));	
		
		return(fos);
	}
	
	/**
	 * Opens the input text file. Reads the records in the file
	 * until we get to the starting line.
	 * Writes a line into the text file.  Reads another line
	 * until we reach forty records.  Closes and write out the file.
	 * Repeats the process until done.
	 * @param startLine - this could be 7000 if we are starting to create
	 * @param linesPerFile - the number of lines in each file.  Typically this will be forty
	 * @param endLine - the last line in the file that we want to process. Typically
	 * we will process 1000 lines in the file at a time.  So we might go from 7000 to 8000
	 * 
	 */
	public void buildSoundFiles(int startLine, 
								int linesPerFile,
								int endLine) 
								throws Exception{
		// Creates the input file
		File theFile = openInputFile();
		
		// Open a random access file for reading the file from the disk
		RandomAccessFile theRAFile = new RandomAccessFile(theFile, "r");	
		BufferedWriter theOutFile = null;
		String line = "";
		int lineCnt=1;									// start the line count at zero
		while(line != null) {							// Loop through the list
			line = theRAFile.readLine();				// read the next line
			if (lineCnt >= startLine) {					// Check if we are at our starting point.
				
				int currentCnt=0;
				// Create the output file using something like 7000_7040.text
				int lastLine =lineCnt+linesPerFile-1;
				
				// Create the file name
				String fileName = getOutPutFilePath(lineCnt, lastLine);
				
				// Create the buffered writer
				theOutFile = new BufferedWriter(new FileWriter(fileName));
				
				while (currentCnt < linesPerFile) {		// Loop for the number of lines per file
					
					System.out.println("line " + line);
					writeFile(line, theOutFile);		// Write the line into the output file 
					currentCnt++;						// increment our counter for the lines in this file
					lineCnt++;							// The total line counter
					line = theRAFile.readLine();		// read the next line
				}
				
				writeFile(line, theOutFile);			// Write the line into the output file 
				theOutFile.close();						// close the current input file
				
				// Rip out those weird question marks that appear where ' are found etc...
				int theStartLine =lineCnt-linesPerFile;	// We have processed the file so we have subtract to get a good line count
				
				// Pull out those strange ?
				replaceChars(fileName, theStartLine,lastLine);
				currentCnt=0;							// reset the line counter
			}
			//lineCnt++;								// increment the line count
			if (lineCnt >= endLine) {					// We are on the last line that we want to process
				break;									// So get out of the loop
			}
		}
		
		theRAFile.close();								// Close the file when done processing
		
	}

	/**
	 * This method just replaces ? that are showing up in strange places with the empty string
	 * @param fileName - the complete path to the original file
	 * @param lineCnt  - Our current position in the file
	 * @param lastLine - The last position in the file
	 * @throws Exception
	 */
	private void replaceChars(String fileName, int lineCnt, int lastLine) throws Exception{
		

		// Create a reader of the file
		BufferedReader theReader = new BufferedReader(new FileReader(fileName));
		
		// Create a final good out put file
		String finalFileName=FILE_OUT_PATH+OUTFILE_BASENAME+lineCnt + "_" + lastLine+ ".txt";
		
		// Create the writer for putting data into the file that has no weird ? marks in it
		BufferedWriter theWriter = new BufferedWriter(new FileWriter(finalFileName));
		
		String line = "";								// Define our output line
		while(line != null) {							// Loop through the list
			line = theReader.readLine();				// read the next line
			if (line != null) {							// make sure the line is not null
				String goodLine = line.replace("?", "");	// take out the strange ?
				writeFile(goodLine, theWriter);				// Write the good file out		
			}

		}
		
		theReader.close();								// close the reader 
		theWriter.close();								// close the writer
		File theOriginalFile = new File(fileName);		// Original file with weird characters
		theOriginalFile.delete();						// Delete the file with the strange characters
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
		PleiadianMissionBuildSoundFiles theFiles = new PleiadianMissionBuildSoundFiles();
		try {
			theFiles.buildSoundFiles(1,75, 20000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
