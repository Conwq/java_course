<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--
	Одна из этих ссылок на код будут импортированы в текущий файл в слуае если условие будет true

	Если пользователь просто перейдет на эту страницу, то для него отобразится список со всеми новостями,
	поскольку в него положится аттрибут со значением newsList.

	Если пользователь в коде отображения всех страниц нажмет на кнопку viewList, то отправится гет запрос обратно
	на наш фронт контроллер, после чего в качестве аттрибута вложится значение viewNews и отобразится уже 2-ой код.
--%>

<c:if test="${requestScope.presentation eq 'newsList' }">
	<c:import url="/WEB-INF/pages/tiles/newsList.jsp" />
</c:if>


<c:if test="${requestScope.presentation eq 'viewNews' }">
	<c:import url="/WEB-INF/pages/tiles/viewNews.jsp" />
</c:if>