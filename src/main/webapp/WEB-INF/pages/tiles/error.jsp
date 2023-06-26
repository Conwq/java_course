<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Error page. Sorry.</h2>

<c:if test="${not (sessionScope.RegistrationError eq null)}">
    <div style="color:red">
        <b><c:out value="${sessionScope.RegistrationError}"/></b>
    </div>
</c:if>

<a href="${pageContext.request.contextPath}/index.jsp">Back</a>