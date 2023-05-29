<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

guest info

<div class="body-title">
	<a href="">News >> </a> Latest News
</div>

<%--
	Эта вся информация, которую будет получать неавторизованный пользователь.
 	Здесь будет отображаться список последних 5 новостей, полученные из нашей БД, которые мы положили в качестве параметров в наш запрос
 	при помощи метода request.setAttribute("news", news) в конкрид контроллере GoToBasePage.
 	Здесь мы при помощи итератора отображаем список из 5 новостей, а при условии, если список новостей пустой печатаем надпись "No news."
--%>

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
