<<<<<<< HEAD
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
=======
	
>>>>>>> fcf5ae30407940892297d838de70efbad4a4cc72
	
<body>

	<div align="center">
		<h3>Комментарии</h3>
	</div>
	
<<<<<<< HEAD
	<c:if test="${requestScope.comments eq  null}">
		No comments, be first.
	</c:if>
	
	<c:if test="${not (requestScope.comments eq null)}">
		<table>
		    <thead>
		      <tr>
		        <th>Date</th>
		        <th>Text</th>
		      </tr>
		    </thead>
		    <tbody>
		      <c:forEach var="comment" items="${requestScope.comments}">
		        <tr>
		          <td>${comment.date}</td>
		          <td>
		          	<b>${comment.user}</b><br> 
		          	   ${comment.text} 
		          </td>
		        </tr>
		      </c:forEach>
		    </tbody>
  		</table> <br><br>
	</c:if>
 	 
  	<form action="comments" method="post">
  		<textarea id="comment" name="comment" rows="4" cols="30" required placeholder="Write comments here ..."></textarea><br>
  
 		<input type="submit" value="Send">
	</form>
=======
	<table>
    <thead>
      <tr>
        <th>Date</th>
        <th>User</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach var="row" items="${rows}">
        <tr>
          <td>${row.date}</td>
          <td>${row.user}</td>
        </tr>
      </c:forEach>
    </tbody>
  </table>
>>>>>>> fcf5ae30407940892297d838de70efbad4a4cc72

</body>
	