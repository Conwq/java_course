<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/viewNews.css"/>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.local" var="locale"/>

<fmt:message bundle="${locale}" key="local.locbutton.view_news.delete" var="delete"/>
<fmt:message bundle="${locale}" key="local.locbutton.view_news.back" var="back"/>
<fmt:message bundle="${locale}" key="local.locbutton.view_news.edit" var="edit"/>
<fmt:message bundle="${locale}" key="local.locbutton.view_news.news_title" var="news_title"/>
<fmt:message bundle="${locale}" key="local.locbutton.view_news.news_date" var="news_date"/>
<fmt:message bundle="${locale}" key="local.locbutton.view_news.brief" var="brief"/>
<fmt:message bundle="${locale}" key="local.locbutton.view_news.content" var="content"/>
<fmt:message bundle="${locale}" key="local.locbutton.view_news.not_photo" var="not_photo"/>
<fmt:message bundle="${locale}" key="local.locbutton.view_news.news" var="newses"/>
<fmt:message bundle="${locale}" key="local.locbutton.view_news.view_news" var="view_news"/>

<c:set value="${requestScope.news}" var="news"/>
<c:set value="${sessionScope.role}" var="role"/>

<div class="body-title">
	<a href="">${newses} >> </a> ${view_news}
</div>

<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text">${news_title}</td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${news.title }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">${news_date}</td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${news.newsDate }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">${brief}</td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${news.briefNews }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">${content}</td>
			<td class="space_around_view_text">
				<div class="word-breaker">
					<c:out value="${news.content }"/>
				</div></td>
		</tr>
	</table>

	<c:if test="${role eq 'admin'}">
		<div class="first-view-button">
			<a href="<c:url value="/controller?command=go_to_edit_news&id=${news.idNews}"/>">
				<button>${edit}</button>
			</a>
		</div>

		<div class="second-view-button">
			<form action="controller" method="post">
				<input type="hidden" name="command" value="do_delete_news" />
				<input type="hidden" name="id" value="${news.idNews}" />

				<input type="submit" value="${delete}"/>
			</form>
		</div>
	</c:if>
    <button onclick="history.back()" class="button-wrapper">${back}</button>
    <br>

    <img src="${news.photoPath}" alt="${not_photo}" width="30%" height="30%" style="display: block; margin-left: auto; margin-right: auto;"/> <br><br><hr>

    <c:import url="/WEB-INF/pages/tiles/comments.jsp"/>
</div>


