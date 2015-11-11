<%@ page import="context.UserSession" %>
<%@ page import="hibernate.tables.userInfo.UserRole" %>
<%@ page import="context.ContentSession" %>
<%@ page import="hibernate.tables.Content" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
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

    <h1 style="text-align:center">Articles</h1>
</div>
<div id="content_page_main_materials" class="container">
    <div class="row">

        <div class="container" style="width: 300px;">
            <%ContentSession.getContentSession().removeInstance();%>
            <form action="content_view.jsp" method="post">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Add new</button>
            </form>
        </div>

        <div class="span12">
            <div class="row">
                <div class="span6">
                </div>
                <div id="search-refresh-bg" class="span6">
                    <form action="/add_to_front" method="get">
                </div>
            </div>
            <div class="row" style="height: 20px"></div>
            <div class="row">
                <table class="table table-striped">
                    <tr>
                        <th>Select</th>
                        <th>Name</th>
                        <th>Author</th>
                        <th>History</th>
                        <th>Status</th>
                        <th>Next step</th>

                        <%--<th>AUTHOR</th>--%>
                        <%--<th>CORRECTOR</th>--%>
                        <%--<th>LAST CORRECTOR</th>--%>
                        <%--<th>EDITOR</th>--%>
                        <%--<th>DELETED</th>--%>
                        <%--<th>WEBSITE</th>--%>
                        <%--<th>TO MAIN</th>--%>
                        <%--<th>EDITOR NAME</th>--%>
                    </tr>
                    <tbody>

                    <% ArrayList<Content> contentList= (ArrayList) session.getAttribute("contentList");
                        for(Content content: contentList) {%>

                    <tr>
                        <%String statusRef = "/editstatus?id="+ content.getId();
                            String historyRef = "/contenthistory?contentId=" + content.getId();
                            String contentDetails = "/content_details?id=" + content.getId();
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
                            String lastEdit;
                            if(content.getLastEdit() != null){
                                lastEdit = dateFormat.format(content.getLastEdit());
                            }
                            else {
                                lastEdit = "";
                            };%>
                        <th><input type="checkbox" name="contentList" value=<%out.print(content.getId());%> ></th>
                        <th><a href= <%out.print(contentDetails);%>><%out.print(content.getName());%></a></th>
                        <th><% out.print(content.getAuthor().getLogin());%></th>
                        <th><a href=<%out.print(historyRef);%>> show history</a></th>
                        <th><% out.print(content.getArticleStatus());%></th>
                        <th><a href=<%out.print(statusRef);%>> finish working with article</a></th>

                        <%--<th><% out.print(lastEdit);%></th>--%>
                    </tr>

                    <%}%>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="span6">
            <button class="btn btn-success" type="submit">Add to top</button>
        </div>
    </div>
</div>
</div>
</form>
<%
    int pageAmount = (Integer) session.getAttribute("pageAmount");
    int currentPage = 2;
%>
<div class="paginator text-center">
    <ul class="pagination">
        <li><a href="/article_list?page=1">1</a></li>
        <%
            while (currentPage <= pageAmount) {
                String ref = "/article_list?page=" + currentPage;
        %>
        <li><a href=<%out.print(ref);%>><%out.print(currentPage);%></a></li>
        <%
                currentPage++;
            }
        %>
    </ul>
</div>
<script src="../dist/js/pushy.min.js"></script>
</body>
</html>

