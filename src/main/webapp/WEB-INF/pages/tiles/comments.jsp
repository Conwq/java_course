
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<body>

	<div align="center">
		<h3>Комментарии</h3>
	</div>
	
	<c:if test="${requestScope.comments eq  null}">
		No comments, be first.
	</c:if>
	
	<c:if test="${not (requestScope.comments eq null)}">
		<table>
		    <thead>
		      <tr>
		        <th style="width: ">Date</th>
		        <th style="width: ">Text</th>
		      </tr>
		    </thead>
		    <tbody>
		      <c:forEach var="comment" items="${requestScope.comments}">
		        <tr>
		          <td>${comment.date}</td>
		          <td>
		          	<b>${comment.user.login}</b><br> 
		          	   ${comment.text} 
		          </td>
		        </tr>
		      </c:forEach>
		    </tbody>
  		</table> <br><br>
	</c:if>
	
 	 
  	<form action="controller" method="post">
  		<input type="hidden" name="command" value="do_add_comment"/>
  		<input type="hidden" name="news_id" value="${requestScope.news.newsId}">
  		<input type="hidden" name="user_id" value="${request }">
  		<textarea id="comment" name="comment" rows="4" cols="30" placeholder="Write comments here ...Max count - 300 characters" required></textarea><br>
  
 		<input type="submit" value="Send">
	</form>
</body>
	