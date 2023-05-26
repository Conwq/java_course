<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--
	Одна из этих ссылок на код будут импортированы в текущий файл в слуае если условие будет true
--%>

<c:if test="${requestScope.presentation eq 'newsList' }">
	<c:import url="/WEB-INF/pages/tiles/newsList.jsp" />
</c:if>


<c:if test="${requestScope.presentation eq 'viewNews' }">
	<c:import url="/WEB-INF/pages/tiles/viewNews.jsp" />
</c:if>