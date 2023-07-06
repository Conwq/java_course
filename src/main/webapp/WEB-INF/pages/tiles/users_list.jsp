<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set value="${requestScope.users}" var="users"/>

  <ul>
    <c:forEach var="user" items="${users}">

      <li style="font-size: 16px">
        Login - <b>${user.login}</b>, email <b>${user.email}</b>, ROLE - <c:out value="${user.role}"/>

        <c:if test="${not (user.role eq 'admin'.toUpperCase())}">
          <c:if test="${user.banned}">
            <a href="<c:url value='/controller?command=do_unban_user&user_id=${user.userId}'/>">
              <button type="submit">Unban</button>
            </a>
          </c:if>

          <c:if test="${not user.banned}">
            <a href="<c:url value='/controller?command=do_ban_user&user_id=${user.userId}'/>" >
              <button type="submit">Ban</button>
            </a>
          </c:if>
        </c:if>

        <c:if test="${user.role eq 'admin'.toUpperCase()}">
          <a href=" <c:url value='/controller?command=do_downgrade_user&user_id=${user.userId}'/>">
            <button type="submit">Downgrade role to user</button>
          </a>
        </c:if>
      </li>

    </c:forEach>
  </ul>
  <button onclick="history.back()">Back</button>