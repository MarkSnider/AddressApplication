<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<%@ page import="com.actions.InputAction, com.constants.Constants,com.dao.Country, com.dao.StudentType, com.service.StudentTypeService, com.service.CountryService, com.dao.State, com.service.StateService, com.forms.EditForm, com.utilities.LoadStates, java.util.List, java.util.Iterator, java.util.Date" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
   <link type="text/css" href="css/ui-lightness/jquery-ui-1.8.17.custom.css" rel="stylesheet" />
   <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
   <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
  
	<script src="development-bundle/jquery-1.7.1.js"></script>
	<script src="development-bundle/ui/jquery.ui.core.js"></script>
	<script src="development-bundle/ui/jquery.ui.widget.js"></script>
	<script src="development-bundle/ui/jquery.ui.button.js"></script>
	<script src="development-bundle/ui/jquery.ui.accordion.js"></script>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<script>
	$(function() {
		$( "#accordion" ).accordion();
	});
	$(function() {
		$( ".demo button:first" ).button({
            icons: {
                primary: "ui-icon-plus"
            }
		});
	});    
	$(function() {
		$( "#tabs" ).tabs();
	});		
	
	</script>
<title>Edit</title>
			<style type="text/css">

			h3 { 
				font: italic normal 1.4em georgia, sans-serif;
				letter-spacing: 1px; 
				margin-bottom: 0; 
				color: #7D775C;
				}	
			#smalltext
			{
				width: 45px;
			} 
			#mediumtext
			{
				width: 90px;
			}
			#largeCombo
			{
				width: 110px;
			}
			#largetext
			{
				width: 265px;
			}  
			      
        	#buttonLook 
        	{
        		width: 	55px;

        	}
 
			#Form 
			{
				font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
				width:30%;
				border-collapse:collapse;
			}
			#Form td, #Form th 
			{
			
			font-size: small;
			
			padding:3px 7px 2px 7px;
			background-color: 	#E0E0E0;
			}
			#Form th 
			{
				font-size:1.4em;
				text-align:left;
				padding-top:5px;
				padding-bottom:4px;
				background-color:#A7C942;
				color:#fff;
			}
			#Form tr.alt td 
			{
			color:#000;
			background-color:#EAF2D3;
			}

      		
		
	 
							
		</style>			
		
		<script type="text/javascript">	
		function goBack()
		{
			document.forms[0].action = "inputAction.do?method=populate";
			document.forms[0].submit();
		}	
		/*
		* Opens the delete jsp in its own window
		* @param theURL points to the action for the radioShow deleteprep method. It has parameters on the url
		* That are displayed on the jsp
		*/
		function displayWindow(theUrl) {
			
			// Open the window as resizable with scroll bars
			var myWindow = window.open(theUrl,
					"mywindow","location=1,status=1,scrollbars=1,width=650,height=300");
			
			// Move the window to the right for easier access
			myWindow.moveTo(800, 0);
		}
		// Bring up the add screen
		function add()
		{
			// Set the action to the method for add in the 
			document.forms[0].action = "Radio.do?method=add";
			document.forms[0].submit();
		}			
		</script>
</head>
<body style="font-size:62.5%;">
        <div style="color:red">
            <html:errors />
        </div>
        
 <html:form action="/Edit" >	
<div class="demo" align="center">

