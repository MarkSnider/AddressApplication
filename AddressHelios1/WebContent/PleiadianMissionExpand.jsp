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
<title>Pleiadian Mission Expand</title>

</head>
<body>

	<h3>Pleiadian Mission Expand</h3>

	
		<logic:iterate id="EachPerson" name="datalist" type="com.dao.PleiadeanMission" indexId="count">
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
 
							<bean:write name="EachPerson" property="lineNumber" /> 

					</td>																				
					<td width="90%">
						<bean:write name="EachPerson" property="info" /> 
					</td>
												
				</tr>
			</table>	
		</logic:iterate> 		
</body>
</html:html>