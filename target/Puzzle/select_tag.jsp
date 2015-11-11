<%@ page import="hibernate.tables.Tag" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Puzzle CMS|Tags</title></head>
</head>
<body>
<form action= "/selectTags" method="get">
    <div id="content_page_main_materials" class="container">
        <div class="row">

        </div>

        <div class="row" style="height: 20px"></div>
        <table class="table table-striped">
            <tr>
                <th>Name</th>
                <th>Select</th>
            </tr>
            <tbody>
                <% ArrayList<Tag> tagList= (ArrayList) session.getAttribute("tagList");
            for(Tag tag: tagList) {%>

            <tr>
                <th><% out.print(tag.getName());%></th>
                <th><input type="checkbox" name="selected" value=<%out.print(tag.getId());%> ></th>
            </tr>
                <%}%>
        </table>
    </div>

    <button><b>Submit</b></button>
</form>

</body>
</html>
