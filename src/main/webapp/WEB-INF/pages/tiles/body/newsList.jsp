<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set value="${requestScope.news}" var="news"/>
<c:set value="${sessionScope.role}" var="role"/>

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
		margin-bottom: 0; /* Удаляем нижний отступ */
	}

	.news-date {
		position: absolute;
		top: 0;
		right: 0; /* Изменяем значение свойства left на right */
		font-style: italic;
		background-color: #fff;
		padding: 2px 5px;
	}

	.news-content {
		margin-top: 5px; /* Изменяем значение на 5px */
	}


	.form_news input[type="submit"] {
		padding: 5px 10px;
		background-color: #337ab7;
		color: #fff;
		border: none;
		cursor: pointer;
	}

	.form_news input[type="submit"]:hover {
		background-color: #23527c;
	}
</style>

<div class="body-title">
	<a href="">News >> </a> News List
</div><br><hr>

<div class="no-news">
	<c:if test="${news eq null || empty news }">
		No news.
	</c:if>
</div>

<form action="controller?command=do_delete_newses" method="post">
	<c:forEach var="news" items="${news}">
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
						<c:if test="${role eq 'admin'}">
						      <a href="<c:out value="/controller?command=go_to_edit_news&id=${news.idNews}"/>">editlink</a>
						</c:if>
						&nbsp;&nbsp;&nbsp;
						<a href="controller?command=go_to_view_news&id=${news.idNews}">viewlink </a>
						&nbsp;&nbsp;&nbsp;
   					    <c:if test="${role eq 'admin'}">
   					         <input type="checkbox" name="select_newses" value="${news.idNews }"/>
   					    </c:if>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
	<input type="submit" value="Delete selected newses"/>
</form>
