<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set value="${requestScope.presentation}" var="presentation"/>

<c:if test="${presentation eq 'newsList' }">
	<c:import url="/WEB-INF/pages/tiles/body/newsList.jsp" />
</c:if>

<c:if test="${presentation eq 'viewNews' }">
	<c:import url="/WEB-INF/pages/tiles/body/viewNews.jsp" />
</c:if>