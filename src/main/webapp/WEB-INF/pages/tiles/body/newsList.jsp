<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="body-title">
	<a href="">News >> </a> News List
</div>

<form action="" method="post">
	<c:forEach var="news" items="${requestScope.news}">
		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">
				<div class="news-title">
					<u><c:out value="${news.title}" /></u>
				</div>

				<div class="news-date" style="display: inline-block">
					<c:out value="${news.newsDate}" />
				</div>

				<div class="news-content" style="display: inline-block">
					<b><c:out value="${news.briefNews}" /></b>
				</div>

				<div class="news-link-to-wrapper">
					<div class="link-position">
						<c:if test="${sessionScope.role eq 'admin'}">
						      <a href="<c:out value="/controller?command=go_to_edit_news&id=${news.idNews}"/>">editlink</a>
						</c:if>
						
						<a href="controller?command=go_to_view_news&id=${news.idNews}">viewlink </a> 
   					    
<%--   					    <c:if test="${sessionScope.role eq 'admin'}">--%>
<%--   					         <input type="checkbox" name="idNews" value="${news.idNews }" />--%>
<%--   					    </c:if>--%>
					</div>
				</div>

			</div>
		</div>
		<br><hr>

	</c:forEach>

	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
        	No news.
		</c:if>
	</div>
</form>
