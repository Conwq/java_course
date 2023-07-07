<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set value="${requestScope.users}" var="users"/>

<style>
    form.add-news-form {
        padding: 20px;
        background-color: #f2f2f2;
        border: 1px solid #ccc;
        border-radius: 5px;
    }

    form.add-news-form label {
        font-weight: bold;
    }

    form.add-news-form input[type="text"],
    form.add-news-form select {
        width: 100%;
        padding: 8px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        margin-bottom: 10px;
    }

    form.add-news-form input[type="file"] {
        margin-bottom: 10px;
    }

    form.add-news-form input[type="submit"],
    form.add-news-form button {
        padding: 10px 20px;
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    form.add-news-form button {
        background-color: #ccc;
    }

    form.add-news-form button:hover,
    form.add-news-form input[type="submit"]:hover {
        background-color: #45a049;
    }

    form.add-news-form hr {
        border: none;
        border-top: 1px solid #ccc;
        margin-bottom: 20px;
    }
</style>


<form class="add-news-form" action="<c:url value="/controller?command=do_add_news"/> " method="post" enctype="multipart/form-data">
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