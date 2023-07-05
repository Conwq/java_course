<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set value="${sessionScope.user}" var="user"/>
<c:set value="${requestScope.registration}" var="registration"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="script/validation.js"></script>
<title>News manager</title>

<link rel="stylesheet" type="text/css" href="styles/newsStyle.css">
</head>

<body>
	<div class="page">

		<div class="header">
			<c:import url="/WEB-INF/pages/tiles/header.jsp" />
		</div>

		<div class="base-layout-wrapper">

			<div class="menu">
				<c:if test="${not (user eq 'active')}">
				    Welcome!!!!!
				</c:if>

				<c:if test="${user eq 'active'}">
					<c:import url="/WEB-INF/pages/tiles/menu.jsp" />
				</c:if>
			</div>

			<div class="content">
				<c:choose>
					<c:when test="${user eq 'active'}">
						<c:import url="/WEB-INF/pages/tiles/body.jsp" />
					</c:when>

					<c:when test="${registration eq 'registration'}">
						<c:import url="/WEB-INF/pages/tiles/registration.jsp"/>
					</c:when>

					<c:otherwise>
						<c:import url="/WEB-INF/pages/tiles/guestInfo.jsp" />
					</c:otherwise>
				</c:choose>
			</div>

		</div>

		<div class="footer">
			<c:import url="/WEB-INF/pages/tiles/footer.jsp" />
		</div>

	</div>
</body>
</html>