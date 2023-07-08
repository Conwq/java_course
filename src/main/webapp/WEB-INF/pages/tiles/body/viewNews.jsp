<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set value="${requestScope.news}" var="news"/>
<c:set value="${sessionScope.role}" var="role"/>


<div class="body-title">
	<a href="">News >> </a> View News
</div>

<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text">News Title</td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${news.title }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">News Date</td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${news.newsDate }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">Brief</td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${news.briefNews }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">Content</td>
			<td class="space_around_view_text">
				<div class="word-breaker">
					<c:out value="${news.content }" />
				</div></td>
		</tr>
	</table>

	<c:if test="${role eq 'admin'}">
		<div class="first-view-button">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="go_to_edit_news"/>
				<input type="hidden" name="id" value="${news.idNews}"/>

				<input type="submit" value="Edit"/>
			</form>
		</div>

		<div class="second-view-button">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="do_delete_news" />
				<input type="hidden" name="id" value="${news.idNews}" />

				<input type="submit" value="Delete" />
			</form>
		</div>
	</c:if>
    <button onclick="history.back()">Back</button>
    <br>

    <img src="${news.photoPath}" alt="Not photo" width="30%" height="30%" style="display: block; margin-left: auto; margin-right: auto;"/> <br><br><hr>

    <c:import url="/WEB-INF/pages/tiles/comments.jsp"/>
</div>


