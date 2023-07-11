<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set value="${requestScope.users}" var="users"/>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.local" var="locale"/>

<fmt:message bundle="${locale}" key="local.locbutton.add_news.title" var="title_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.add_news.brief" var="brief_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.add_news.content" var="content_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.add_news.photo" var="photo_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.add_news.author" var="author_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.add_news.add" var="add_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.add_news.back" var="back_local"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/addNews.css">


<form class="add-news-form" action="<c:url value="/controller?command=do_add_news"/> " method="post" enctype="multipart/form-data">
    <label for="title">${title_local}</label><br>
    <input type="text" name="title" id="title"/><br>

    <label for="brief_news">${brief_local}</label><br>
    <input type="text" name="brief_news" id="brief_news"/><br>

    <label for="content">${content_local}</label><br>
    <input type="text" name="content" id="content"/><br>

    <label for="photo">${photo_local}</label><br>
    <input type="file" name="photo" id="photo"/><br>

    <label for="author">${author_local}</label>
    <select name="user_id" id="author">
        <c:forEach var="user" items="${users}">
            <option value="${user.userId}">
                ${user.login}
            </option>
        </c:forEach>
    </select><br><hr><br>

    <input type="submit" value="${add_local}"/>
</form>
<button onclick="history.back()">${back_local}</button>