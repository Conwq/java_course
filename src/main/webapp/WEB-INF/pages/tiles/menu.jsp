<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="localization.local" var="locale"/>

<fmt:message bundle="${locale}" key="local.locbutton.menu.add_news" var="add_news"/>
<fmt:message bundle="${locale}" key="local.locbutton.menu.news_list" var="news_list"/>

<div class="menu-wrapper">

	<div class="menu-title-wrapper">
		<div class="menu-title">
		       News
		</div>
	</div>

	<div class="list-menu-invisible-wrapper">
		<div class="list-menu-wrapper" style="float: right;">

			<ul style="list-style-image: url(images/img.jpg); text-align: left;">
				<li style="padding-left: 15px;">
					<a href="<c:url value="/controller?command=go_to_news_list"/>">${news_list}</a>
					<br />
				</li>

				<c:if test="${sessionScope.role eq 'admin'}">
				   <li style="padding-left: 15px;">
				    	<a href="<c:url value="/controller?command=go_to_add_news"/>">${add_news}</a><br/>
					</li>
				</c:if>
			</ul>
		</div>

		<div class="clear"></div>
	</div>

	<div style="height: 25px;"></div>
</div>

