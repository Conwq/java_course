<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Error page. Sorry.</h2>

<c:if test="${not (requestScope.CommandError eq null)}">
    <c:forEach var="error" items="${requestScope.CommandError}">
        <div style="color:red">
            <b><c:out value="${error}"/></b>
        </div>
    </c:forEach>
</c:if>

<c:if test="${not (requestScope.IllegalFormat eq null)}">
    <div style="color:red">
        <b><c:out value="${requestScope.IllegalFormat}"/> </b>
    </div>
</c:if>