<%-- 
    Document   : input
    Created on : December 20, 2011
    Author     : Mark Snider
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="com.forms.InputForm, com.dao.Student, java.util.Iterator, java.util.ArrayList, com.dao.Address,  com.dao.Phone, java.util.Set, com.dao.Email, com.dao.CurrentSort, com.constants.Constants, com.dao.Web, com.actions.InputAction" %>
<html>
    <head>
		    
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
  <link type="text/css" href="css/ui-lightness/jquery-ui-1.8.17.custom.css" rel="stylesheet" />

  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
	
	<script src="development-bundle/jquery-1.7.1.js"></script>
	<script src="development-bundle/ui/jquery.ui.core.js"></script>
	<script src="development-bundle/ui/jquery.ui.widget.js"></script>
	<script src="development-bundle/ui/jquery.ui.button.js"></script>
	<script src="development-bundle/ui/jquery.ui.tabs.js"></script>
	<script src="development-bundle/ui/jquery.ui.resizable.js"></script>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<title>Address</title>
	<style>

		#resizable4 { width: 89%; height: 575px; padding: 0.5em;}
		#resizable4 h3 { text-align: center; margin: 0; }			
			
	</style>	
 <script>

	$(function() {
		$( ".demo button:first" ).button({
            icons: {
                primary: "ui-icon-search"
            }
        }).next().button({
            icons: {
                primary: "ui-icon-search"
            }		
        }).next().button({
            icons: {
                primary: "ui-icon-plus"
            }     

        }).next().button({
            icons: {
                primary: "ui-icon-arrowrefresh-1-e"
            }  
        }).next().button({
            icons: {
                primary: "ui-icon-clipboard"
            } 
        }).next().button({
            icons: {
                primary: "ui-icon-volume-on"
            } 
        }).next().button({
            icons: {
                primary: "ui-icon-volume-off"
            } 
        }).next().button({
            icons: {
                primary: "ui-icon-help"  // Contact 251
            }         
        }).next().button({
            icons: {
                primary: "ui-icon-help"  // Pleadian Mission
            } 
        }).next().button({
            icons: {
                primary: "ui-icon-help"
            }         
        }).next().button({
            icons: {
                primary: "ui-icon-search"
            }	        
        });
	});    
	$(function() {
		$( "#tabs" ).tabs();
	});	
  </script>	
	<script type="text/javascript">
	
		function initFilterList() {
			
			// *******************************************************************
			// Set the value of the list on the load of the page
			// *******************************************************************			
			<% 
			String theValue = (String)session.getAttribute("listValue");
			
			if (theValue == null) {
				theValue="ALL";
			}
			%>
			
			// Get the drop down list with guest, guitar, All, etc...
			var selectFilter = document.getElementById("selectfilter");
			
			// set the value in the list
			selectFilter.value="<%= theValue %>";
			
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
			
			
			// Set the action to the search action ListChange method and pass the text of the option
			document.forms[0].action = "Search.do?method=ListChange&listValue=" + theOptionText;
			
			// submit the form
			document.forms[0].submit();
			
		}
		function show_alert()	
		{
			alert("Select a radio button and press edit to change an address. Select a radio button and press delete to delete an address. Press the add button to create a new address");
		}
		
		function popitup(url) {
			newwindow=window.open(url,'name','height=200,width=800');
			if (window.focus) {newwindow.focus()}
			return false;
		}
		function submitForm()
		{
			document.forms[0].action = "inputAction.do?method=edit";
			document.forms[0].submit();
		}
		function submitFormRadio()
		{
			document.forms[0].action = "inputAction.do?method=radioshow";
			document.forms[0].submit();
		}
		function submitSearch()
		{
			var theId = document.forms[0].startsWith.value.length;
		  
			if (theId > 0) {
				document.forms[0].action = "inputAction.do?method=search";
				document.forms[0].submit();				
			}

		}
		function submitDelete()
		{
			document.forms[0].action = "inputAction.do?method=delete";
			document.forms[0].submit();
		}	
		
		function clickedRadioButton(id) {
			
		
		}
		
		function add() {
			
			document.forms[0].action = "inputAction.do?method=add";
		}
		function advancedsearch() {
			
			document.forms[0].action = "inputAction.do?method=advancedSearch";
		}	
		function refresh() {
			
			document.forms[0].action = "inputAction.do?method=all";
		}	
		function pageview() {
			
			document.forms[0].action = "inputAction.do?method=page";
		}	
		function numlistens() {
			
			document.forms[0].action = "Radio.do?method=list";
		}	
		function listguests() {
			
			document.forms[0].action = "Radio.do?method=listguest";
		}	
		function helppage() {
			
			document.forms[0].action = "inputAction.do?method=helppage";
		}	
		function upload() {
			
			document.forms[0].action = "inputAction.do?method=upload";
		}			
		function search251() {
			
			document.forms[0].action = "display251.do?method=displayLike";
			//window.open ("search251.do?method=OK","mywindow","menubar=1,resizable=1,width=350,height=250");
			
		}	
		function searchPleiadeanMission() {
			
			
			document.forms[0].action = "displayPleiadean.do?method=displayLike";
			
		}		
	</script>
	

    </head>
    <body style="font-size:62.5%;" onload="initFilterList()">
    
        <html:form action="/inputAction" >
		<%
        	InputForm theForm = (InputForm)session.getAttribute("InputForm");
        	ArrayList<Student> theList = theForm.getStudentList();
       
		%>    
		
	<!-- The outer div surrounds everything -->	    
 		<div align="center">
		<table  class="customers largecustomers" >
               <tr>
                    <td colspan="2">
                       <div class="demo">
                            


								<div id="tabs">
									<ul>
										<li><a href="#tabs-1">Functions</a></li>
										<li><a href="#tabs-2">Other</a></li>	
										<li><a href="#tabs-3">Help</a></li>
																				
									</ul>
									<div id="tabs-1">

											
				                      		Select Search Type:
				
											<html:select property="selectedMatchMode" >
												<html:option value="START">START</html:option>
												<html:option value="ANYWHERE">ANYWHERE</html:option>
												<html:option value="END">END</html:option>
												<html:option value="END">EXACT</html:option>
											</html:select>
				                    								
											Name:<html:text styleId="searchtext" name="InputForm" property="startsWith"  /> 
							
											<button onclick="submitSearch()">Search</button>
											<button onclick="advancedsearch()">Advanced Search</button>
											<button onclick="add()">Add</button>

											<button onclick="refresh()">Refresh</button>
											<button onclick="pageview()">Page View</button>
											<button onclick="numlistens()"># Listens</button>
											<button onclick="listguests()">Guests</button>
											<button onclick="search251()">Contact 251</button>
											<button onclick="searchPleiadeanMission()">Pleiadean Mission</button>
											<button onclick="upload()">Upload</button>		
																																
									</div>

									<div id="tabs-2">
									<h3>Search Contact 251 </h3>
										<a href="display251.do?method=displayLike">Contact 251</a> 
										
										
									</div>	
									<div id="tabs-3">
										<table class="customers" >
											<tr>
												<th>
												Help
												</th>
											</tr>
											<tr>
												<td>
												 	<ul>
												 		<li>Select a radio button and press edit to change an address</li>
												 		<li>Select a radio button and press delete to delete an address</li>
												 		<li>Press the add button to create a new address</li>
												 		<li>Press the search button to go to the search page.  On the search page
												 			select the desired search type (START,ANYWHERE,EXACT, END) then enter
												 			a value in the name field.  To find all names that have the word "and" in them.  Select
												 			ANYWHERE and type "and" in the name field.
												 		</li>
												 	</ul>			
												</td>
											</tr>
										</table>									
									</div>								
								</div>
						
					 </div> 					
 						<%-- 	
 						Name:<html:text styleId="searchtext" name="InputForm" property="startsWith"  onchange="submitSearch()" />  
 						<a href="#" onclick="submitSearch()" title="Search through current listing" ><img src="images/SearchImage.jpg" /></a> 
 						--%>
 						
 					</td>

 				 </tr>
 				 <tr>
 				  <td colspan="3">
 				  </td>
 				 </tr>				
		</table>		
 			 
		<div id="resizable4" class="ui-widget-content" align="center" style="overflow : auto; ">
		<h3 class="ui-widget-header">Contact and Radio Show Information</h3><br>
		
		
		<!-- Apply the style customers first and then override the width in large customers -->
       <!-- <TABLE  class="customers largecustomers" align="center"> -->
       <TABLE  align="center" width="100%" >
             <TR>
                <TH align="left" >Name</TH>
				<TH align="left" >Address</TH>
				<TH align="left" >Phone</TH>
				<TH align="left" >Email</TH>
				<TH align="left" >Web</TH>
				<TH align="left" >
						<select id="selectfilter" onchange="filterList()">
						  <option value="ALL">ALL</option>
						  <option value="RADIO_SHOW">RADIO_SHOW</option>
						  <option value="FRIEND">FRIEND</option>
						  <option value="GUITAR">GUITAR</option>
						  <option value="LISTENER">	LISTENER </option>
						  <option value="FAMILY">FAMILY</option>
						  <option value="FAMILY">MEIER_CONTACT</option>
						</select> 	
					
				</TH>
            </TR>   
			<logic:iterate name="studentlist" id="listUserId" indexId="counter">
				<% 
					String TrClassVar="";
				
					// Get the row that we are on
					int row = counter.intValue();
					
					// Set the name of our CSS
					TrClassVar = row % 2 == 0 ? "odd" : "even";
					
				%>
				<tr class="<%=TrClassVar  %>">

					<td nowrap="nowrap">
						 <a href="inputAction.do?method=delete&studentid=<bean:write name="listUserId" property="studentId"/>" title="Click here to delete student" ><img src="images/deletesmall.jpg" /></a>

						 <a href="inputAction.do?method=edit&studentid=<bean:write name="listUserId" property="studentId"/>" title="Click here to edit student...">
						 		<bean:write name="listUserId" property="studentName"/> 
						 </a>
					</td>
					<td><bean:write name="listUserId" property="addressInfo"/></td>
					<td><bean:write name="listUserId" property="phoneInfo"/></td>
					<td>
						<a href="mailto:<bean:write name="listUserId" property="email" />" >
							<bean:write name="listUserId" property="email"/>
						</a>	
					</td>
					<td>
						<a href="<bean:write name="listUserId" property="url" />" > 
							<bean:write name="listUserId" property="url" />
						</a>
					</td>
					<td>
						<bean:write name="listUserId" property="studentType" />
					</td>					
				</tr>
			</logic:iterate>	
		</TABLE>	       
  		</div>
  		
  		
  		</div>
 <!-- First Outer Div --> 		
  		
        </html:form>
    </body>
</html>

