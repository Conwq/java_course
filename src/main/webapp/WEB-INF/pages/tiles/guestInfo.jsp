<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
	.single-news-wrapper {
		margin-bottom: 10px;
		padding: 10px;
		border: 1px solid #ccc;
		position: relative;
	}

	.news-title {
		font-size: 18px;
		font-weight: bold;
		text-decoration: underline;
		margin-bottom: 0;
	}

	.news-date {
		position: absolute;
		top: 0;
		right: 0;
		font-style: italic;
		background-color: #fff;
		padding: 2px 5px;
	}

	.news-content {
		margin-top: 5px;
	}

</style>

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
		</div>
	</c:forEach>

	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
        	No news.
		</c:if>
	</div>

