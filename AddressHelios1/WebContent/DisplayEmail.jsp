
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="com.forms.InputForm, com.dao.Student, java.util.Iterator, java.util.ArrayList, com.dao.Address,  com.dao.Phone, java.util.Set, com.dao.Email, com.dao.CurrentSort, com.constants.Constants, com.dao.Web, com.actions.InputAction" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Email</title>
<!-- CSS comes from a very interesting web site  -->
<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">
</head>
<body>
<hr>
	
  <table class="pure-table pure-table-bordered pure-table-striped">
    <thead>
      <tr>
	    <th>Email Address</th>
	  	<th>Email Type</th>

	  </tr>
	  
    </thead>
	
    <tbody>
    <logic:iterate name="emailslist" id="emailListId" indexId="counter">
    	<tr>
    		<td align="center">
    			
 				<a href="mailto:<bean:write name="emailListId" property="emailAddr" />" >
					<bean:write name="emailListId" property="emailAddr"/>
				</a>	   			
    		</td>
    		<td align="center">
    			<bean:write name="emailListId" property="emailType"/>
    		</td>   
    		   		  		 		
    	</tr>
    </logic:iterate>
    </tbody>
 </table>	
</body>
</html>