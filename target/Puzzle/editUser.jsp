<%@ page import="context.UserSession" %>
<%@ page import="hibernate.tables.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Puzzle CMS</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="../dist/css/style.css">
    <link rel="stylesheet" href="../dist/css/normalize.css">
    <link rel="stylesheet" href="../dist/css/demo.css">
    <link rel="stylesheet" href="../dist/css/pushy.css">
    <link rel="stylesheet" href="../dist/css/bootstrap/css/bootstrap.css">
    <link id="content_page" rel="import" href="templates/content_page.html">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>

    <link id="header_template" rel="import" href="templates/header_template.html">
    <link id="footer_template" rel="import" href="templates/footer_template.html">
</head>
<body>

<form action="/commituser" method="get">

    <%User user = (User)session.getAttribute("userName");%>

    <form action="/commituser">
        <input type="text" name="login" title="User" value=<%out.print(user.getLogin());%>>
        <BR>
        New role:
        <BR>
        <select name="newRole">
            <option value="ADMINISTRATOR">ADMINISTRATOR</option>
            <option value="CORRECTOR">CORRECTOR</option>
            <option value="EDITOR">EDITOR</option>
            <option value="AUTHOR">AUTHOR</option>
            <option value="UNCONFIRMED">UNCONFIRMED</option>
        </select>
        <button class="btn btn-default btn-inverse" style="margin-left: 25px" type="submit">Submit</button>
    </form>
</body>
</html>
