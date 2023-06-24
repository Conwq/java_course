<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Edit news</title>
</head>
<body>

<form action="controller" method="post">
    <input type="hidden" name="command" value="do_edit_news"/>
    <input type="hidden" name="id" value="${requestScope.news.idNews}"/>

    <label for="title">Enter title:</label><br>
    <input type="text" name="title" id="title" value="${requestScope.news.title}"><br>

    <label for="brief_news">Enter brief news:</label><br>
    <input type="text" name="brief_news" id="brief_news" value="${requestScope.news.briefNews}"><br>

    <label for="content">Enter content news:</label><br>
    <input type="text" name="content" id="content" value="${requestScope.news.content}"><br>

    <label for="date">Enter date news:</label><br>
    <input type="text" name="date" id="date" value="${requestScope.news.newsDate}"><br>

    <label for="photo">Enter photo path:</label><br>
    <input type="text" name="photo" id="photo" value="${requestScope.news.photoPath}"><br><br>

    <input type="submit" value="Submit">
</form>
<button onclick="history.back()">Back</button>


</body>
</html>
