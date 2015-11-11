<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ taglib uri="http://ckeditor.com" prefix="ckeditor" %>
<%@page import="com.ckeditor.CKEditorConfig"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page import="context.TagListSingleton" %>
<%@ page import="context.ContentSession" %>
<%@page language="Java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<html lang="en">

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

<script>
    var link = document.querySelector('#header_template');
    var content = link.import;
    var el = content.querySelector('.header');
    document.body.appendChild(el.cloneNode(true));
</script>


<script>
    var link = document.querySelector('#content_add_article');
    var content = link.import;
    var el = content.querySelector('#content_add_article');
    document.body.appendChild(el.cloneNode(true));
</script>

<%ContentSession contentSession = ContentSession.getContentSession();%>

<form action="/add_content" method="post">
    <div id="content_add_article">
        <div class="container">
            <div class="row-fluid">

                <table>
                    <tr>
                        <th></th>
                        <th>Name <input type="text"  name="name" class="input-block-level" value="<%out.print(contentSession.name != null ?contentSession.name: "");%>"></th>
                    </tr>
                    <tr>
                        <th></th>
                        <th>Image name <input type="text" name="imageName" class="input-block-level" value="<%out.print(contentSession.image != null ?contentSession.image: "");%>"></th>
                    </tr>
                </table>
            </div>
            <div align="center">
                <p>
                    <%
                        String text;
                        if (contentSession.text != null){
                            text = contentSession.text;
                        }
                        else {
                            text = "Type here...";
                        }
                        Map<String, String> attr = new HashMap<String, String>();
                        CKEditorConfig settings = new CKEditorConfig();
                        settings.addConfigValue("toolbar", "Full");
                    %>
                    <ckeditor:editor textareaAttributes="<%=attr %>"
                                     basePath="../ckeditor/" config="<%=settings %>"
                                     editor="text" value="<%=text%>"/>
                </p>
            </div>
            <button class="btn btn-default btn-inverse" style="margin-left: 25px" type="submit">Next</button>
        </div>
    </div>
    <br>

    <br>
</form>
<br>
<div align="left">
    <form action="/article_list" method="get">
        <%TagListSingleton.getTagList().deleteInstance();%>
        <button class="btn btn-default btn-inverse" style="margin-left: 25px" type="submit">Close</button>
    </form>
</div>
<br/>

<script>
    var link = document.querySelector('#footer_template');
    var content = link.import;
    var el = content.querySelector('.footer');
    document.body.appendChild(el.cloneNode(true));
</script>
</body>
</html>