<div id="accordion" style="width:70%; background:#FFA500;">
	<h3><a href="#">Standard Information</a></h3>
	<div>
		<p>
 	        <TABLE class="customersnowidth" align="center" width="100%"> 
            <TR>
				<TH colspan="4" align="left" >Information on a particular person.</TH>

            </TR>   		
 			</TABLE>		
 				<table class="customersnowidth" align="center" width="100%">

 					<tr>
 						<td>Name:</td><td><html:text name="EditForm" property="name" styleId="largetext" /> </td>

 						<td>Street:</td><td><html:text name="EditForm" property="street" styleId="largetext" /> </td>
 					
 					</tr>
 					<tr>
 						<td>City:</td><td><html:text name="EditForm" property="city" styleId="largetext" /> </td>						
  						
 						<td align="left">State:</td>
	                    <td>
							<html:select property="state">
								<%
								List<State> theStatesList = StateService.selectAllStates();
			                	Iterator<State> it = theStatesList.iterator();
			                	while(it.hasNext()) {
			                		State state = (State)it.next();
			                		String stateAbbr = state.getAbbrev();
								%>
								<!-- Display the state as an option -->
								<html:option value="<%= stateAbbr %>"><%= stateAbbr %></html:option>
								<% } %>
							</html:select> 
	                    </td>
	               </tr>
	               <tr>     
  						<td>Country:</td>
	                    <td>
							<html:select property="country" styleId="largeCombo">
								<%
								List<Country> theCountryList = CountryService.selectAllCountries();
			                	Iterator<Country> it = theCountryList.iterator();
			                	while(it.hasNext()) {
			                		Country country = (Country)it.next();
			                		String name = country.getCountryName();							
								%>
								<!-- Display the state as an option -->
								<html:option value="<%=name %>"><%=name %></html:option>
							<% } %>
							</html:select> 
	                    </td>
	                   <td>Zip:</td><td><html:text name="EditForm" property="zip" styleId="smalltext" /> </td>
	                 </tr>
 						<tr>
 						<td>Home Phone:</td>
 						<td><html:text name="EditForm" property="homePhone" styleId="mediumtext" /> </td>
 						<td>Cell Phone:</td><td><html:text name="EditForm" property="cellPhone" styleId="mediumtext" /> </td>
 						</tr>

 					<tr>
 						<td>Home Email:</td><td><html:text name="EditForm" property="homeEmail" styleId="largetext" /> </td>
 						<td>Work Email:</td><td><html:text name="EditForm" property="workEmail" styleId="largetext" /> </td>
 					</tr>

    				<tr>
 						<td>URL Information</td>
 						<td>
 						<html:text name="EditForm" property="urlOne" styleId="largetext" /><BR> 
 						<html:text name="EditForm" property="urlTwo" styleId="largetext" /><BR>
 						<html:text name="EditForm" property="urlThree" styleId="largetext" />
 						
 						</td>
 						<td>
 						Type
 						</td>
 						<td>
 						
 						  
 								<html:select property="studentType">
								<%
								List<StudentType> theStudentTypeList = StudentTypeService.selectStudentTypes();
			                	Iterator<StudentType> it = theStudentTypeList.iterator();
			                	while(it.hasNext()) {
			                		StudentType theStudentType = (StudentType)it.next();
			                		String type = theStudentType.getStudentType();
								%>
								<!-- Display the type as an option -->
								<html:option value="<%= type %>"><%= type %></html:option>
								<% } %>
							</html:select> 
 						</td>
 					</tr>					
					
					<tr>	

 						<td valign="top" colspan="2" align="center">
 						General Information <br>
 						<html:textarea name="EditForm" property="information" rows="18" cols="90"/> 
 						</td>
 						<td valign="top" colspan="2" align="center">
 						Radio Display <br>
 							<div style="height: 300px; overflow: auto;">
				 				<table class="customersnowidth"  align="center" width="100%">
				 				    <tr>
				 				      <td colspan="3">
				 				      <button onclick="add()">Add</button>
				 				      Add radio show information by clicking the add button. 
				 				      Edit a specific radio show by clicking the link. 
				 				      Delete a specific show by clicking the appropriate "x".
				 				      </td>
				 				    </tr>
									<tr>										
										<th>Title</th>
										<th>
											<a href="Radio.do?method=sortRadioShow">Archive</a>
										</th>
										<th>
											<a href="Radio.do?method=sortRadioShowDate">Date</a>
										</th>
									<tr>
									<logic:iterate name="radioshowsmall" id="listUserId">
									<tr bgcolor="white">
										 <td nowrap="nowrap">
										 	<a href="Radio.do?method=deleteprep&radioid=<bean:write name="listUserId" property="showId"/>&title=<bean:write name="listUserId" property="title"/>&desc=<bean:write name="listUserId" property="description"/>&arch=<bean:write name="listUserId" property="archiveListens"/>&live=<bean:write name="listUserId" property="liveListens"/>&ldate=<bean:write name="listUserId" property="listedDate" format="MM/dd/yyyy"/>" title="Click here to delete a radio show" ><img src="images/deletesmall.jpg" /></a>
				
										 	<a href="Radio.do?method=edit&radioid=<bean:write name="listUserId" property="showId"/>&title=<bean:write name="listUserId" property="title"/>&desc=<bean:write name="listUserId" property="description"/>&arch=<bean:write name="listUserId" property="archiveListens"/>&live=<bean:write name="listUserId" property="liveListens"/>&ldate=<bean:write name="listUserId" property="listedDate" format="MM/dd/yyyy"/>" title="Click here to edit a radio show">
										 		<bean:write name="listUserId" property="title"/> 
										 	</a>
										 </td>
										 <td><bean:write name="listUserId" property="archiveListens"/></td>
										 <td>
										 	<bean:write name="listUserId" property="listedDate" format="MM/dd/yyyy"/>
										 </td>
									</tr>
									</logic:iterate>	
								</table> 						
 							</div>
 						
 						
 						
 						</td>
 					</tr>					

					<tr>	

 						<td valign="top" colspan="4" align="center">

 						</td>
 						
 					</tr>


					<tr>
					  <td colspan="4">
					   	<p align="center">
							<html:submit property="method" value="OK" styleId="buttonLook" /> 
 							<input type="button" onclick="goBack()" value="Cancel" /> 
 						</p> 
					  </td>
					</tr>		
 				</table>

		</p>
	</div>
	<h3><a href="#">Radio Show Complete Information</a></h3>
	<div>
			

		<p>
 				<table class="customersnowidth"  align="center" width="100%">
 				    <tr>
 				      <td colspan="5">
 				      <button onclick="add()">Add</button>
 				      Add radio show information by clicking the add button. 
 				      Edit a specific radio show by clicking the link. 
 				      Delete a specific show by clicking the appropriate "x".
 				      </td>
 				    </tr>
					<tr>
						
						<th>Title</th>
						<th>Description</th>
						<th><a href="Radio.do?method=sortRadioShow">Archive</a></th>
						<th>Live</th>
						<th><a href="Radio.do?method=sortRadioShowDate">Date</a></th>
					<tr>
					<logic:iterate name="radioshows" id="listUserId">
					<tr bgcolor="white">

						 <td nowrap="nowrap">
						 	<a href="Radio.do?method=deleteprep&radioid=<bean:write name="listUserId" property="showId"/>&title=<bean:write name="listUserId" property="title"/>&desc=<bean:write name="listUserId" property="description"/>&arch=<bean:write name="listUserId" property="archiveListens"/>&live=<bean:write name="listUserId" property="liveListens"/>&ldate=<bean:write name="listUserId" property="listedDate" format="MM/dd/yyyy"/>" title="Click here to delete a radio show" ><img src="images/deletesmall.jpg" /></a>

						 	<a href="Radio.do?method=edit&radioid=<bean:write name="listUserId" property="showId"/>&title=<bean:write name="listUserId" property="title"/>&desc=<bean:write name="listUserId" property="description"/>&arch=<bean:write name="listUserId" property="archiveListens"/>&live=<bean:write name="listUserId" property="liveListens"/>&ldate=<bean:write name="listUserId" property="listedDate" format="MM/dd/yyyy"/>" title="Click here to edit a radio show">
						 		<bean:write name="listUserId" property="title"/> 
						 	</a>
						 </td>
						 <td><bean:write name="listUserId" property="description"/></td>
						 <td><bean:write name="listUserId" property="archiveListens"/></td>
						 <td><bean:write name="listUserId" property="liveListens"/></td>
						 <td>
						 	<bean:write name="listUserId" property="listedDate" format="MM/dd/yyyy"/>
						 </td>
					</tr>
					</logic:iterate>	
				</table>					

		</p>
	</div>
</div>

</div><!-- End demo -->

 </html:form>			    
       

 			
</body>
</html>