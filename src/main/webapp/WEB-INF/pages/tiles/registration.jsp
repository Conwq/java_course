<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<h2><i>Registration form</i></h2><br>
<hr>
<br>

<div class="registration_form">
    <form action="controller" method="post">
        <input type="hidden" name="command" value="do_registration"/>

        <label for="login">Enter login:</label><br>
        <input type="text" name="login" id="login"><br>

        <label for="email">Enter email:</label><br>
        <input type="text" name="email" id="email"><br>

        <label for="password">Enter password:</label><br>
        <input type="text" name="password" id="password"><br>

        <c:if test="${not (sessionScope.RegistrationError == null)}">
            <div style="color:red"><c:out value="${sessionScope.RegistrationError}"/></div>
        </c:if>

        <input type="submit" value="Submit">
    </form>

    <button onclick="history.back()">Back</button>
</div>