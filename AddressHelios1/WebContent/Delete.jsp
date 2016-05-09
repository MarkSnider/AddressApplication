<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete</title>
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
 
			#Form 
			{
			font-family:"Trebuchet MS", Arial, Helvetica, sans-serif;
			width:30%;
			border-collapse:collapse;
			}
			#Form td, #Form th 
			{
			
			font-size: small;
			
			padding:3px 7px 2px 7px;
			background-color: 	#E0E0E0;
			}
			#Form th 
			{
			font-size:1.4em;
			text-align:left;
			padding-top:5px;
			padding-bottom:4px;
			background-color:#A7C942;
			color:#fff;
			}
			#Form tr.alt td 
			{
			color:#000;
			background-color:#EAF2D3;
			}

      		


	 
							
		</style>				
		<script type="text/javascript">	
		function goBack()
		{
			history.back();
		}	
		</script>			
</head>
<body>
	    <p>
	        
<!--     		<a href="inputAction.do?method=populate" title="Press here to cancel and go back to main display."> <img src="Back.jpg" /> </a> -->
    	</p>
        <html:form action="/Delete" >
 			<div class="ex">
  	        <TABLE class="customers" align="center"> 
            <TR>
				<TH align="left" colspan="2">Delete</TH>
				

            </TR>  				
 				<table class="customers" align="center">
 					<tr>
 						<td align="center">
 						<h1>Are you sure that you want to delete 
 						<bean:write name="DeleteForm" property="name" />? </h1>
 						
 						</td>
 						
 					</tr>

 					<tr>
 						<td align="center" colspan="4">
 						<html:submit property="method" value="OK" styleId="buttonLook" /> 
 						<button type="button" onclick="goBack()">Cancel </button> 
 						</td>
 					</tr>				
 				</table>
 				 
				 
 			</div>  
 		</html:form>	
</body>
</html>