package com.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads the values from the txt file that contain the current student types
 * Friend, Family, radio show guest or stamper
 * @author User
 *
 */
public class LoadStudentTypes {

		File studentTypeFile;				// File object created for the student types
		String path;						// the path to the files
		List<String> StudentTypeList;		// List of student types abbreviations
		
		public static String STUDENTTYPES = "C:\\heliosWorkspace\\AddressHelios1\\src\\com\\utilities\\StudentTypes.txt";		// Current location of the student types file
		
		/**
		 * Construct the list of student types
		 * @param path
		 */
		public LoadStudentTypes(String path) {
			this.path=path;
		}
		
		/**
		 * Loop through the file populate the list of student types
		 */
		public void processFile() {
			studentTypeFile = new File(path);
			try {
				RandomAccessFile theRAFile = new RandomAccessFile(studentTypeFile, "r");
				StudentTypeList = new ArrayList<String>();
				String line="";
				try {
					line = theRAFile.readLine();					// Read the first record out of the file
					addLineToList(line);							// Add the student type to the list								
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
		 * Add a student type abbreviation to the list
		 * @param line the student type that is coming from the file
		 */
		private void addLineToList(String line) {
			if (line != null) {					// Check for a null student type
				StudentTypeList.add(line);		// Add the student type to the list
			}
		}


		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}

		public File getStudentTypeFile() {
			return studentTypeFile;
		}

		public void setStudentTypeFile(File studentTypeFile) {
			this.studentTypeFile = studentTypeFile;
		}

		public List<String> getStudentTypeList() {
			return StudentTypeList;
		}

		public void setStudentTypeList(List<String> studentTypeList) {
			StudentTypeList = studentTypeList;
		}
		
		public static void main(String[] args) {
			LoadStudentTypes theStudentTypes = new LoadStudentTypes(LoadStudentTypes.STUDENTTYPES);
			theStudentTypes.processFile();
			System.out.println(" theStudentTypes.getStudentTypeList().size() " + theStudentTypes.getStudentTypeList().size());

		}		
}
