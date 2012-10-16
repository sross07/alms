<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@page import="org.web.beans.*" %> 
<jsp:useBean id="AccountBean" class="org.web.beans.AccountBean" scope="request"/>
<jsp:setProperty name="AccountBean" property="*" />
<%
	
	if (request.getParameter("submit")!=null){
		if (request.getParameter("submit").toString().equalsIgnoreCase("true")){
			if (AccountBean.validateData())
				AccountBean.processData();
			
		}
	}
%>    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Account</title>
</head>
<body>

<form name="myform" action="AddAccount.jsp" method="post">
User Name : <input type="text" name="userName" value=""><br>
Password : <input type="password" name="passWord" value=""><br>
Account OId: <input type="text" name="accountOid" value=""><br>
<input type="SUBMIT" value="OK">
<input type="HIDDEN" name="submit" value="true">
</form>

</body>
</html>