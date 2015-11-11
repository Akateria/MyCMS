
<%@ page import="context.UserSession" %>
<%@ page import="hibernate.tables.userInfo.UserRole" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="hibernate.tables.User" %>
<%@ page import="hibernate.tables.Content" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Puzzle CMS</title></head>
<meta charset="utf-8">
<meta httap-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../dist/css/style.css">
<link rel="stylesheet" href="../dist/css/normalize.css">
<link rel="stylesheet" href="../dist/css/demo.css">
<link rel="stylesheet" href="../dist/css/pushy.css">
<link rel="stylesheet" href="../dist/css/bootstrap/css/bootstrap.css">
<link id="header_template" rel="import" href="templates/header_template.html">
<link id="footer_template" rel="import" href="templates/footer_template.html">
<link id="content_page" rel="import" href="templates/content_page.html">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
</head>
<body>
<div class="container" style="width: 300px;">
    <%Content content = (Content)session.getAttribute("article");
        UserRole role = UserSession.getUserSession().getRole();
        ArrayList<User> users = ( ArrayList<User>)session.getAttribute("users");%>

    <h2 class="form-signin-heading">
        Current status: <%out.print(content.getArticleStatus().toString());%>
    </h2>

    <div class="container" style="width: 300px;">
        <form action="/changeStatus?status=DELETED&id=<%out.print(content.getId());%>" method="post">
            <button id = "btnDelete" class="btn btn-lg btn-primary btn-block" type="submit">Delete article</button>
        </form>
    </div>


    <div class="container" style="width: 300px;">
        <form action="/changeStatus?status=CORRECTOR&id=<%out.print(content.getId());%>" method="post">
            <button id = "btnCorrector" class="btn btn-lg btn-primary btn-block" type="submit">Appoint corrector</button>
        </form>
    </div>

    <div class="container" style="width: 300px;">
        <form action="/changeStatus?status=EDITOR&id=<%out.print(content.getId());%>" method="post">
            <button id = "btnEditor" class="btn btn-lg btn-primary btn-block" type="submit">Appoint editor</button>
        </form>
    </div>

    <div class="container" style="width: 300px;">
        <form action="/changeStatus?status=AUTHOR&id=<%out.print(content.getId());%>" method="post">
            <button id = "btnAuthor" class="btn btn-lg btn-primary btn-block" type="submit">Appoint author</button>
        </form>
    </div>


    <div class="container" style="width: 300px;">
        <form action="/changeStatus?status=WEBSITE&id=<%out.print(content.getId());%>" method="post">
            <button id = "btnPublish" class="btn btn-lg btn-primary btn-block" type="submit">Publish</button>
        </form>
    </div>

<c:set var="role" scope="session" value="<%out.print(role.toString());%>"/>


</body>
</html>
