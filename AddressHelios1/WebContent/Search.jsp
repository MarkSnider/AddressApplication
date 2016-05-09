<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.actions.InputAction, java.util.Iterator, com.forms.SearchForm, java.util.ArrayList, java.util.List, com.dao.StudentType, com.service.StudentTypeService" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>Search</title>
		<script type="text/javascript">	
		function submitForm()
		{
			document.forms[0].action = "inputAction.do?method=populate"
			document.forms[0].submit();
		}	
		</script>
</head>
<body>
	    <p>
	        
<!--     		<a href="inputAction.do?method=populate" title="Press here to cancel and go back to main display."> <img src="Back.jpg" /> </a> -->
    	</p>
   
        <html:form action="/Search" >
 			<div class="ex">
 				
 			<table class="customers" align="center">
				<tr>
				   <th>
				   Advanced
				   </th>
				   <th>
				   Search
				   </th>				   
				</tr>
               <tr>
                    <td>
                        Select Search Type:
                    </td>
                    <td>
						<html:select property="selectedMatchMode">
							<html:option value="START">START</html:option>
							<html:option value="ANYWHERE">ANYWHERE</html:option>
							<html:option value="END">END</html:option>
							<html:option value="END">EXACT</html:option>
						</html:select>
                    </td>
                </tr>           
                
                		
 			   <tr>
 					<td>Name:</td><td><html:text name="SearchForm" property="startsWith" /> </td>
 						
 				</tr>

				<tr>
					<td>Student type:</td>
					<td>
							<html:select property="studentType">
								<%
								List<StudentType> theStudentTypeList = StudentTypeService.selectStudentTypes();
			                	Iterator<StudentType> it = theStudentTypeList.iterator();
			                	while(it.hasNext()) {
			                		StudentType theStudentType = (StudentType)it.next();
			                		String type = theStudentType.getStudentType();
								%>
								<!-- Display the type as an option -->
								<html:option value="<%= type %>"><%= type %></html:option>
								<% } %>
							</html:select> 	
					</td>			
				</tr>
 				<tr>
 					<td align="center" colspan="2">
 						<html:submit property="method" value="OK" styleId="buttonLook" /> 
 						<html:submit value="Cancel" onclick="submitForm()" styleId="buttonLook" />
 					</td>
 				</tr>				
 			</table>
 				 
				 
 			</div>  
 		</html:form>	
</body>
</html>