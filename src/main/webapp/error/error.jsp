<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set value="${requestScope.error}" var="error"/>


<html>

<head>
    <title>Error page</title>
    <style>
        body {
            background-color: #f5f5f5;
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
        }

        h1 {
            color: #333;
        }

        img {
            width: 200px;
            height: 200px;
            margin-top: 20px;
        }
    </style>
</head>

<body>
    <h1>Server Error</h1>
    <p>The server is currently unavailable. Please try again later.</p>
    <img src="${pageContext.request.contextPath}/images/Sleeping_owl.jpg" alt="Sleeping Owl">

  <c:if test="${not (error eq null)}">
      <div style="color:red"><c:out value="${error}"/> </div>
  </c:if>
  <br><br>
  <a href="${pageContext.request.contextPath}/index.jsp">Go to main page</a>

</body>
</html>
