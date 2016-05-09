<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="com.forms.InputForm, com.dao.Student, java.util.Iterator, java.util.ArrayList, com.dao.Address,  com.dao.Phone, java.util.Set, com.dao.Email, com.dao.CurrentSort, com.constants.Constants, com.dao.Web, com.actions.InputAction" %>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Page</title>
      <meta charset = "utf-8">
      <meta http-equiv = "X-UA-Compatible" content = "IE = edge">
      <meta name = "viewport" content = "width = device-width, initial-scale = 1">
      

      
      <link href = "//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel = "stylesheet">
      <script type="text/javascript">
      function CloseWindow() {
    	  
    	  // Take us back to the main page
 	   	  var Url = "inputAction.do?method=datatable";
   	   	  window.location=Url;     
      }
      function SaveJustOccurred() {
    	  var theSave='<bean:write name="SaveEditForm" property="saveEvent"/>';
    	
    	  if (theSave) {
        	  if (theSave == "SAVE") {
        		  alert("Your data has been saved!");     		  
        		  this.close();    		  
        	  }    		  
    	  }
      }
      // ******************************************************************
      // * Will make the approrpiate div element with text fields visible
      // ******************************************************************
      
      function AddTextFields(CheckObj,TheElementId) {
    	  var theValue = CheckObj.checked;
    	
    	  if (theValue == true) {
        	  // Make the text boxes for creating a new email visible
        	  document.getElementById(TheElementId).style.display = "";  		  
    	  }
    	  else {
        	  // Make the text boxes for creating a new email visible
        	  document.getElementById(TheElementId).style.display = "none";  	  		  
    	  }

      }
      // ******************************************************************
      // * Will put the text _DELETE after the id of an email, phone or web.
      // * That record will not be added to the hash table used for the add.
      // ******************************************************************      
      function MarkDeleted(theId, checkObj) {
    	 
    	  var theCheckedValue = checkObj.checked;
    	  
    	  if (theCheckedValue == true) {
    		  var theValue = document.getElementById(theId).value;
    		  document.getElementById(theId).value = theValue + "_DELETE";
    	  }
    	  else {
    		  var theValue = document.getElementById(theId).value;
    		  var theIndex = theValue.indexOf("_DELETE");
    		  var RegularValue = theValue.substring(0,theIndex);
    		  document.getElementById(theId).value=RegularValue;
    		  
    	  }
      }
      </script>
	  <style>

	  </style>
</head>
<body>
   

<div align="center">

 <!-- Call the SaveData method in SaveAction.java -->
 
