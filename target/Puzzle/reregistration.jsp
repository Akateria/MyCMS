<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Registration in CMSPuzzle</title>
</head>
<body>
<!-- /CMSPuzzle-1.0-SNAPSHOT/registration -->
	<form action="registration" method="post">
		<%
			String firstName = (String) request.getAttribute("firstname");
			String lastName = (String) request.getAttribute("lastname");
			String login = (String) request.getAttribute("login");
			String email = (String) request.getAttribute("email");
			// 			String role = (String) request.getAttribute("role");
			if (firstName == null)
				firstName = "";
			if (lastName == null)
				lastName = "";
			if (login == null)
				login = "";
			if (email == null)
				email = "";
			// 			if (role == null)
			// 				role = "";
		%>
		
		<h3>This login is exist! Please try another!</h3>
		<h4>
			After registration we will send you a letter. 
			<br /> You should to go by reference on it to confirm your registration
		</h4>

		<table border="1">
			<tr>
				<td>Your first name</td>
				<td><input name="firstname" type="text" value="<%=firstName%>"></input></td>
			</tr>
			<tr>
				<td>Your last name</td>
				<td><input name="lastname" type="text" value="<%=lastName%>"></input></td>
			</tr>
			<tr>
				<td>Login</td>
				<td><input name="login" type="text" value="<%=login%>"></input></td>
			</tr>
			<tr>
				<td>Email</td>
				<td><input name="email" type="text" value="<%=email%>"></input></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input name="password" type="password"></input></td>
			</tr>
			<tr>
				<td>Password confirmation</td>
				<td><input name="password_confirmation" type="password"></input></td>
			</tr>
			<!-- 			<tr> -->
			<!-- 				<td>Role</td> -->
			<%-- 				<td><input name="role" value="<%=role%>"> </input></td> --%>
			<!-- 			</tr> -->

			<tr>
				<td colspan="2"><input type="submit" value="submit"> </input></td>
			</tr>
		</table>

	</form>
</body>
</html>