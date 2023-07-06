<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set value="${requestScope.users}" var="users"/>

<form action="<c:url value="/controller?command=do_add_news"/> " method="post" enctype="multipart/form-data">
    <label for="title">Title:</label><br>
    <input type="text" name="title" id="title"/><br>

    <label for="brief_news">Brief news:</label><br>
    <input type="text" name="brief_news" id="brief_news"/><br>

    <label for="content">Content:</label><br>
    <input type="text" name="content" id="content"/><br>

    <label for="photo">Add photo:</label><br>
    <input type="file" name="photo" id="photo"/><br>

    <label for="author">Choose author:</label>
    <select name="user_id" id="author">
        <c:forEach var="user" items="${users}">
            <option value="${user.userId}">
                ${user.login}
            </option>
        </c:forEach>
    </select><br><hr><br>

    <input type="submit" value="Add news"/>
</form>
<button onclick="history.back()">Back</button>