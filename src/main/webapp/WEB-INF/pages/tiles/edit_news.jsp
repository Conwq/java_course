<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<c:set value="${requestScope.news}" var="news"/>

<form action="controller?command=do_edit_news" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${news.idNews}"/>
    <input type="hidden" name="photo_path" value="${news.photoPath}">

    <label for="title">Enter title:</label><br>
    <input type="text" name="title" id="title" value="${news.title}"><br>

    <label for="brief_news">Enter brief news:</label><br>
    <input type="text" name="brief_news" id="brief_news" value="${news.briefNews}"><br>

    <label for="content">Enter content news:</label><br>
    <input type="text" name="content" id="content" value="${news.content}"><br>

    <label for="photo">Change photo:</label><br>
    <input type="file" name="photo" id="photo"/><br>

    <input type="submit" value="Submit">
</form>
<button onclick="history.back()">Back</button>