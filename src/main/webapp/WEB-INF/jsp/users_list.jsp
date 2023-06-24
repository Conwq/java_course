<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>

  <ul>
    <c:forEach var="user" items="${requestScope.users}">
      <li>login - ${user.login}, email ${user.email}</li>
    </c:forEach>
  </ul>
  <button onclick="history.back()">Back</button>

</body>
</html>
