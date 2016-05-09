<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.constants.Constants, java.io.PrintWriter" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
			<style type="text/css">
			div.ex
			{
			width: 30%;
			padding:2px;
			border:1px solid #0099FF;
			margin:0px;
			/* color: 	#707070; */
			background-color: #F8F8F8;
			font-size:90%;
			
			}
			h3 { 
				font: italic normal 1.4em georgia, sans-serif;
				letter-spacing: 1px; 
				margin-bottom: 0; 
				color: red;
				}	
				
			h4 { 
				font: italic normal 1.2em georgia, sans-serif;
				letter-spacing: 1px; 
				margin-bottom: 0; 
				color: red;
				}			
			</style> 
</head>
<body>
	<html:form action="/inputAction" >
	
	    <p>
	        
    		<a href="inputAction.do?method=populate" title="Press here to cancel and go back to main display."> <img src="Back.jpg" /> </a>
    	</p>
	<div class="ex">
	
		<h4>An error occurred processing your request.</h4>
		<p>
		<b><%= request.getAttribute(Constants.EXCEPTION_REPORT) %></b>
		</p>
		<p align="center">
			<html:submit property="method" value="ok" />
		</p>
	</div>
	</html:form>
	
</body>
</html>