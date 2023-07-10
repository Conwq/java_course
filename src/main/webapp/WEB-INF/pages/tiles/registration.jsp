<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<c:set value="${sessionScope.RegistrationError}" var="error"/>
<c:set value="${sessionScope.local}" var="local"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/registration.css">

<h2><i>Registration form</i></h2>
<br><hr><br>

<div class="registration_form">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="do_registration"/>

		<label for="country">Select your country of residence:</label>
		<select id="country" multiple name="country">
			<option value="ru" ${local.getLanguage() eq 'ru' ? 'selected' : ''}>Russia</option>
			<option value="en" ${local.getLanguage() eq 'en' ? 'selected' : ''}>United States</option>
		</select>

        <label for="login">Enter login:</label>
        <input type="text" name="login" id="login"/>

        <label for="email">Enter email:</label>
        <input type="text" name="email" id="email"/>
        
        <label for="name">Enter name:</label>
        <input type="text" name="name" id="name"/>
        
        <label for="surname">Enter surname:</label>
        <input type="text" name="surname" id="surname"/>
        
        <label for="city">Enter city of residence</label>
        <input type="text" name="city" id="city"/><br>

        <label for="password">Enter password:</label>
        <input type="text" name="password" id="password"/>
        
        <label for="repeat">Repeat password:</label>
        <input type="text" name="repeat" id="password_repeat"/>

        <c:if test="${not (error == null)}">
            <div style="color:red"><c:out value="${error}"/></div>
        </c:if>

        <input type="submit" value="Submit">
    </form>

    <button onclick="history.back()">Back</button>
</div>