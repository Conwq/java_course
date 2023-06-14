<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

guest info

<div class="body-title">
	<a href="">News >> </a> Latest News
</div>

<%--
	��� ��� ����������, ������� ����� �������� ���������������� ������������.
 	����� ����� ������������ ������ ��������� 5 ��������, ���������� �� ����� ��, ������� �� �������� � �������� ���������� � ��� ������
 	��� ������ ������ request.setAttribute("news", news) � ������� ����������� GoToBasePage.
 	����� �� ��� ������ ��������� ���������� ������ �� 5 ��������, � ��� �������, ���� ������ �������� ������, �������� ������� "No news."
--%>
	<br><hr>

	<c:forEach var="news" items="${requestScope.news}">
		<div class="single-news-wrapper">

			<div class="single-news-header-wrapper">

				<div class="news-title">
					<c:out value="${news.title}" />
				</div>

				<div class="news-date">
					<c:out value="${news.newsDate}" />
				</div>

				<div class="news-content">
					<c:out value="${news.briefNews}" />
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