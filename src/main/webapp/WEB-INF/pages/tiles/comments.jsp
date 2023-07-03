<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<c:set value="${requestScope.comments}" var="comments"/>
	<c:set value="${requestScope.news}" var="news"/>
	<c:set value="${sessionScope.userInfo}" var="user"/>
	<c:set value="${sessionScope.role}" var="role"/>

<body>
	<div align="center">
		<h3>Комментарии</h3> <br> <br>
	</div>
	
	<c:if test="${empty comments}">
		<h3>No comments, be first.</h3>
	</c:if>
	
	<c:if test="${not empty comments}">
		<table style="width: 100%; max-width: 670px;">
			<thead>
			<tr>
				<th style="width: 30%; text-align: left;">Date</th>
				<th style="width: 20%; text-align: left;">Username</th>
				<th style="width: 100%; text-align: left;">Comment</th>
				<c:if test="${role eq 'admin'}">
					<th style="width: 20%; text-align: right;">Command</th>
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
								<button type="submit">Delete</button>
							</form>

							<form action="controller?command=go_to_edit_comment&comment_id=${comment.commentId}" method="post">
								<input type="hidden" name="news_id" value="${news.idNews}"/>
								<button type="submit">Edit</button>
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
	<c:if test="${not (requestScope.comment_text eq null)}">
		<form action="controller?command=do_edit_comment" method="post">
			<input type="hidden" name="comment_id" value="${requestScope.comment_id}"/>
			<input type="hidden" name="news_id" value="${news.idNews}"/>

			<label for="text">Write here:</label> <br>
			<textarea id="text" name="text" rows="4" cols="120" required><c:out value="${requestScope.comment_text}"/></textarea><br>

			<input type="submit" value="Send">
		</form>
	</c:if>

	<c:if test="${requestScope.comment_text eq null}">
		<form action="controller?command=do_add_comment" method="post">
			<input type="hidden" name="news_id" value="${news.idNews}"/>
			<input type="hidden" name="user_id" value="${user.userId}"/>

			<label for="text">Write here:</label> <br>
			<textarea id="text" name="text" rows="4" cols="120" placeholder="Write comments here... Max count - 300 characters" required></textarea><br>

			<input type="submit" value="Send">
		</form>
	</c:if>

	<c:if test="${not (sessionScope.error eq null)}">
		<div style="color:red"><c:out value="${sessionScope.error}"/></div>
	</c:if>
</body>
	