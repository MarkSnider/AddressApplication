<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <link type="text/css" href="css/ui-lightness/jquery-ui-1.8.17.custom.css" rel="stylesheet" />
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>
	
	<script src="development-bundle/jquery-1.7.1.js"></script>
	<script src="development-bundle/ui/jquery.ui.core.js"></script>
	<script src="development-bundle/ui/jquery.ui.widget.js"></script>
	<script src="development-bundle/ui/jquery.ui.button.js"></script>

  <script>
  $(document).ready(function() {
	  
    $("button").button();
    
  });
    $(document).ready(function() {
	  

	    $("button3").button();
	    
    });  
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
		function show_alert()	
		{
			alert("This is the first button new test");
		}
		function show_alert2()	
		{
			alert("This is the second button");
		}
		function show_alert3()	
		{
			alert("This is the third button");
		}	
		function show_alert4()	
		{
			window.location="inputAction.do?method=add";
		}
		function show_alert5()	
		{
			window.location="inputAction.do?method=add";
		}		
		function show_alert6()	
		{
			alert("Edit Button");
		}			
	</script>	
</head>
<body style="font-size:62.5%;">

<h3>Button Example</h3>
  
<button onclick="show_alert()">Button1</button>
<button onclick="show_alert2()">Button2</button>
<button3 onclick="show_alert3()">Button3</button3>

<br><br>
<form>
<div class="demo">

<button onclick="show_alert4()">Search</button>
<button onclick="show_alert5()">Add</button>
<button onclick="show_alert6()">Edit</button>
<button>Button with two icons and no text</button>

</div><!-- End demo -->
</form>
</body>
</html>