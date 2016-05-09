<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
 
<html>
<head>
</head>
<body>
<h1>Upload File</h1>
 

 
<html:form action="/UploadFile" method="post" enctype="multipart/form-data">
<br />
	Upload File For Radio Show : 
	<html:file property="file" size="50" />
<br />
<br />
<html:submit>
	Submit
</html:submit>
 
</html:form>
 
 
</body>
</html>