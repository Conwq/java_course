<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set value="${sessionScope.user}" var="user"/>
<c:set value="${sessionScope.role}" var="role"/>
<c:set value="${requestScope.action}" var="action"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
	<title>News manager</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<script type="text/javascript" src="script/validation.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/styles/newsStyle.css">
</head>

<body>
	<div class="page">

		<div class="header">
			<c:import url="/WEB-INF/pages/tiles/header.jsp" />
		</div>

		<div class="base-layout-wrapper">
			<div class="menu">
				<c:if test="${user eq 'not active'}">
				    Welcome!!!
				</c:if>

				<c:if test="${user eq 'active'}">
					<c:import url="/WEB-INF/pages/tiles/menu.jsp" />
				</c:if>
			</div>

			<div class="content">
				<c:choose>
					<c:when test="${action eq 'users_list' && user eq 'active' && role eq 'admin'}">
						<c:import url="/WEB-INF/pages/tiles/users_list.jsp"/>
					</c:when>
				
					<c:when test="${action eq 'add_news' && user eq 'active' && role eq 'admin'}">
						<c:import url="/WEB-INF/pages/tiles/add_news.jsp"/>
					</c:when>
				
					<c:when test="${action eq 'edit_news' && user eq 'active' && role eq 'admin'}">
						<c:import url="/WEB-INF/pages/tiles/edit_news.jsp"/>	
					</c:when>
					
					<c:when test="${action eq 'registration'}">
						<c:import url="/WEB-INF/pages/tiles/registration.jsp"/>
					</c:when>
					
					<c:when test="${action eq 'personal_cabinet' && user eq 'active'}">
						<c:import url="/WEB-INF/pages/tiles/personal_cabinet.jsp"/>
					</c:when>
					
					<c:when test="${user eq 'active'}">
						<c:import url="/WEB-INF/pages/tiles/body.jsp"/>
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