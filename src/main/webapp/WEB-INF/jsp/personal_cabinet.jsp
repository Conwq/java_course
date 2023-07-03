<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set value="${sessionScope.userInfo}" var="user"/>
<c:set value="${sessionScope.error}" var="error"/>

<html>
<head>
    <title>Cabinet</title>
</head>
<body>

<h1>Hello, <b><c:out value="${user.login}"/></b>. Your role ${sessionScope.role}.</h1>
<hr>
<br>

<h3>Your personal data. In this form you can edit them.</h3><br>

<form action="<c:url value="/controller"/> " method="post">
    <input type="hidden" name="command" value="do_edit_user_info"/>
    <input type="hidden" name="id" value="${user.userId}">

    <label for="login">Old login:</label> <br>
    <input type="text" name="login" id="login" value="${user.login}"/> <br><br>

    <label for="email">Old email:</label><br>
    <input type="text" name="email" id="email" value="${user.email}"/> <br><br>

    <label for="password">Enter new password:</label> <br>
    <input type="text" name="password" id="password" value=""/> <br><br>

    <c:if test="${not (error eq null)}">
        <div style="color:red">${error}</div>
    </c:if>

    <input type="submit" value="Edit"/>
</form>

<button onclick="history.back()">Back</button>

</body>
</html>
