<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="com.actions.InputAction, com.constants.Constants, com.forms.RadioShowForm, java.util.List, java.util.Iterator, java.util.Date" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete</title>
			<style type="text/css">
        	#buttonLook 
        	{
        		width: 	55px;

        	}
      		
			#customers
			{
				font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
				width:55%;
				border-collapse:collapse;
			}
			#customers td, #customers th 
			{
			
			font-size:x-small;
			border:1px solid #98bf21;
			padding:3px 7px 2px 7px;
			}
			#customers th 
			{
			font-size:1.4em;
			text-align:left;
			padding-top:5px;
			padding-bottom:4px;
			background-color:#A7C942;
			color:#fff;
			}
			#customers tr.alt td 
			{
			color:#000;
			background-color:#EAF2D3;
			}
			
      					
			</style> 
			<script type="text/javascript">	
				function deleteradio() {
					
				// 	document.forms[0].action = "Radio.do?method=DeleteShow";
				// 	document.forms[0].submit();
					window.opener.deleteradio();
					self.close();
				}
				function goBack()
				{
					history.back();
				}
				/*
				* Closes the window since this page is opened as javascript window
				*/
				function closeTheWindow() {
					self.close();
				}
			</script>
</head>
<body>
	<html:form action="/Radio" >
 		<table align="center">
					<tr>
						<td>
						<h3>Are you sure that you would like to delete the following radio show? </h3>
							<div>
								Id: <bean:write name="RadioShowForm" property="showId"/>
								Title: <bean:write name="RadioShowForm" property="title"/>
							</div>								
						</td>
					</tr>
		</table>
 		<p align="center">
			<html:submit property="method" value="Delete" styleId="buttonLook"  onclick="deleteradio()" />
 			<button type="button" onclick="closeTheWindow()">Cancel </button> 
 		</p> 	
 </html:form> 
</body>
</html>