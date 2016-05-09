<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>251 Information</title>
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
	<style>

		#resizable1 { width: 89%; height: 575px; padding: 0.5em;}
		#resizable1 h3 { text-align: center; margin: 0; }			
			
	</style>	
</head>
<body>
	<div id="resizable1" class="ui-widget-content"  style="overflow: auto; ">
	<h3 class="ui-widget-header">251 Information</h3>
		<table width="100%">
			<tr bgcolor="white">
			<th width="5%" align="left">
				Number
			</th>
			<th align="left">
				Text
			</th>	
			</tr>	
		</table>
		<table width="100%">
		<logic:iterate name="251List" id="listUserId" indexId="counter">
			<%
			int row = counter.intValue() % 2;
			String color = "#FFFFFF";
			if (row == 0) {
				color = "#F0F0F0"; 
			}
			%>

		<tr bgcolor="<%= color %>">			
			<td width="5%" align="left">
				<a href="#">
				<bean:write name="listUserId" property="lineNumber"/>
				</a>
			</td>
			<td align="left">
				<bean:write name="listUserId" property="lineInfo"/>
			</td>

		</tr>
		</logic:iterate>
		</table>	
	   </div>
</body>
</html>