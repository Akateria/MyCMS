<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
    <title>Registration in CMSPuzzle</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="../pages/css/bootstrap.css" />
    <link  rel="stylesheet" href="<../pages/css/signin.css" />
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
</head>
<body>
<form action="registration" method="post">
    <%
        String firstName = (String) request.getAttribute("firstname");
        String lastName = (String) request.getAttribute("lastname");
        String login = (String) request.getAttribute("login");
        String email = (String) request.getAttribute("email");
        Boolean pasNotEquals = false;
        pasNotEquals = (Boolean) request.getAttribute("passwords_not_equal");
        Boolean reregister = false;
        reregister = (Boolean) request.getAttribute("login_exists");
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
// 			if (pasNotEquals.equals(true)){
// 				out.println("<h3>Passwords are not equal</h3>");
// 			}
// 			if (reregister.equals(true)){
// 				out.println("<h3>Login has already existed </h3>");
// 			}
    %>
    <div class="container" style="width: 300px;">
        <h4>
            <br/>Password must be more than 8 symbols and less than 100 <BR> <BR>
        </h4>
    </div>
    <div class="container" style="width: 300px;">
        <td>Your first name</td>
        <td><input class="form-control" name="firstname" type="text" value="<%=firstName%>"></td>

        <td>Your last name</td>
        <td><input class="form-control" name="lastname" type="text" value="<%=lastName%>"></td>

        <td>Login</td>
        <td><input class="form-control" name="login" type="text" value="<%=login%>"></td>

        <td>Email</td>
        <td><input class="form-control" name="email" type="text" value="<%=email%>"></td>

        <td>Password</td>
        <td><input class="form-control" name="password" type="password" maxlength="100"></td>

        <td>Password confirmation</td>
        <td><input class="form-control"name="password_confirmation" type="password" maxlength="100"></td>
        <BR><BR>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>


    </div>
</form>

</body>
</html>