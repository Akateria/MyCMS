<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Restoring password</title>
</head>
<body>
	<%String email = (String)request.getParameter("email");
		if (email==null)
			email = "";
	%>
	<form action="restorePassword" method="post">
		<h3>Please, enter your email.</h3>

		<h4>
            We will send you a letter with a restoring link in 5 minutes. You will should go by reference
		</h4>
		<table border="1">
			<tr>
				<td>Email</td>
				<td><input name="email" type="text" value="<%=email%>"></input></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="submit"> </input></td>
			</tr>

		</table>
	</form>
</body>
</html>