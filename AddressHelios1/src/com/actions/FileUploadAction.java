package com.actions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.constants.Constants;
import com.dao.ContactFileInfo;
import com.dao.Student;
import com.forms.FileUploadForm;
import com.service.StudentService;

public class FileUploadAction extends Action{
    private final static String SUCCESS = "success";	// Find forward to take us to the main display
    private final String ERROR = "error";
    
	public ActionForward execute(ActionMapping mapping, ActionForm form,
		    HttpServletRequest request, HttpServletResponse response)
		    throws Exception {
		 
	    FileUploadForm fileUploadForm = (FileUploadForm)form;
 
	    FormFile file = fileUploadForm.getFile();
	    String fileName = file.getFileName();
	    String line="";
	    String forwardLocation=SUCCESS;	
	   
	    
	   try {
		// Get the input stream and convert it into a buffer reader
		    BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
		    //Get the servers upload directory real path name
		    String filePath = 
	               getServlet().getServletContext().getRealPath("/") +"upload";
	 
		    System.out.println("filePath " + filePath);
		    request.getSession().setAttribute("FILEPATH",filePath);
		    //create the upload folder if not exists
		    File folder = new File(filePath);
		    if(!folder.exists()){
		    	folder.mkdir();
		    }
			// Define an array list for storing the information that will be displayed on the screen
			ArrayList<ContactFileInfo> theList = new ArrayList<ContactFileInfo>();
			
		    // Create the output file
		    File outFile = new File(filePath + File.separatorChar + fileName);
		    
		    // Create the file writer
		    BufferedWriter theFileWriter = new BufferedWriter(new FileWriter(outFile));
		    
		    // Read a line in the file and continue
			while ((line = br.readLine()) != null) {
				

				// Get the list of the information and place that into the session
				theList = getDesiredInfo(line,theFileWriter,theList);
				
				// the list placed into the file is put into the session for later display
				request.getSession().setAttribute("FILE_DATA",theList);
				System.out.println("theList " + theList.size());
			}
			
			br.close();
			theFileWriter.close();
			

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		forwardLocation=ERROR;																		// set the flag so that we go the error JSP
		request.setAttribute(Constants.EXCEPTION_REPORT, e.toString());								// place the short text 
		request.setAttribute(Constants.EXCEPTION, e);			
	}    	        
	    
		return(mapping.findForward(forwardLocation));
		
	}
	/**
	 * Loop through the string backwards and trim the numbers off of the end and the beginning
	 * @param data string before line number removed
	 * @return String after the line number removed
	 * @throws Exception
	 */
	private String trimNumbers(String data) throws Exception{
		String goodData="";
		// Look character by character
		for (int i=0; i <= data.length()-1;  i++) {
			
			// Get the character
			char c = data.charAt(i);
			
			// Check for a digit that is at the end of the buffer
			if (Character.isDigit(c) == true) {
				
				// We are at the end of the line and its a digit
				if ( i > data.length()-3) {
					System.out.println("Found line number c  " + c);
				}
				// Its a number in the middle of the line
				else {
					
					// And we are not at the beginning of the line
					if (i != 0 && i > 2) {
						
						// Add the character
						goodData+=c;							
					}
			
				}
				
			}
			else {
				// Add the character
				goodData+=c;	
			}
			
			
		}		
		return(goodData);
	}
	/**
	 * This method screens out the line numbers that appear as \1234.
	 * The code splits on he "." and the "/" and ignores the part that contains the line numbers.
	 * This code writes out the desired information into the file without the line numbers
	 * @param data a particular line of information
	 * @param BufferedWriter - the object used to write the needed information into the file
	 * @return A list of lines that contains no \1234 ; however, a line with some embedded line breaks
	 */
	private ArrayList<ContactFileInfo> getDesiredInfo(  String data, 
														BufferedWriter theFileWriter,
														ArrayList<ContactFileInfo> theList) throws Exception {
		
		String goodData="";

		
		// Split on the sentence first
		String parts[] = data.split("[.]");
		
		// Loop through the sentences
		for(String subPart: parts) {
			
			// Split of the slash which ends the line number
			String[] subPart2 = subPart.split("[/]");
			
			// Loop through those
			for (String currData: subPart2) {
				
				// Ignore the parts that are just line numbers
				if (currData != null && currData.length() > 4) {
					
					// Split out the sections that are 
					String[] finalData = currData.split("- ");
					
					for (String last: finalData) {
						
						// Ignore the part that is just a line number
						if (last != null && last.length() > 4) {
							String record = last + ".";
							System.out.println("record " + record);
							// write the data out
							theFileWriter.write(record);
							theFileWriter.newLine();	
							// Save the data into the list for later display
							theList.add(new ContactFileInfo(record));
						}
					}
				}
			}
		}
		
	
		return(theList);
	}
	public static void main(String[] args) {
		//String data="Senjase- 148/But surely, as you know this very well. 149/In cause of their reached by themselves and";
		//String data = "Semjase- 2/I understand. 3/Unofficially America gets governed by several kinds of governments. 4/On the one hand is governing there the presidential govern­ment, but on the other hand as well are the Pentagon and the CIA moreover. 5/They officially belong to­gether all right, but in truth, all three of these formations work for themselves in their inner­most, for which reason they form in a certain re­spect each a government of its own. 6/Then when your";
		String data ="13/Around 150,000 years ago in earth time, our home­ worlds finally found peace and liberty after very long times of wars and revolutions. 14/A short time before the settling of the peace and quiet yet, a scientist by the name of PELEGON elected himself leader of a group of about 70,000 human beings, with whose help he took possession of several great spacer ships and fled.15/Being an important scientist, it was an easy thing for him to coerce the 70,000 head group under his control, and to bring them through space and time in his wild escape towards Earth. 16/On board the stolen spaceship were nearly 200";
		//String parts[] = data.split("[0-9][0-9][0-9]/");
		// String parts[] = data.split("[0-999]/");
		
		String parts[] = data.split("[.]");
		for(String subPart: parts) {
			//System.out.println("subPart "+ subPart);
			String[] subPart2 = subPart.split("[/]");
			
			// Process the last section make sure we have no line numbers
			for (String currData: subPart2) {
				if (currData != null && currData.length() > 4) {
					
					String[] finalData = currData.split("- ");
					
					for (String last: finalData) {
						
						
						if (last != null && last.length() > 4) {
							System.out.println("last ["+ last +"]");
						}
					}
					
				}
				
			}
		}
	}
	
	
	
