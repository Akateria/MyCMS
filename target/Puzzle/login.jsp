<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="../pages/css/bootstrap.css" />
    <link  rel="stylesheet" href="<../pages/css/signin.css" />
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
    <title>Sign in</title>
</head>
<body>
<div class="container" style="width: 300px;">
    <form action= "/login" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <BR><BR>
        <input type="text" class="form-control" name="login" placeholder="login" required autofocus>
        <BR><BR>
        <input type="password" class="form-control" name="password" placeholder="Password" required>
        <BR><BR>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <BR><BR>
        <a href="restorePassword.jsp" >Forgot password?</a>
        <br/>
        <br><br>
        <a href="registration.jsp" >Registration</a>
    </form>
</div>
</body>
</html>