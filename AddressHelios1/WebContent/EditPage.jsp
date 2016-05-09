<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="com.forms.InputForm, com.dao.Student, java.util.Iterator, java.util.ArrayList, com.dao.Address,  com.dao.Phone, java.util.Set, com.dao.Email, com.dao.CurrentSort, com.constants.Constants, com.dao.Web, com.actions.InputAction" %>

<html>
<head>

<title>Edit Page</title>
      <meta charset = "utf-8">
      <meta http-equiv = "X-UA-Compatible" content = "IE = edge">
      <meta name = "viewport" content = "width = device-width, initial-scale = 1">

      <link href = "//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css" rel = "stylesheet">
      
      <script type="text/javascript">
      function CloseWindow() {
    	  
    	  this.close();
      }
      function SaveJustOccurred() {
    	  var theSave='<bean:write name="InputForm" property="saveEvent"/>';
    	  
    	  if (theSave) {
        	  if (theSave == "SAVE") {
        		  alert("Your data has been saved!");
        		  this.close();
        	  }    		  
    	  }
      }
      function HandleEmail(emailId) {
    	  alert(emailId);
      }
      </script>
</head>
<body onload="SaveJustOccurred()">
<html:form action="/inputAction?method=SaveStudent" >
  
     <%
     InputForm theForm = (InputForm)session.getAttribute("InputForm");
     Student theStudent = theForm.getSingleStudent();
     
     %>
  
      <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
      <script src = "https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
      
      <!-- Include all compiled plugins (below), or include individual files as needed -->
      <script src = "//maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
      
      <br>
      <br>
      <!-- For some reason this property things has to be lowercase -->
      <bean:write name="InputForm" property="saveEvent"/>
		 <div class="container">
			<b>Edit Page</b>
		  <div class="row">
		    <div class="col-sm-12">
				<img src="images/editSmall.png" />	  	
		     <hr>
		    </div>	
	    	        	    
		  </div>
		  
		   <!-- style="border-style: solid; 1px;" -->
		  <div class="row" >
		  
		    <div class="col-sm-1">
			Name: 	
		    </div>
		    
		    <div class="col-sm-4">
				<input type="text" class="form-control" id="StudentName" name="StudentName" value="<%=theStudent.getStudentName()%>">
		    </div>
		    
		    <div class="col-sm-1">
			Type:
		    </div>	    		    
		    <div class="col-sm-4">
		    <!-- List of Student Types which basically defines a relationship -->
					<select id="studentTypes" name="studentTypes">
					
						<logic:iterate name="studentTypes" id="listUserId" indexId="counter">
							<option value="<bean:write name="listUserId" property="studentType"/>" <bean:write name="listUserId" property="selected"/>>
								<bean:write name="listUserId" property="studentType"/>
							</option>
						</logic:iterate>
					
					</select> 	
		    </div>

		    <div class="col-sm-2">
		    </div>		    	    
		  </div>
		  <!-- HR -->
		  <div class="row">
		    <div class="col-sm-12">
				 	
		     <hr>
		    </div>	    	    
		  </div>		  
		  
		   <!-- Street Row -->

		  <div class="row">
		    <div class="col-sm-1">
				
		     Street: 
		    </div>
		    <div class="col-sm-3">
				<input type="text" class="form-control" id="Street" name="Street" value="<%=theStudent.getStreet()%>"> 
		    </div>

		    <div class="col-sm-1">
		     	City:
		    </div>	
		    
		    <div class="col-sm-2">
		    	 <input type="text" class="form-control" id="City" name="City" value="<%=theStudent.getCity()%>">
		    </div>	
		    
		    <div class="col-sm-1">
		    	 State:
		    </div>		
			<div class="col-sm-2">
		    	<select id="state" name="state">
					<logic:iterate name="statesList" id="statesListId" indexId="counter">
						<option value="<bean:write name="statesListId" property="stateid"/>" <bean:write name="statesListId" property="selected"/>>
							<bean:write name="statesListId" property="abbrev"/>
						</option>
					</logic:iterate>
				</select>	
		    </div>	
		    	    
 		    <div class="col-sm-2">
		    	 
		    </div>	   		    	    
		  </div>	
		  
		  <!-- HR -->
		  <div class="row">
		    <div class="col-sm-12">
				 	
		     <hr>
		    </div>	    	    
		  </div>		  
		  
		  <div class="row">
		    <div class="col-sm-2">
				
		     Email:  
		    </div>			  
		    <div class="col-sm-8">
				
					<logic:iterate name="emailList" id="emailListId" indexId="counter">
						<input type="text" name="emails" class="form-control"  value="<bean:write name="emailListId" property="emailAddr"/>"  /> <br>
					   
					</logic:iterate>
		    </div>	 
		    
		    <div class="col-sm-2">

		     
		    </div>		           	    
		  </div>
		  	


		  
		  <div class="row">
		    <div class="col-sm-12">
				
		     <hr>
		    </div>	
	    	        	    
		  </div>		 
		   
		  <!-- Phone List -->
		  <div class="row">
		    <div class="col-sm-2">
				
		     Phone:  
		    </div>			  
		    <div class="col-sm-8">
				
					<logic:iterate name="phoneList" id="phoneListId" indexId="counter">
						<input type="text" name="phones" class="form-control"  value="<bean:write name="phoneListId" property="phoneNumber"/>"  /> <br>
					   
					</logic:iterate>
		    </div>	 
		    
		    <div class="col-sm-2">

		     
		    </div>		           	    
		  </div>			  
		  
		  	  	  
		  <div class="row">
		    <div class="col-sm-12">
				
		     <hr>
		    </div>	
	    	        	    
		  </div>	
		  
		  	<!-- The Web Information -->  	  
		  <div class="row">
		    <div class="col-sm-2">

		     Web:
		     
		    </div>
		    <div class="col-sm-8">
				<logic:iterate name="webList" id="webListId" indexId="counter">
					<input type="text" name="webs" class="form-control"  value="<bean:write name="webListId" property="url"/>"  /> <br>
					   
				</logic:iterate>				
		    </div>
		    <div class="col-sm-2">

		     
		    </div>		    
		    		        	    
		  </div>		  	  
		  	  
	
		  
		  <div class="row">
		    <div class="col-sm-12">
					  	
		     <hr>
		    </div>	
	    	        	    
		  </div>	
		  		  
		  
		  <div class="row">
		    <div class="col-sm-3">
					  	
		     
		    </div>	
		    <div class="col-sm-3">

		     <button type="submit" class="btn btn-default">
		      <span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span> Submit
		      </button>
		      
		    </div>
		    <div class="col-sm-3">
				<button type="button" class="btn btn-default" onclick="CloseWindow()">
				<span class="glyphicon glyphicon glyphicon-ban-circle" aria-hidden="true" ></span> Cancel
				
				</button>	  	
		    </div>
		    <div class="col-sm-3">

		     
		    </div>		    	        	    
		  </div>	
	
	
		  
		    
		  	  
		</div>   
      
</html:form>  
</body>

</html>