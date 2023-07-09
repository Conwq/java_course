<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/comments.css">

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.local" var="locale"/>

<fmt:message bundle="${locale}" key="local.locbutton.comments.comments" var="comments_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.comments.no_comments" var="no_comments_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.comments.write_here" var="write_here_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.comments.length_string" var="length_string_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.comments.date" var="date_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.comments.username" var="username_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.comments.command" var="command_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.comments.delete" var="delete_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.comments.edit" var="edit_local"/>
<fmt:message bundle="${locale}" key="local.locbutton.comments.send" var="send_local"/>

<c:set value="${requestScope.comments}" var="comments"/>
<c:set value="${requestScope.news}" var="news"/>
<c:set value="${sessionScope.userInfo}" var="user"/>
<c:set value="${sessionScope.role}" var="role"/>
<c:set value="${requestScope.comment_text}" var="text"/>
<c:set value="${sessionScope.error}" var="error"/>
<c:set value="${requestScope.comment_id}" var="comment_id"/>

<div class="comments">
	<div align="center">
		<h3>${comments_local}</h3> <br> <br>
	</div>
	
	<c:if test="${empty comments}">
		<b>${no_comments_local}</b>
	</c:if>
	
	<c:if test="${not empty comments}">
		<table style="width: 100%; max-width: 670px;">
			<thead>
			<tr>
				<th style="width: 30%; text-align: left;">${date_local}</th>
				<th style="width: 20%; text-align: left;">${username_local}</th>
				<th style="width: 100%; text-align: left;">${comments_local}</th>
				<c:if test="${role eq 'admin'}">
					<th style="width: 20%; text-align: right;">${command_local}</th>
				</c:if>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="comment" items="${comments}">
				<tr>
					<td>${comment.date}</td>
					<td>${comment.newUserInfo.login}</td>
					<td>${comment.text}</td>

					<c:if test="${role eq 'admin'}">
						<td>
							<form action="controller?command=do_delete_comment&comment_id=${comment.commentId}" method="post">
								<input type="hidden" name="news_id" value="${news.idNews}"/>
								<button type="submit">${delete_local}</button>
							</form>

							<form action="controller?command=go_to_edit_comment&comment_id=${comment.commentId}" method="post">
								<input type="hidden" name="news_id" value="${news.idNews}"/>
								<button type="submit">${edit_local}</button>
							</form>
						</td>
					</c:if>
				</tr>
				<tr style="position: relative;">
					<td colspan="4" style="position: absolute; bottom: 0; left: 0; width: 100%; border-bottom: 1px solid #000;"></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>

	<br><br>
	<c:if test="${not (text eq null)}">
		<form action="controller?command=do_edit_comment" method="post">
			<input type="hidden" name="comment_id" value="${comment_id}"/>
			<input type="hidden" name="news_id" value="${news.idNews}"/>

			<label for="text">${write_here_local}</label> <br>
			<textarea id="text" name="text" rows="4" cols="120" required><c:out value="${text}"/></textarea><br>

			<input type="submit" value="Send">
		</form>
	</c:if>

	<c:if test="${text eq null}">
		<form action="controller?command=do_add_comment" method="post">
			<input type="hidden" name="news_id" value="${news.idNews}"/>
			<input type="hidden" name="user_id" value="${user.userId}"/>

			<label for="text">${write_here_local}</label> <br>
			<textarea id="text" name="text" rows="4" cols="120" placeholder="${length_string_local}" required></textarea><br>

			<input type="submit" value="${send_local}">
		</form>
	</c:if>

	<c:if test="${not (error eq null)}">
		<div style="color:red"><c:out value="${error}"/></div>
	</c:if>
</div>
	