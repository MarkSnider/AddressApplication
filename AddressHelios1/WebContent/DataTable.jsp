
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="com.forms.InputForm, com.dao.Student, java.util.Iterator, java.util.ArrayList, com.dao.Address,  com.dao.Phone, java.util.Set, com.dao.Email, com.dao.CurrentSort, com.constants.Constants, com.dao.Web, com.actions.InputAction" %>

<html>
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Address Information</title>
<link href="jquery-ui-1.11.1.custom/jquery-ui.css" rel="stylesheet">
<link href="css/mainNew.css" rel="stylesheet">

<!-- CSS comes from a very interesting web site  -->
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">

 <link href = "//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel = "stylesheet">
 
<style>
    div.padded {  
      padding-top: 20px;  
      padding-right: 20px;  
      padding-bottom: 20px;  
      padding-left: 20px; 

    }  

</style>

   <link rel="stylesheet" href="http://cdn.datatables.net/1.10.4/css/jquery.dataTables.min.css">
   
   <script type="text/javascript">
   	function openEmailWindow(TheStudentId) {
   		
   		// Place the Student id on the url
   		var url="inputAction.do?method=displayemail&StudentId=" + TheStudentId;
   		
   		// Add the url to the open
   		window.open(url, "", "width=400,height=150");
   	}
   	
	// *******************************************************************
	// Called from the onchange method on the list to change the category
	// of information that we are seeing. For example, we can change
	// just to she radio show guests
	// *******************************************************************
	function filterList() {
		
		
		// Get the drop down list with guest, guitar, All, etc...
		var selectFilter = document.getElementById("selectfilter");
		
		// get the value of the select
		var current = selectFilter.value;
		// Get the selected option
		var theOption = selectFilter.options[selectFilter.selectedIndex];
		
		// Get the actual text associated to that option
		var theOptionText = theOption.text;
		
		
		// Go to the input action ListChange method
		document.forms[0].action = "inputAction.do?method=ListChange&listValue=" + theOptionText;
		
		// submit the form
		document.forms[0].submit();
		
	}   	
   	function InitFilterList() {
   		
   		// Get the value from the hidden field
   		var theValue = document.getElementById("HiddenSelectedItem").value;
   		
		// Get the drop down list with guest, guitar, All, etc...
		var selectFilter = document.getElementById("selectfilter");
		
		// Set the selected value in the drop down list
		selectFilter.value = theValue;
   		
   	}
   	
   	// ******************************************
   	// * Delete the Record
   	// ******************************************
   	function Delete(TheId, theName) {
   		
   		var r = confirm("Are you sure you want to delete the record for " + theName + "?");
   		
   		if (r == true) {
   			
   	   		// Delete the record
   	   		var Url = "SaveEdit.do?method=DeleteStudent&StudentId=" + TheId;
   	   		window.location=Url;
   		} 
   		
   	}
   	
   	
   	// ******************************************
   	// * Display the Edit page
   	// ******************************************
   	function Edit(TheId) {
   		
   		// Take us directly to the page and dont open in another window
   		var Url = "SaveEdit.do?method=EditStudent&StudentId=" + TheId;
   		window.location=Url;
   		
   	}
   	// *************************************************************
   	// * Display the Add page which is the same as the Edit page
   	// *************************************************************  	
   	function Add() {
   		
   		// SaveEdit method AddStudent take us directly so we dont open in another window
   		var Url = "SaveEdit.do?method=AddStudent";
   		window.location=Url;  		
   	}
   </script>
</head>
<body onload="InitFilterList()">

      <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
      <script src = "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
      
      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src = "//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
      
 		  
	    
         <hr>
         <div align="center">
         	<b>Contacts Application</b>

         	  
			 <button type="button" class="btn btn-default" onclick="Add()">
				<span class="glyphicon glyphicon-plus-sign" aria-hidden="true">Add</span> 
			</button>	         	 
         </div>
         
         

         <br>       
		  <!-- HR -->
		  <div class="row">
		    <div class="col-sm-4">
				 
			 	
		     </div>			  
		  
		    <div class="col-sm-6" align="right">
		    
		    	
		    
		    </div>	 
		    <div class="col-sm-2">
				 
			 	
		     </div>				       	    
		  </div>	



		  <div class="row">
		    <div class="col-sm-3">
			 
			 	
		     </div>			  
		    <div class="col-sm-7">
				<!-- CSS comes from a very interesting web site that is linked to in the head yui.yahooapis.com -->
				  <table id="table_id" class="pure-table pure-table-bordered pure-table-striped" style="display: none;" align="center">
				    <thead>
				      <tr>
					 
					  	<th>Name</th>
					  	<th>Type</th>
					    <th>Information</th>
					  	<th>City</th>
					    <th>Email</th>
					  </tr>
					  
				    </thead>
					
				    <tbody>
				    <logic:iterate name="studentlist" id="listUserId" indexId="counter">
				    	<tr>
								
				    		<td width="4%" nowrap>
				    			<table  width="100%">
				    				<tr>
				    					<td>
				    						<a href="#" onclick="Edit('<bean:write name="listUserId" property="studentId"/>')" title="Click here to edit."><bean:write name="listUserId" property="studentName"/></a>
				    					
				    					</td>
				    					<td align="right">
								    		 <button type="button" class="btn btn-default" onclick="Delete('<bean:write name="listUserId" property="studentId"/>', '<bean:write name="listUserId" property="studentName"/>')">
						      					<span class="glyphicon glyphicon-minus-sign" aria-hidden="true"></span> 
						      				</button>				    					
				    					
				    					</td>				    					
				    				</tr>
				    			</table>
		      	

						
				    		</td>   
				     		<td >
				    			<bean:write name="listUserId" property="studentType"/>
				    		</td>   
				      		<td >
				    			<bean:write name="listUserId" property="studentInfoSmall"/>
				    		</td>     		
				     		<td >
				    			<bean:write name="listUserId" property="city"/>
				    		</td>  
				     		<td >
				     		    <a href="#" onclick="openEmailWindow('<bean:write name="listUserId" property="studentId"/>')"><img src="images/email2.png" title="Click to See Emails" /></a>
				     			
				     			
				    			
				    		</td>     		   		  		 		
				    	</tr>
				    	
				    </logic:iterate>
				    </tbody>
				 </table>					 
							 	
		     </div>		  
		    <div class="col-sm-2">
				 
			 	
		     </div>			          	    
		  </div>	
		  


  
</body>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://cdn.datatables.net/1.10.4/js/jquery.dataTables.min.js"></script>
<script src="jquery-ui-1.11.1.custom/jquery-ui.js"></script>

<script>
    $(document).ready(function () {
    	
        // Create the data table
    	$('#table_id').dataTable( );
        
        // Make the table visible its invisible by default so we dont see that strange resize effect on start up
        document.getElementById("table_id").style.display = "";

    });

</script>
</html>