<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:set value="${sessionScope.RegistrationError}" var="error"/>
<c:set value="${requestScope.local}" var="local"/>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.local" var="locale"/>

<fmt:message bundle="${locale}" key="local.locbutton.registration.header_text" var="header_text_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.registration.select_country" var="select_country_locale"/>
<fmt:message bundle="${locale}" key="local.locbutton.registration.country_us" var="us_locale"/>
<fmt:message bundle="${locale}" key="local.locbutton.registration.country_russia" var="russia_locale"/>
<fmt:message bundle="${locale}" key="local.locbutton.registration.enter_login" var="enter_login_locale"/>
<fmt:message bundle="${locale}" key="local.locbutton.registration.enter_email" var="enter_email_locale"/>
<fmt:message bundle="${locale}" key="local.locbutton.registration.enter_name" var="enter_name_locale"/>
<fmt:message bundle="${locale}" key="local.locbutton.registration.enter_surname" var="enter_surname_locale"/>
<fmt:message bundle="${locale}" key="local.locbutton.registration.enter_city" var="enter_city_locale"/>
<fmt:message bundle="${locale}" key="local.locbutton.registration.enter_password" var="enter_password_locale"/>
<fmt:message bundle="${locale}" key="local.locbutton.registration.enter_repeat_password" var="enter_repeat_password_locale"/>
<fmt:message bundle="${locale}" key="local.locbutton.registration.submit" var="submit_locale"/>
<fmt:message bundle="${locale}" key="local.locbutton.registration.back" var="back_locale"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/registration.css">

<h2><i>${header_text_locale}</i></h2>
<br><hr><br>

<div class="registration_form">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="do_registration"/>

		<label for="country">${select_country_locale}</label>
		<select id="country" multiple name="selectedLocale">
            <option value="en_US" ${local.getCountry() eq 'US' ? 'selected' : ''}>${us_locale}</option>
            <option value="ru_RU" ${local.getCountry() eq 'RU' ? 'selected' : ''}>${russia_locale}</option>
		</select>

        <label for="login">${enter_login_locale}</label>
        <input type="text" name="login" id="login"/>

        <label for="email">${enter_email_locale}</label>
        <input type="text" name="email" id="email"/>
        
        <label for="name">${enter_name_locale}</label>
        <input type="text" name="name" id="name"/>
        
        <label for="surname">${enter_surname_locale}</label>
        <input type="text" name="surname" id="surname"/>
        
        <label for="city">${enter_city_locale}</label>
        <input type="text" name="city" id="city"/><br>

        <label for="password">${enter_password_locale}</label>
        <input type="text" name="password" id="password"/>
        
        <label for="password_repeat">${enter_repeat_password_locale}</label>
        <input type="text" name="password_repeat" id="password_repeat"/>

        <c:if test="${not (error == null)}">
            <div style="color:red"><c:out value="${error}"/></div>
        </c:if>

        <input type="submit" value="${submit_locale}">
    </form>

    <button onclick="history.back()">${back_locale}</button>
</div>