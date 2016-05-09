<%-- 
    Document   : input
    Created on : December 20, 2011
    Author     : Mark Snider
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
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
                primary: "ui-icon-pencil"
            }
        }).next().button({
            icons: {
                primary: "ui-icon-trash"
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
                primary: "ui-icon-help"
            }             
        });
	});    
	$(function() {
		$( "#tabs" ).tabs();
	});	
  </script>	
	<script type="text/javascript">
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
	</script>
	

    </head>
    <body style="font-size:62.5%;">
        <html:form action="/inputAction" >
		<%
        	InputForm theForm = (InputForm)session.getAttribute("InputForm");
			Phone Cell=null; Phone Home=null; Email homeEmail=null;  Email workEmail=null;
			int i=0;
			String TrClassVar="";
		
        	ArrayList<Student> theList = theForm.getStudentList();
        
			CurrentSort sort = (CurrentSort)request.getSession().getAttribute(Constants.CURRENT_SORT);
			// Set the defaults just in case there is no sort object in the session
			if (sort == null) {
				sort = new CurrentSort();
				sort.setCurrentSort(Constants.SORT_ASC);
				sort.setSortImage(Constants.SORT_UP_IMG);
			}
			String currSortImg = sort.getSortImage();
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
											<%=theList.size() %> records found, displaying 1 to <%=theList.size() %>										
									</ul>
									<div id="tabs-1">
				                      		Select Search Type:
				
											<html:select property="selectedMatchMode">
												<html:option value="START">START</html:option>
												<html:option value="ANYWHERE">ANYWHERE</html:option>
												<html:option value="END">END</html:option>
												<html:option value="END">EXACT</html:option>
											</html:select>
				                    								
											Name:<html:text styleId="searchtext" name="InputForm" property="startsWith"  /> 
							
											<button onclick="submitSearch()">Search</button>
											<button onclick="advancedsearch()">Advanced Search</button>
											<button onclick="add()">Add</button>
											<button onclick="submitForm()">Edit</button>
											<button onclick="submitDelete()">Delete</button>	
											<button onclick="refresh()">Refresh</button>
											<button onclick="pageview()">Page View</button>
											<button onclick="numlistens()"># Listens</button>
											<button onclick="listguests()">Guests</button>
											<button onclick="helppage()">Help</button>		
																																
									</div>

									<div id="tabs-2">
									This is a test
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
            	<!--  Display the sort image either upArrow or the downArrow -->
                <TH align="left" width="15%"><a href="inputAction.do?method=changesort" ><img src="<%= currSortImg %>" /></a>Name</TH>
                
                 
				<TH align="left" width="28%">Address</TH>

				<TH align="left" >Phone</TH>
				<TH align="left" width="23%">Email</TH>
				<!-- <TH align="left" >Notes</TH>  -->
				
				<TH align="left" >Web</TH>
				<TH align="left" >
					Type
				</TH>
            </TR>          
		
            
            
            <% 
            

             for (Student data: theList) {
            	Address theAddress = data.getStudentAddress();
            	Set<Phone> thePhonesSet = data.getStudentPhoneNumbers();
            	Set<Email> theEmailSet = data.getStudentEmailAddresses();
            	Set<Web> theWebSet = data.getStudentWebAddresses();
            	
            	Object[] thePhones = thePhonesSet.toArray();
            	
            	// This will be friend, guest on the radio show, etc...
            	String type = data.getStudentType();
            	
            	
            	if (thePhones != null && thePhones.length > 0) {
            		Home=(Phone)thePhones[0];
            		if (thePhones.length > 1) {
            			Cell=(Phone)thePhones[1];
            		}
            		
            	}
            	
            	if (theEmailSet != null) {
                	Object[] theEmails = theEmailSet.toArray();
                	
                	if (theEmails != null) {
                    	if (theEmails != null && theEmails.length > 0) {
                    		homeEmail=(Email)theEmails[0];
                    		if (theEmails.length > 1) {
                    			workEmail=(Email)theEmails[1];
                    		}
                    		
                    	}              		
                	}            		
            	}

            	
            	i += 1;
            	TrClassVar = i % 2 == 0 ? "odd" : "even";
            %>
           <%--  <TR class="<%=TrClassVar  %>"> --%>
           
				<TR class="<%=TrClassVar  %>">	
                <TD class="small" width="10%"  valign="top" nowrap="nowrap">
                
						<%
							// This code is so that we retain the checked value next to the radio button
							// After we execute the javascript which sets the more info in the request
							String idSelected = request.getParameter("studentid");		// Get the selected radio button
						    String checked="";			// initialize the checked state to empty string
						    String theCurrentId = "";	
							if (idSelected != null) {	// First time in this will be null as 
								long theId = data.getStudentId();
								if (theId != 0) {
									theCurrentId = String.valueOf(theId);		// get the string value
									
									if (theCurrentId.equals(idSelected)) {					// the id matches so
										checked="CHECKED";									// set the checked attribute
									}									
								}

							}
						%>
						
						                
					<input type="radio" name="studentid" value="<%= data.getStudentId() %>"  onclick = "clickedRadioButton(<%= data.getStudentId() %>)"   <%=checked%> > 
						
						<!-- Here is where the name is displayed -->
						<%= data.getStudentName() %>
                </TD>
                 
                <TD  nowrap="nowrap" valign="top">
					<%=theAddress.getStreet() %>&nbsp; <%=theAddress.getCity() %>,<%=theAddress.getState() %> &nbsp;<%= theAddress.getCountry() %>&nbsp;<%=theAddress.getZipcode() %><br />
                </TD >

                <TD   nowrap="nowrap" valign="top">
                <%=Home %><br>
				<%=Cell %>
                </TD>  
                <TD  valign="top">
                <A HREF="mailto:<%=homeEmail %>" title="Click to send an email"><%=homeEmail %></A><br>
				<A HREF="mailto:<%=workEmail %>" title="Click to send an email"><%=workEmail %></A>                

                </TD>

                <TD  nowrap="nowrap" valign="top">
                	<%
                	
                	Iterator<Web> it = theWebSet.iterator();
                	while(it.hasNext()) {
                		Web theWeb = it.next();
                		String url = theWeb.getUrl();
                		// This keeps us from taking up a line with a blank url
                		if (url != null && !url.equals("")) {
	                		String theURL = "<A HREF=";
	                		theURL += "\"";
	                		theURL += url;
	                		theURL += "\"";
	                		theURL += ">";
	                		theURL += url;
	                		theURL += "</A><BR>";
	                		out.println(theURL);
                		}
                	%>
                	
                	
                <% 	} %>
                	
                </td>   
                <TD  nowrap="nowrap" valign="top">
                <%= type %>
                </TD>                                         				
            </TR>
        
            <% } %>
        </TABLE>    
  		</div>
  		
  		</div>
 <!-- First Outer Div --> 		
  		
        </html:form>
    </body>
</html>

