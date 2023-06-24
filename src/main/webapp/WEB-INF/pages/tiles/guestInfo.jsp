<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

guest info

<div class="body-title">
	<a href="">News >> </a> Latest News
</div><br><hr>

	<c:forEach var="news" items="${requestScope.news}">
		<div class="single-news-wrapper">

			<div class="single-news-header-wrapper">

				<div class="news-title">
					<u><c:out value="${news.title}" /></u>
				</div>

				<div class="news-date">
					<c:out value="${news.newsDate}" />
				</div>

				<div class="news-content">
					<b><c:out value="${news.briefNews}" /></b>
				</div>
			</div>
			<br><hr>
		</div>
	</c:forEach>

	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
        	No news.
		</c:if>
	</div>

