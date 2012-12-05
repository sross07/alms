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

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Account</title>

<link rel="stylesheet" type="text/css" media="all" href="Styles/salms.css"  />
</head>
<body>
	<div id="content">

		<div id="identity">
			<h1>SALMS -- Add Account</h1>
		</div>		

		  <form name="myform" method="post" action="AddAccount.jsp">

				<fieldset class="form-submit">
					<div class="form-pair">
						<div class="form-item">
							<label for="uid">User Name:</label>
						</div>
						<div class="form-value">
							<input name="userName" id="userName" type="text" size="30"  />
						</div>
					</div>

					<div class="form-pair">
						<div class="form-item">
							<label for="pid">Password:</label>
						</div>
						<div class="form-value">
							<input id="passWord" name="passWord" type="password" size="30" autocomplete="off" />
						</div>
					</div>

					<div class="form-pair">
						<div class="form-item">
							<label for="oid">MSH 6:</label>
						</div>
						<div class="form-value">
							<input id="accountOid" name="accountOid" type="text" size="30" />
						</div>
					</div>

					<div class="form-pair">
						<div class="form-value">
							<input  type="submit" class="input-submit" name="Submit" value="Register" alt="Login" />
<!-- 							<input  type="reset" class="input-reset" name="Reset" value="Reset" alt="Reset" /> -->
							<input type="hidden" name="submit" value="true">
						</div>
					</div>
				</fieldset>
			</form>


		<div id="offsetlinks">
			<ul>
				<li><a href="">SALMS Collaboration Website</a></li>
				<li><a href="">SALMS Portal for management of accounts</a></li>
				<li><a href="https://github.com/sross07/alms">ALMS on GitHub</a></li>
			</ul>
		</div>

		<div id="footer">
     				<p>
     					<a href="">link in a footer</a>
     				</p>
     				<p>
						<strong>Note:</strong> This page is for registration of an account to test on SALMS.
					</p>
					<p>
						This project is being host by Cornell University for the sole purpose of promoting interoperability in laboratories
					</p>
					<p>
						Please contact Scott Ross at Cornell University for more information
					</p>

					All Rights Reserved.
  		</div><!--footer-->
  </div><!--content-->

</body>
</html>
