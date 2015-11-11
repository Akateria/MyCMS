<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Puzzle CMS</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <br>
    <h1>Login is successful</h1>
    <jsp:useBean id="user" class="context.UserSession" scope="application"/>
    <br>

    <form action="logout.jsp" method="post">
        <input type="submit" value="LOGOUT"/>
    </form>
</body>
</html>
