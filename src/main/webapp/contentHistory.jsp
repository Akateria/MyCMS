<%@ page import="hibernate.tables.ContentPositionHistory" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<div id="content_page_main_materials" class="container">

    <div class="row" style="height: 20px"></div>
    <table class="table table-striped">
        <tr>
            <th>User</th>
            <th>Status</th>
            <th>Date</th>
        </tr>
        <tbody>

        <% ArrayList<ContentPositionHistory> contentList= (ArrayList<ContentPositionHistory>) session.getAttribute("contentHistory");
            for(ContentPositionHistory content: contentList) {%>

        <tr>
            <th><%out.print(content.getUserId().getLogin());%></th>
            <th><%out.print(content.getStatus());%></th>
            <th><%out.print(content.getDate());%></th>
        </tr>

        <%}%>
        </tbody>
    </table>
</div>

<br>
<div align="left">
    <form action="/article_list" method="get">
        <button class="btn btn-default btn-inverse" style="margin-left: 25px" type="submit">Close</button>
    </form>
</div>
<br/>

</body>
</html>
