<%@ page import="context.UserSession" %>
<%@ page import="hibernate.tables.userInfo.UserRole" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Puzzle team</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
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
<script>
    var link = document.querySelector('#header_template');
    var content = link.import;
    var el = content.querySelector('.header');
    document.body.appendChild(el.cloneNode(true));
</script>
<nav class="pushy pushy-left">
    <ul>
        <li><a href="/article_list">Materials</a></li>
        <li><a href="/front">Front page</a></li>
        <li><a href="/tags">Tags</a></li>
        <%UserSession userSession = UserSession.getUserSession();
        if(userSession.getRole() == UserRole.ADMINISTRATOR){%>
        <li><a href="/userlist">Users</a></li>
        <%}%>
        <li><a href="/logout">Logout</a></li>
    </ul>
</nav>
<div class="site-overlay"></div>
<div id="container">
    <div class="menu-btn">&#9776; Menu</div>
    <h1 style="text-align:center">Welcome</h1>

    <h1 style="text-align:center">Puzzle CMS</h1>
</div>
<script>
    var link = document.querySelector('#footer_template');
    var content = link.import;
    var el = content.querySelector('.footer');
    document.body.appendChild(el.cloneNode(true));
</script>
<script src="../dist/js/pushy.min.js"></script>
</body>
</html>