	public ActionForward executeOld(ActionMapping mapping, ActionForm form,
		    HttpServletRequest request, HttpServletResponse response)
		    throws Exception {
		 
	    FileUploadForm fileUploadForm = (FileUploadForm)form;
 
	    FormFile file = fileUploadForm.getFile();
	    String fileName = file.getFileName();
	    String line="";
	    String forwardLocation=SUCCESS;	
	   
	    
	   try {
		// Get the input stream and convert it into a buffer reader
		    BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));
		    //Get the servers upload directory real path name
		    String filePath = 
	               getServlet().getServletContext().getRealPath("/") +"upload";
	 
		    System.out.println("filePath " + filePath);
		    
		    //create the upload folder if not exists
		    File folder = new File(filePath);
		    if(!folder.exists()){
		    	folder.mkdir();
		    }
		    
		    // Create the output file
		    File outFile = new File(filePath + File.separatorChar + fileName);
		    
		    // Create the file writer
		    BufferedWriter theFileWriter = new BufferedWriter(new FileWriter(outFile));
		    
		    // Read a line in the file and continue
			while ((line = br.readLine()) != null) {
				
				// Display the line
				System.out.println("line " + line);
				
				// Split the line into the pieces separated by the slash
				String[] theArray = line.split("/");
				
				// Loop through the array
				for (String data: theArray) {
					
					// Trim the line number off of the end or beginning for that matter
					String goodData = trimNumbers(data);
					System.out.println("goodData " + goodData);
					
					// write the data out
					theFileWriter.write(goodData);
					theFileWriter.newLine();
					
				}
				
			}
			
			br.close();
			theFileWriter.close();
			
		    // Get the list of student / address information which includes all of the sub or nested objects that hibernate does not populate by default
		    ArrayList<Student> theList = (ArrayList<Student>)StudentService.listCompleteStudentInfo();
		    
		    // This is the list in memory for logic iterate tag to display list
			request.setAttribute("studentlist", theList);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		forwardLocation=ERROR;																		// set the flag so that we go the error JSP
		request.setAttribute(Constants.EXCEPTION_REPORT, e.toString());								// place the short text 
		request.setAttribute(Constants.EXCEPTION, e);			
	}    	        
	    
		return(mapping.findForward(forwardLocation));
		
	}	
	
}
