<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set value="${requestScope.users}" var="users"/>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.local" var="locale"/>

<fmt:message bundle="${locale}" key="local.locbutton.users_list.login" var="login_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.users_list.role" var="role_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.users_list.unban" var="unban_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.users_list.ban" var="ban_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.users_list.downgrade" var="downgrade_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.users_list.back" var="back_local"/>


  <ul>
    <c:forEach var="user" items="${users}">

      <li style="font-size: 16px">
        ${login_local} - <b>${user.login}</b>, email <b>${user.email}</b>, ${role_local} - <c:out value="${user.role}"/>

        <c:if test="${not (user.role eq 'admin'.toUpperCase())}">
          <c:if test="${user.banned}">
            <a href="<c:url value='/controller?command=do_unban_user&user_id=${user.userId}'/>">
              <button type="submit">${unban_local}</button>
            </a>
          </c:if>

          <c:if test="${not user.banned}">
            <a href="<c:url value='/controller?command=do_ban_user&user_id=${user.userId}'/>" >
              <button type="submit">${ban_local}</button>
            </a>
          </c:if>
        </c:if>

        <c:if test="${user.role eq 'admin'.toUpperCase()}">
          <a href=" <c:url value='/controller?command=do_downgrade_user&user_id=${user.userId}'/>">
            <button type="submit">${downgrade_local}</button>
          </a>
        </c:if>
      </li>

    </c:forEach>
  </ul>
  <button onclick="history.back()">${back_local}</button>