<html:form action="/SaveEdit?method=SaveData" >

      <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
      <script src = "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
      
      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src = "//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
 
		<h4 align="center">Add / Edit Screen</h4>
		
		
		

		   
		  <div class="row" style="width: 800px; border: 1px solid #E8E8E8; background-color: #E8E8E8;">
		    <div class="col-sm-1">
				Name:
		     </div>	
		     	  
		    <div class="col-sm-3" align="left">
				<html:text name="SaveEditForm" property="name" styleId="largetext"  />
		     
		    </div>	
		    <div class="col-sm-1">
				 Type:
		     </div>		    
		    <div class="col-sm-3" align="left">
				<html:select property="studentType">
						<logic:iterate name="studentTypes" id="listUserId" indexId="counter">
							<option value="<bean:write name="listUserId" property="studentType"/>" <bean:write name="listUserId" property="selected"/>>
								<bean:write name="listUserId" property="studentType"/>
							</option>
						</logic:iterate>	
								
				</html:select>
		     
		    </div>		
			 <div class="col-sm-1">
				 Street:
		     </div>		    
		    <div class="col-sm-3">
		     	<html:text name="SaveEditForm" property="street" styleId="form-control"  />
		    </div>		    	    
  	        	    
		  </div>   
		  
		 
		  <div class="row" style="width: 800px; border: 1px solid #E8E8E8; background-color: #E8E8E8;">
		    <div class="col-sm-1">
				City:
			 	
		     </div>			  
		    <div class="col-sm-3" align="left">
				 <html:text name="SaveEditForm" property="city" styleId="form-control"  />
			 	
		     </div>			  
		    <div class="col-sm-1">
					   State:
			 	
		     </div>	
		     
			 <div class="col-sm-3" align="left">
		    	<html:select property="state">
					<logic:iterate name="statesList" id="statesListId" indexId="counter">
						<option value="<bean:write name="statesListId" property="abbrev"/>" <bean:write name="statesListId" property="selected"/>>
							<bean:write name="statesListId" property="abbrev"/>
						</option>
					</logic:iterate>
				</html:select>						 
			 	
		     </div>		     		     	  


		    <div class="col-sm-1">
		    	Country: 
		    </div>	
		    

		     		    
		    <div class="col-sm-3">
				
		    	<html:text name="SaveEditForm" property="country" styleId="form-control"  />
		    </div>			    

		    
		    	        	    
		  </div>	
		  
		  		  
		  <div class="row" style="width: 800px; border: 1px solid #E8E8E8; background-color: #E8E8E8;">
		    <div class="col-sm-1">
				 
			 	Zip:
		     </div>	
		    <div class="col-sm-3" align="left">
				 
			 	<html:text name="SaveEditForm" property="zip" styleId="form-control" size="5" />
		     </div>			     		  
		    <div class="col-sm-2" align="left">
				 	
		    General Information:
		    </div>	 
		    <div class="col-sm-6">
				 
		    	<textarea class="form-control" rows="3" name="generalinformation" id="generalinformation" ><%= request.getAttribute("STUDENT_INFORMATION") %></textarea>
			 	
		     </div>			       	    
		  </div>			  
		  
		  
		  <div class="row" style="width: 800px; border: 1px solid #E8E8E8; background-color: #E8E8E8;">

		

		    <div class="col-sm-12">
		    	
		    	<table  class="table table-striped table-bordered table-hover" >
		    		<tr>
		    			<td> Remove </td>
		    			<td> Type </td>
		    			<td> Address </td>
		    		</tr>
		    		
		    	   <% 
		    	   int cnt=0;
		    	   String EmailId = "EmailId";
		    	   %>
					<logic:iterate name="emailList" id="emailListId" indexId="counter">
				   <% 
		    	    cnt+= 1;
				    EmailId = "Email_" + cnt;
		    	   %>
						<tr>
							<td>
							
							<input type="checkbox" onclick="MarkDeleted('<%= EmailId %>', this)" />
							</td>						
							<td valign="top">
							  
							  <input type="text" name="emailTypes" size="5"  value="<bean:write name="emailListId" property="emailType"/>"  />
							</td>
							 <!-- The Email Address -->
							<td>
								<input type="text" name="emails" size="25"  value="<bean:write name="emailListId" property="emailAddr"/>"  /> <br>
								<input type="hidden" name="emailIds" size="5"  id="<%= EmailId %>" value="<bean:write name="emailListId" property="emailId"/>"  /> <br>							
							</td>

						</tr>

					    
					</logic:iterate>	
					<tr>
					    <td>Add : <input type="checkbox" onclick="AddTextFields(this,'NewEmailDiv')" /></td>
						<td colspan="2" >

							<div style="display: none;" id="NewEmailDiv">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text" name="emailTypesNew" size="3"  value=""  /> 
							<input type="text" name="emailsNew" size="25"  value=""  /> <br>
							
							</div>
							 
							
							
						</td>
					
					</tr>			
				</table>	
		    <!-- The user can add a new email even though they are updating an existing record -->
		    
		      
		    
		    </div>	
	    	        	    
		  </div>	
		  
	  
		  
		  
		  <!-- Phone -->
		  <div class="row" style="width: 800px; border: 1px solid #E8E8E8; background-color: #E8E8E8;">

		     	<!-- style="height: 175px; overflow-y: auto;" -->	  
		    <div class="col-sm-6">
		   
		    	<table  class="table table-striped table-bordered table-hover">
		    		<tr>
		    			<td>Remove</td>
		    			<td>Phone Type</td>
		    			<td>Phone Number</td>
		    		</tr>			
		    	   <% 
		    	   int cnt2=0;
		    	   String PhoneId = "PhoneId";
		    	   %>		    			 	
					<logic:iterate name="phoneList" id="phoneListId" indexId="counter">
				   <% 
		    	    cnt2 += 1;
				   PhoneId = "PhoneId_" + cnt2;
		    	   %>					
					
					<tr>
							<td>
							
							<input type="checkbox" onclick="MarkDeleted('<%= PhoneId %>', this)" />
							</td>	
												
							<td valign="top">
							  
							  <input type="text" name="phoneTypes" size="5"  value="<bean:write name="phoneListId" property="phoneType"/>"  />
							</td>					
						
							<td>
								<input type="text" name="phones" size="15"  value="<bean:write name="phoneListId" property="phoneNumber"/>"  /> <br>
							    <input type="hidden" name="phonesIds" id="<%= PhoneId %>" size="5"  value="<bean:write name="phoneListId" property="phoneId"/>"  /> <br>						
							</td>
						</tr>

					</logic:iterate>	
						<tr>
							<td>
							Add: <input type="checkbox" onclick="AddTextFields(this,'NewPhonesDiv')" />
							</td>
							<td colspan="2">
								<div style="display: none;" id="NewPhonesDiv">
								<input type="text" name="PhoneTypeNew" size="5"  value=""  /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="text" name="PhoneNumberNew" size="15"  value=""  /> <br>
								
								</div>
								
														
							</td>
						</tr>					
				</table>	
		    </div>	
		     <!-- style="height: 175px; overflow-y: auto;" -->
		    <div class="col-sm-6" >
		    	<table  class="table table-striped table-bordered table-hover">
		    		<tr>
		    			<td>Remove </td>
		    			<td>
		    			    
		    			    Web Page
		    			</td>
		    		</tr>
		    	   <% 
		    	   int cnt3=0;
		    	   String WebId = "WebId_";
		    	   %>			    		
	    			<logic:present name="webList">		
   				 
						<logic:iterate name="webList" id="webListId" indexId="counter">
				   <% 
		    	    cnt3 += 1;
				    WebId = "WebId_" + cnt3;
		    	   %>
							<tr>
							   <td>
							   		<input type="checkbox" onclick="MarkDeleted('<%= WebId %>', this)" />
							   </td>
				    			<td>
				    			    <input type="text" name="webs" size="35"  value="<bean:write name="webListId" property="url"/>"  />
				    			    <input type="hidden" name="webIds" id="<%= WebId %>" size="5"  value="<bean:write name="webListId" property="webid"/>"  /> <br>
				    			</td>
				    			
				    		</tr>	   
						</logic:iterate>
					</logic:present>
					<tr>
					    <td>
					    Add: <input type="checkbox" onclick="AddTextFields(this,'NewWebDiv')" />
					    </td>
						<td colspan="2" >

							<div style="display: none;" id="NewWebDiv">
							<input type="text" name="WebUrlNew" size="35"  value=""  /> 
							
							</div>
							
							
						</td>
					
					</tr>						
				</table>
								 	
		     </div>		
	     	       	    
		  </div>		  
		  
		  		  
		  
		  		  
		  <div class="row" style="width: 800px; border: 1px solid #E8E8E8; background-color: #E8E8E8;">
		    <div class="col-sm-1">
				 
			 	
		     </div>			  
		    <div class="col-sm-5" align="right">
		     <button type="submit" class="btn btn-default">
		      <span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span> Submit
		      </button>
			 	
		     </div>		  
		    <div class="col-sm-5" align="left">
				
				<button type="button" class="btn btn-default" onclick="CloseWindow()">
				<span class="glyphicon glyphicon glyphicon-ban-circle" aria-hidden="true" ></span> Cancel
				
				</button>	
		    </div>	
		    <div class="col-sm-1">
				 
			 	
		     </div>			    	        	    
		  </div>		  
		   
	
</html:form>
</div>
</body>
</html>