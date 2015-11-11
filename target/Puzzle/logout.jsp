
<%@page import="hibernate.general.HibernateFactory"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Puzzle CMS| Logout</title>
</head>
<body>
	<br />
	<h1>
		User
		<%=request.getAttribute("firstname")%>
		<%=request.getAttribute("lastname")%>
		log out
	</h1>
	<br />
	<a href="./">Main page</a>
	<br />
	<a href="login.jsp">Log in</a>
s
</body>
</html>
