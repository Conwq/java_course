<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.local" var="local"/>

<fmt:message bundle="${local}" key="local.locbutton.content.editlink" var="editlink"/>
<fmt:message bundle="${local}" key="local.locbutton.content.viewlink" var="viewlink"/>
<fmt:message bundle="${local}" key="local.locbutton.content.delete_selected_newses" var="delete_newses"/>

<c:set value="${sessionScope.error_delete_newses}" var="errorDeleteNewses"/>
<c:set value="${requestScope.news}" var="news"/>
<c:set value="${sessionScope.role}" var="role"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/newsList.css"/>

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
						      <a href="<c:url value="/controller?command=go_to_edit_news&id=${news.idNews}"/>">${editlink}</a>
						</c:if>
						&nbsp;&nbsp;&nbsp;
						<a href="<c:url value="controller?command=go_to_view_news&id=${news.idNews}"/>">${viewlink}</a>
						&nbsp;&nbsp;&nbsp;
   					    <c:if test="${role eq 'admin'}">
   					         <input type="checkbox" name="select_newses" value="${news.idNews }"/>
   					    </c:if>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>

	<c:if test="${not (errorDeleteNewses eq null)}">
		<div style="color:red">
			<h2><c:out value="${errorDeleteNewses}"/></h2>
		</div>
	</c:if>

	<c:if test="${role eq 'admin'}">
		<input type="submit" value="${delete_newses}"/>
	</c:if>
</form>
