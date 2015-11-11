<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Form of restoring password</title>
</head>
<body>
	<form action="successRestore" method="post">
		<%
			String login = (String) request.getAttribute("login");
			if (login == null)
				login = "";
		%>
		<h3>Please enter new password</h3>
		<h4>
			<br /> Password must be more than 8 symbols and less than 100
		</h4>
		<table border="1">
			<tr>
				<td>New password</td>
				<td><input name="password" type="password"></input></td>
			</tr>
			<tr>
				<td>New password confirmation</td>
				<td><input name="password_confirmation" type="password"></input></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="submit"> </input></td>
			</tr>
		</table>
		<input type="hidden" name="login" value="<%=login%>" ></input>
	</form>
</body>
</html>