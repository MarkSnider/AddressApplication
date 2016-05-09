<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display File</title>
</head>
<body>
	<h3>
		<% 
		String thePath = (String)request.getSession().getAttribute("FILEPATH");
		%>
		<%=thePath %>
	</h3>
	<table>
		<logic:iterate name="FILE_DATA" id="rec" indexId="counter">
			<tr>
				<td><bean:write name="rec" property="data" /></td>
			</tr>
		</logic:iterate>
	</table>
</body>
</html>