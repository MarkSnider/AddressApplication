<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
  <link type="text/css" href="css/ui-lightness/jquery-ui-1.8.17.custom.css" rel="stylesheet" />
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
	
	<script src="development-bundle/jquery-1.7.1.js"></script>
	<script src="development-bundle/ui/jquery.ui.core.js"></script>
	<script src="development-bundle/ui/jquery.ui.widget.js"></script>
	<script src="development-bundle/ui/jquery.ui.button.js"></script>
<title>Test2</title>
 <script>

	$(function() {
		$( ".demo button:first" ).button({
            icons: {
                primary: "ui-icon-search"
            }
        }).next().button({
            icons: {
                primary: "ui-icon-plus"
            }
        }).next().button({
            icons: {
                primary: "ui-icon-pencil"
            }
        }).next().button({
            icons: {
                primary: "ui-icon-gear",
                secondary: "ui-icon-triangle-1-s"
            },
            text: false
        });
	});    
  </script>
 	<script type="text/javascript">

		function show_alert4()	
		{
			var theId = document.forms[0].startsWith.value.length;
			  
			if (theId > 0) {
				document.forms[0].action = "inputAction.do?method=search";
				document.forms[0].submit();				
			}
		}
		
	</script>	
</head>
<body style="font-size:62.5%;">
	<h3>Strange</h3>
	<html:form action="/inputAction" >
	<div class="demo">
	Name:<html:text styleId="searchtext" name="InputForm" property="startsWith"  /> 
	

		<button onclick="show_alert4()">Search</button>


	</div>
	<!-- End demo -->	
	</html:form>
	
</body>
</html>