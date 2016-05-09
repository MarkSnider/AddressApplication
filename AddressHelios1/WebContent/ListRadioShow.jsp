<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>Radio Shows</title>

</head>
<body>
	
	<table class="customers" align="center">
		<%
		int row=0;
		String TrClassVar="";
		%>	
		<tr>
			<th width="5px">#</th>
			<th>Id</th>				
			<th>Title</th>
			<th>Description</th>
			<th>Archive</th>
			<th>Live</th>
			<th>Date</th>
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
		 		<bean:write name="listUserId" property="showId"/> 
			 </td>		
			 <td>
		 		<bean:write name="listUserId" property="title"/> 
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
</body>
</html>