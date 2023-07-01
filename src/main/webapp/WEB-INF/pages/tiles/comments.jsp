<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<c:set value="${requestScope.comments}" var="comments"/>
	<c:set value="${requestScope.news}" var="news"/>
	<c:set value="${sessionScope.userInfo}" var="user"/>

<body>
	<div align="center">
		<h3>Комментарии</h3>
	</div>
	
	<c:if test="${empty comments}">
		<h3>No comments, be first.</h3>
	</c:if>
	
	<c:if test="${not empty comments}">
		<table style="width: 100%; max-width: 500px;">
			<thead>
			<tr>
				<th style="width: 30%; text-align: left;">Date</th>
				<th style="width: 30%; text-align: left;">Username</th>
				<th style="width: 70%; text-align: left;">Comment</th>
			</tr>
			</thead>
			<tbody>
			<c:forEach var="comment" items="${comments}">
				<tr>
					<td>${comment.date}</td>
					<td>${comment.newUserInfo.login}</td>
					<td>${comment.text}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</c:if>

	<br><br>
 	 
  	<form action="controller?command=do_add_comment" method="post">
  		<input type="hidden" name="news_id" value="${news.idNews}"/>

		<input type="hidden" name="user_id" value="${user.userId}"/>

		<label for="text">Write here:</label> <br>
		<textarea id="text" name="text" rows="4" cols="120" placeholder="Write comments here... Max count - 300 characters" required></textarea><br>
  
 		<input type="submit" value="Send">
	</form>
</body>
	