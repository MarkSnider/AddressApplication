<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.actions.InputAction, com.forms.RadioShowForm, com.forms.EditForm, java.util.List, java.util.Iterator, java.text.DateFormat, java.text.SimpleDateFormat, java.util.Date" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>Radio Show</title>
			<style type="text/css">

			h3 { 
				font: italic normal 1.4em georgia, sans-serif;
				letter-spacing: 1px; 
				margin-bottom: 0; 
				color: #7D775C;
				}	
			#smalltext
			{
				width: 45px;
			} 
			#mediumtext
			{
				width: 90px;
			}
			#largeCombo
			{
				width: 110px;
			}
			#largetext
			{
				width: 265px;
			}  
			      
        	#buttonLook 
        	{
        		width: 	55px;

        	}

			
      					
			</style> 
		<script type="text/javascript">	
		function submitForm()
		{
			<%

			
			%>
			
			
			document.forms[0].action = "inputAction.do?method=populate";
			document.forms[0].submit();
		}	
		function goBack()
		{
			history.back();
		}	
		</script>
		
		<link type="text/css" href="css/ui-lightness/jquery-ui-1.8.17.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="js/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="js/jquery-ui-1.8.17.custom.min.js"></script>
		<script>
		$(function() {
			$( "#datepicker" ).datepicker({
				changeMonth: true,
				changeYear: true
			});
		});
		</script>
		<style type="text/css">
			/*demo page css*/
			body{ font: 62.5% "Trebuchet MS", sans-serif; margin: 50px;}
			.demoHeaders { margin-top: 2em; }
			#dialog_link {padding: .4em 1em .4em 20px;text-decoration: none;position: relative;}
			#dialog_link span.ui-icon {margin: 0 5px 0 0;position: absolute;left: .2em;top: 50%;margin-top: -8px;}
			ul#icons {margin: 0; padding: 0;}
			ul#icons li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}
			ul#icons span.ui-icon {float: left; margin: 0 4px;}
		</style>
				
</head>
<body>
	
        <html:form action="/Radio" >
 			<div class="ex">
 				<!-- Apply the style customers first and then override the width in smallcustomers -->
 				<table class="customers smallcustomers" align="center">
 				        <TR>
							<TH align="left" colspan="2">Radio Show</TH>
							
				
            			</TR>   
 					<tr>
 						<td>
 						Title:
 						</td>
 						<td>
 						
 						<html:text name="RadioShowForm" property="title" styleId="largetext" />
 						</td>
 					</tr>
 					<tr>
 						<td>
 						Description:
 						</td>
 						<td>
 						
 						
 						<html:textarea name="RadioShowForm" property="description" rows="4" cols="45"/> 
 						</td>
 					</tr> 
  					<tr>
 						<td>
 						Archive Listens:
 						</td>
 						<td>
 						
 						<html:text name="RadioShowForm" property="archiveListens" styleId="largetext" />
 						</td>
 					</tr>
 					
   					<tr>
 						<td>
 						Live Listens:
 						</td>
 						<td>
 						
 						<html:text name="RadioShowForm" property="liveListens" styleId="largetext" />
 						</td>
 					</tr>
    				<tr>
 						<td>
 							Date:
 						</td>
 						<td>
							<div class="demo">
							<%
							// Set the default date if we have one
							String ldate = request.getParameter("ldate");

							%>
						 	<input type="text" id="datepicker" name="showdate" value="<%=ldate %>"> 
						
							
							
							</div>
 						</td>
 					</tr>										
  					<tr>
 						<td align="center" colspan="2">
 						<html:submit property="method" value="OK" styleId="buttonLook" /> 
 						<button type="button" onclick="goBack()">Cancel </button>
 						</td>
 					</tr>	
 	 			</table>
 	 			
 			</div>  
 		</html:form>	
</body>
</html>