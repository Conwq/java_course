<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:set value="${sessionScope.userInfo}" var="user"/>
<c:set value="${sessionScope.error}" var="error"/>
<c:set value="${sessionScope.role}" var="role"/>

<fmt:setLocale value= "${sessionScope.locale}"/>
<fmt:setBundle basename="localization.local" var="locale" />

<fmt:message bundle="${locale}" key="local.locbutton.personal_cabinet.text" var="text_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.personal_cabinet.login" var="login_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.personal_cabinet.email" var="email_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.personal_cabinet.password" var="password_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.personal_cabinet.edit" var="edit_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.personal_cabinet.back" var="back_local"/>

<h1>Hello, <b><c:out value="${user.login}"/></b>. Your role ${role}.</h1><hr><br>

<h3>${text_local}</h3><br>

<form action="<c:url value="/controller?command=do_edit_user_info&id=${user.userId}"/> " method="post">

    <label for="login">${login_local}</label> <br>
    <input type="text" name="login" id="login" value="${user.login}"/> <br><br>

    <label for="email">${email_local}</label><br>
    <input type="text" name="email" id="email" value="${user.email}"/> <br><br>

    <label for="password">${password_local}</label> <br>
    <input type="text" name="password" id="password" value=""/> <br><br>

    <c:if test="${not (error eq null)}">
        <div style="color:red">${error}</div>
    </c:if>

    <input type="submit" value="${edit_local}"/>
</form>

<button onclick="history.back()">${back_local}</button>

