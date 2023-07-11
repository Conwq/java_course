<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<c:set value="${requestScope.news}" var="news"/>

<fmt:setLocale value= "${sessionScope.locale}"/>
<fmt:setBundle basename="localization.local" var="locale" />

<fmt:message bundle="${locale}" key="local.locbutton.edit_news.enter_title" var="title_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.edit_news.enter_brief_news" var="brief_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.edit_news.enter_content_news" var="content_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.edit_news.insert_photo" var="insert_photo_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.edit_news.change_file" var="change_file_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.edit_news.submit" var="submit_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.edit_news.back" var="back_local"/>

 <form action="controller?command=do_edit_news" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${news.idNews}"/>
    <input type="hidden" name="photo_path" value="${news.photoPath}">

    <label for="title">${title_local}</label><br>
    <input type="text" name="title" id="title" value="${news.title}"><br>

    <label for="brief_news">${brief_local}</label><br>
    <input type="text" name="brief_news" id="brief_news" value="${news.briefNews}"><br>

    <label for="content">${content_local}</label><br>
    <input type="text" name="content" id="content" value="${news.content}"><br>

    <label for="photo">${insert_photo_local}</label><br>
    <input type="file" name="photo" id="photo"/><br>

    <input type="submit" value="${submit_local}">
</form>
<button onclick="history.back()">${back_local}</button>