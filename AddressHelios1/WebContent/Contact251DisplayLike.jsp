<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.actions.Contact251Action, com.forms.SearchContact251Form" %>
<html:html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact 251 Display Like</title>
<script type="text/javascript" >
	function handleReturnPressed(event) {
		if(event.keyCode == 13){ 
		
			submitForm();
		}
		

	}
	function submitForm()
	{
		
		document.forms[0].action = "display251.do?method=search";
		document.forms[0].submit();
	}	
	function viewLineInfo(line) {
		
		// set the URL to point to the action
		var targetUrl="display251.do?method=expand&linenumber="+line;
		
	    // Open the window as desired, but make it resizable
	    popWin = window.open(targetUrl, 'AppWindow', 'width=1000,height=700,left=100,top=50,screenX=0,screenY=0,location=no,status=no,menubar=yes,toolbar=yes,scrollbars=yes, resizable=yes'); 
		
		//document.forms[0].action = "display251.do?method=expand&linenumber="+line;
		//document.forms[0].submit();
		
	}
</script>
</head>
<body>

	<h3>Contact 251 Display Like</h3>
	   <html:form action="/display251" >
			Search Text <input type="text" name="searchText" id=searchText" onkeypress="handleReturnPressed(event)"/>
			<input type="submit" name="searchButton" id=searchButton" value="search"/>
		</html:form>
	
		<logic:iterate id="EachPerson" name="datalist" type="com.dao.Contact251" indexId="count">
			<%
				int result = count.intValue() % 2; 	// Take mod to find out if even or odd
				String color="#FFFFFF";				// Make default white
				if (result == 0) {					// check if even	
					color="#F0F0F0";				// make light gray
				}
			%>	
			<table width="100%">
				<tr  bgcolor="<%= color %>">
	
					<td width="10%">
						<a href="#" title="View more information around this line." onclick="viewLineInfo('<bean:write name="EachPerson" property="lineNumber" />')"> 
							<bean:write name="EachPerson" property="lineNumber" /> 
						</a>
					</td>																				
					<td width="90%">
						<bean:write name="EachPerson" property="info" /> 
					</td>
												
				</tr>
			</table>	
		</logic:iterate> 		
</body>
</html:html>