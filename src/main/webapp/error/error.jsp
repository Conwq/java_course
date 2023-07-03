<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set value="${requestScope.error}" var="error"/>


<html>

<head>
    <title>Error page</title>
</head>

<body>

  <h1>It`s error page</h1> <br> <br>

  <c:if test="${not (error eq null)}">
      <div style="color:red"><c:out value="${error}"/> </div>
  </c:if>
  <br><br>

  <a href="${pageContext.request.contextPath}/index.jsp">Go to main page</a>

</body>
</html>
