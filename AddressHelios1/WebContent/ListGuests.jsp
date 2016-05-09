<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Radio Shows</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
</head>
<body>
	
	<table class="customers" align="center">
		<%
		int row=0;
		String TrClassVar="";
		%>
		<tr>
			<th width="5px">#</th>
			<th>Name</th>				
			<th>Email</th>
		</tr>	
		<logic:iterate name="radioshows" id="listUserId">
		
		<% row++;
		TrClassVar = row % 2 == 0 ? "odd" : "even";
		%>
		<tr class="<%=TrClassVar  %>">
			 <td>
			 <%= row %>
			 </td>
			 <td>
		 		<bean:write name="listUserId" property="studentName"/> 
			 </td>		
			 <td>
			    <!-- Calls the emailHtmlLinks attribute in the student which returns the first email address in the set -->
		 		<a href="mailto:<bean:write name="listUserId" property="emailHtmlLinks"/>"><bean:write name="listUserId" property="emailHtmlLinks"/></a> 
			 </td>	
		</tr>
		</logic:iterate>	
	</table>	
</body>
</html>