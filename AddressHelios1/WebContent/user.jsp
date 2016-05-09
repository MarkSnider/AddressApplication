
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page import="com.forms.InputForm, com.dao.Student, java.util.ArrayList, com.dao.Address,  com.dao.Phone, java.util.Set, com.dao.Email, com.dao.CurrentSort, com.constants.Constants" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Page View</title>
        <link href="css/style.css" rel="stylesheet" type="text/css" />
	      <style type="text/css">
        	#buttonLook {
        		width: 		65px;
        	}
        </style>        
    </head>
    <body style="font-size:74.5%;">

		
        <display:table  class="customers" id="data" name="sessionScope.UserForm.actorList" requestURI="/userAction.do" pagesize="10" export="true">

            <display:column property="studentName" title="User Name" sortable="true"  />
            <display:column property="addressInfo" title="Address" sortable="true"  />
            <display:column property="phoneInfo" title="Phone" sortable="true"  />
            
            <display:column property="emailInfo" title="Email" sortable="true"  />
            

            <display:setProperty name="export.excel.filename" value="ActorDetails.xls"/>
            <display:setProperty name="export.pdf.filename" value="ActorDetails.pdf"/>
            <display:setProperty name="export.pdf" value="true" />
        </display:table>
       
    </body>

</html>
