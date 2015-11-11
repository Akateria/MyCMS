<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Login</title>
</head>
<body>
	<h1>Your registration was successful!</h1>
	<%
		String firstname = (String)request.getAttribute("firstname");
		String lastname = (String)request.getAttribute("lastname");
		if (firstname==null)
			firstname="noname";
		if (lastname==null)
			lastname="nolastname";
		// 		out.println(login + " "+password);
		session.setAttribute("firstname", firstname);
		session.setAttribute("lastname", lastname);
	out.println("Welcome " + firstname +" "+lastname +" <br/> <a href=\"logout.jsp\" >Logout</a>");
	%>
	<br/>
	<a href="./">Main page</a>
</body>

</html>