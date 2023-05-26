<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="script/validation.js"></script>
<title>locale.linkname.headertitle <!-- <bean:message key="locale.linkname.headertitle" />
 -->
</title>

<link rel="stylesheet" type="text/css" href="styles/newsStyle.css">

</head>

<body>

<%--
	Наша первоначальная страница при входе на localhost:8080/, которую увидит пользователь.
	Эта страница <div class="page"> состоит из:
	 	- верхнего колонититула 			<div class="header">
	 	- вкладки меню слевой стороны 		<div class="menu">
	 	- основной информации 				<div class="content">
	 	- нижнего колонититула 				<div class="footer">

	Верхний колонититул на текущем виде содержит ссылку на код из файла header.jsp. Содержимое данного файла импортирует на текущую страницу.

	Вкладка меню состоиз из динамически отображаемой информации. Если пользователь не авторизован будет отображаться текст с приветсвием. Если
	пользователь авторизован, то вместо приветствия будет импортирован код с другой jsp страницы menu.jsp.

	Наше содержание так же представляет динамически отображаемую информацию, если пользователь не авторизован, то будет импортирован код с одной страницы и
	будет выведена информация согласно кода, а если авторизован, то будет импортирован другой код и будет отображаться совершенно другая информация.

	Нижний колонтитул состоит из импорта кода с другого jsp файла footer.jsp.

	При помощи тегов div мы формируем отображение блоков кода на странице.

	<c: if test ="${...}">    </c: if>   	 - это блок условного оператора, если условие выполняется, то и выполняется часть кода указанноого внутри данных тегов
	<c: import url="..."/>            		 - этот JSTL код импортирует весь код с указанного файла и вставляет в то место, где он указан
--%>

	<div class="page">

		<div class="header">
			<c:import url="/WEB-INF/pages/tiles/header.jsp" />
		</div>

		<div class="base-layout-wrapper">


			<div class="menu">

				<c:if test="${not (sessionScope.user eq 'active')}">
				    Welcome!!!!!
				</c:if>

				<<c:if test="${sessionScope.user eq 'active'}">
					<c:import url="/WEB-INF/pages/tiles/menu.jsp" />
				</c:if>
			</div>

			<div class="content">


				<c:if test="${not (sessionScope.user eq 'active')}">
					<c:import url="/WEB-INF/pages/tiles/guestInfo.jsp" />
				</c:if>

				<c:if test="${sessionScope.user eq 'active'}">
					<c:import url="/WEB-INF/pages/tiles/body.jsp" />
				</c:if>
			</div>

		</div>

		<div class="footer">
			<c:import url="/WEB-INF/pages/tiles/footer.jsp" />
		</div>

	</div>

</body>
</html>